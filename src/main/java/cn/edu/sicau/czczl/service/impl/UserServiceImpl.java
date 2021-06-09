package cn.edu.sicau.czczl.service.impl;//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                           O\  =  /O
//                        ____/`---'\____
//                      .'  \\|     |//  `.
//                     /  \\|||  :  |||//  \
//                    /  _||||| -:- |||||-  \
//                    |   | \\\  -  /// |   |
//                    | \_|  ''\---/''  |   |
//                    \  .-\__  `-`  ___/-. /
//                  ___`. .'  /--.--\  `. . __
//               ."" '<  `.___\_<|>_/___.'  >'"".
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//              \  \ `-.   \_ __\ /__ _/   .-` /  /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                      Buddha Bless, No Bug !

import cn.edu.sicau.czczl.entity.User;
import cn.edu.sicau.czczl.repository.UserRepository;
import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.util.MD5Util;
import cn.edu.sicau.czczl.vo.WxServerResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-09 12:45
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${weixin.secret}")
    private String SECRET;
    @Value("${weixin.appid}")
    private String APPID;
    @Value("${weixin.api}")
    private String API;

    @Value("${weixin.api.code_start}")
    private Integer code_start;
    @Value("${weixin.api.code_end}")
    private Integer code_end;

    @Value("${weixin.api.secret_start}")
    private Integer secret_start;
    @Value("${weixin.api.secret_end}")
    private Integer secret_end;

    @Value("${weixin.api.appid_start}")
    private Integer appid_start;
    @Value("${weixin.api.appid_end}")
    private Integer appid_end;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Override
    public User findUserById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Integer login(String code) {
        WxServerResult wxServerResult = requestWxServerWithCode(code);
        Integer errCode = wxServerResult.getErrcode();
        if (null != errCode) {
            if (errCode.equals(Constant.WX_ERROR_CODE)){ return Constant.LOGIN_CODE_ERROR;}
            if (errCode.equals(Constant.WX_USED_CODE)){ return Constant.LOGIN_CODE_USED;}
        }
        //没有返回错误码则说明返回了session_key和openid
        User user = userRepository.findUserByOpenid(wxServerResult.getOpenid());
        if (null == user){
            // 没有用户信息, 说明是新用户
            User newUser = new User();
            newUser.setOpenid(wxServerResult.getOpenid());
            newUser.setScore(60);
            newUser.setAddScore(60);
            newUser.setStep(0);
            newUser.setIsBind(false);
            newUser.setIsDelete(false);
            user = userRepository.save(newUser);
            if (null == user){
                return Constant.LOGIN_NO_USER;
            }
        }
        //成功后还要缓存token
        String token = MD5Util.md5(new Date().toString());
        if (redisService.refreshToken((int)user.getId(), token, 15)) {
            return (int)user.getId();
        }
        return Constant.LOGIN_SERVER_ERROR;
    }

    /**
     * 封装了请求微信服务端的代码，传入code，请求后，将结果封装成为一个WxServerResult对象返回
     *
     * @param code
     * @return 微信服务器请求的结果
     */
    private WxServerResult requestWxServerWithCode(String code) {
        StringBuilder uri = new StringBuilder();
        uri.append(API);
        //替换CODE
        uri.replace(code_start, code_end, code);
        //替换SECRET
        uri.replace(secret_start, secret_end, SECRET);
        //替换APPID
        uri.replace(appid_start, appid_end, APPID);
        String uriStr = uri.toString();
        logger.info(uriStr);
        String strBody = getBody(restTemplate, uriStr);
        logger.info(strBody);
        //到数据库查询是否存在openId为某一值的记录
        ObjectMapper objectMapper = new ObjectMapper();
        WxServerResult wxServerResult = null;
        try {
            wxServerResult = objectMapper.readValue(strBody, WxServerResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxServerResult;
    }

    /**
     * 该方法发送简单的get请求，并获取相应数据
     * @param restTemplate
     * @param uri
     * @return 服务器响应数据
     */
    private String getBody(RestTemplate restTemplate, String uri) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            return responseEntity.getBody();
        }
        return null;
    }
}
