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

import cn.edu.sicau.czczl.vo.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class UserController {

    /**
     * 登录接口
     * @param code 小程序 wx.login()获取的code
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(String code){
        return null;
    }
}
