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

import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
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
 * 1. 登录接口 /login
 * 2. 绑定用户信息 /user/{userid}/bind
 * 3. 获取个人信息 /user/{userid}/info
 * 4. 返回个人信息中的积分,蓑衣,草鞋 等信息 /user/{userid}/score
 * 5. 返回用户玩游戏当前的关卡信息 step   /step/{userid}
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(value = "用户模块相关的操作由userController来实现")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
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
}
