package cn.edu.sicau.czczl.controller;

import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//                            _ooOoo_
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

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-07-04 17:59
 */
@RequestMapping("/constant")
@CrossOrigin
@RestController
@Api(value = "constant常量,前端可以参考")
public class ConstantController {

    @GetMapping("")
    @ApiOperation("前端可以参考这个文件对返回值进行判断")
    public ResponseEntity getConstant(){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.success("由于Constant类无法被序列化,所以请向后台索取最新的Constant.java文件");
        return responseEntity;
    }
}
