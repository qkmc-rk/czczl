package cn.edu.sicau.czczl.controller;//                            _ooOoo_
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

import cn.edu.sicau.czczl.annotation.Authentication;
import cn.edu.sicau.czczl.annotation.constant.AuthAopConstant;
import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.BindUserInfoVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-05 12:22
 * 1. 登录接口 /login post
 * 2. 绑定用户信息 /user/bind post
 * 3. 获取个人信息 /user/info get
 * 4. 返回个人信息中的积分,蓑衣,草鞋 等信息 /user/score
 * 5. 返回用户玩游戏当前的关卡信息 step   /user/step
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(value = "用户模块相关的操作由userController来实现")
public class UserController {

    UserService userService;

    RedisService redisService;

    /**
     * 登录接口
     * @param code 小程序 wx.login()获取的code
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "[匿名]传入小程序的code,进行登录,登录要做成restful API，必须跟资源联系起来，登录对应操作的是token")
    public ResponseEntity login(@ApiParam(value = "小程序code,必须传入") @RequestParam String code){
        Integer loginResult = userService.login(code);
        ResponseEntity responseEntity = new ResponseEntity();
        if (loginResult.equals(Constant.LOGIN_NO_USER)){
            responseEntity.error(Constant.LOGIN_NO_USER, "LOGIN_NO_USER, login failed, cannot save user into db", null);
        } else if (loginResult.equals(Constant.LOGIN_CODE_ERROR)){
            responseEntity.error(Constant.LOGIN_CODE_ERROR, "LOGIN_CODE_ERROR", null);
        } else if (loginResult.equals(Constant.LOGIN_CODE_USED)){
            responseEntity.error(Constant.LOGIN_CODE_USED, "LOGIN_CODE_USED", null);
        } else if (loginResult.equals(Constant.LOGIN_SERVER_ERROR)){
            responseEntity.error(Constant.LOGIN_SERVER_ERROR, "LOGIN_SERVER_ERROR", null);
        } else {
            String token = redisService.readDataFromRedis("token" + loginResult.intValue());
            responseEntity.success(Constant.LOGIN_SUCCESS, "login success", token);
        }
        return responseEntity;
    }

    /**
     *  拿到用户需要绑定的信息 然后绑定到数据库
     *  1 . 判断是否绑定, 若已绑定,返回已经绑定过
     *  2 . 判断是否存在空值, 若存在空值,则不给绑定.
     * @param token
     * @param bindUserInfoVO
     * @return
     */
    @PostMapping("/bind")
    @Authentication(role = AuthAopConstant.USER)
    @ApiOperation(value = "登录接口", notes = "[匿名]传入小程序的code,进行登录,登录要做成restful API，必须跟资源联系起来，登录对应操作的是token")
    public ResponseEntity bindUserInfo(@ApiParam("每个参数必填") @RequestBody BindUserInfoVO bindUserInfoVO, @RequestHeader String token){
        return userService.bindUserInfo(token, bindUserInfoVO);
    }

    /**
     * 获取用户的基本信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    @Authentication(role = AuthAopConstant.USER)
    @ApiOperation("获取用户的基本信息, 脱敏，没有openid等信息")
    public ResponseEntity getUserInfo(@ApiParam("token") @RequestHeader String token){
        return userService.getUserInfo(token);
    }
    /**
     * /user/{userid}/score
     **/
    /**
     * 获取用户的积分, 蓑衣, 草鞋等参数的值
     * @param token
     * @return
     */
    @GetMapping("/score")
    @Authentication(role = AuthAopConstant.USER)
    @ApiOperation("获取用户的积分, 蓑衣, 草鞋等参数的值")
    public ResponseEntity getScoreEtc(@RequestHeader String token){
        ResponseEntity responseEntity = userService.getUserScore(token);
        return responseEntity;
    }

    /**
     * 获取用户当前的关卡信息
     * @param token
     * @return
     */
    @GetMapping("/step")
    @Authentication(role = AuthAopConstant.USER)
    @ApiOperation("获取用户当前的关卡信息")
    public ResponseEntity getStep(@RequestHeader String token){
        return userService.getUserStep(token);
    }


    //--------------------------------------------------------------
    //--------------------- 其它无关紧要的方法些 -----------------------
    //--------------------------------------------------------------

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
