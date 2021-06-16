package cn.edu.sicau.czczl.aop;

import cn.edu.sicau.czczl.annotation.Authentication;
import cn.edu.sicau.czczl.annotation.constant.AuthAopConstant;
import cn.edu.sicau.czczl.repository.UserRepository;
import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用aop完成API请求时的认证和权限
 * made by Jason. Completed by mrruan
 * @author qkmc
 */
@Aspect
@Component
public class AuthenticationAspect {

    public final static Logger logger = LoggerFactory.getLogger(AuthenticationAspect.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    @Autowired
    UserRepository userRepository;

    /**
     * 怎么样寻找切入点
     * pointcut 是一种程序结构和规则，它用于选取join point并收集这些point的上下文信息。
     */
    @Pointcut(value = "@annotation(cn.edu.sicau.czczl.annotation.Authentication)")
    public void pointcut() {
    }

    /**
     * 与被注释方法正确返回之后执行
     * //* @param joinPoint 方法执行前的参数
     * //* @param result 方法返回值 后续观察，是否保存
     */
    @AfterReturning(value = "@annotation(cn.edu.sicau.czczl.annotation.Authentication)")
    public void after() {
        logger.info("refreshing token");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (token != null && redisService.readDataFromRedis(token) != null) {
            //通过token获取id值更新token有效期
            int userId = Integer.valueOf(redisService.readDataFromRedis(token));
            redisService.refreshToken(userId, token, 15);
            logger.info("refreshed token");
        } else {
            logger.info("not refreshed token, cannot find any token in redis: " + token);
        }
    }

    /**
     *
     * @param proceedingJoinPoint Proceedingjoinpoint 继承了 JoinPoint。是在JoinPoint的基础上暴露出 proceed 这个方法。proceed很重要，
     *                            这个是aop代理链执行的方法。
     *                            暴露出这个方法，就能支持 aop:around 这种切面
     * @param authentication 切点
     * @return
     */
    @Around("pointcut() && @annotation(authentication)")
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint, Authentication authentication) {
        boolean pass = authentication.pass();
        //要验证权限
        //通过拿到的role,我们可以知道能处理这个请求的角色是什么
        AuthAopConstant role = authentication.role();
        //如果是匿名者，直接放行，如果是用户，就需要用户的权限才行
        if (pass && !role.equals(AuthAopConstant.ANON)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (token == null) {
                //请求时没有token
                //权限错误，返回错误
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.success(Constant.PERMISSION_DENIED, "permission denied,forbidden access", null);
                return responseEntity;
            }
            AuthAopConstant realRole = authenticate(token, request.getRequestURI());
            if (realRole == role) {
                //权限正确，去访问吧
                try {
                    return proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    ResponseEntity responseEntity = new ResponseEntity();
                    responseEntity.success(Constant.AOP_SERVER_ERROR, "AOP_SERVER_ERROR", null);
                    logger.error("aop鉴权完成，但是程序执行出错");
                    return responseEntity;
                }
            } else {
                //权限错误，返回错误
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.success(Constant.AUTH_ROLE_ERROR, "permission denied,forbidden access", null);
                return responseEntity;
            }
        } else {
            //不验证权限
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.success(Constant.AOP_SERVER_ERROR, Constant.AOP_SERVER_ERROR_MESSAGE + ":" +throwable.getMessage(), null);
                return responseEntity;
            }
        }
    }

    /**
     * 这个方法用于判断该token所属的到底是谁(管理员？ 用户？ 匿名？)
     *
     * @param token
     * @return
     */
    private AuthAopConstant authenticate(String token, String path) {
        String userId = null;
        try {
            userId = redisService.readDataFromRedis(token);
        } catch (Exception e) {
            e.printStackTrace();
            //读取userId错误(最大的可能是请求的header中没有token)，直接返回匿名错误
            return AuthAopConstant.ANON;
        }
        if (userId == null) {
            //匿名的或者说用户过期的，没有找到session
            return AuthAopConstant.ANON;
        } else {
            Long id;
            try {
                id = Long.parseLong(userId);
            } catch (Exception e) {
                e.printStackTrace();
                //都抛出了异常了，这个userId是假的，直接匿名者
                return AuthAopConstant.ANON;
            }
            if (null != userService.findUserById(id)) {
                //用户
                logger.info("user: " + id + "; path: " + path);
                return AuthAopConstant.USER;
            } else {
                //没有发现它是用户，假的
                return AuthAopConstant.ANON;
            }
        }
    }
}