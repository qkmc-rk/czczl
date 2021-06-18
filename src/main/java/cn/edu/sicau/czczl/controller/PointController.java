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

import cn.edu.sicau.czczl.entity.JourneyPoint;
import cn.edu.sicau.czczl.entity.Material;
import cn.edu.sicau.czczl.service.JourneyPointService;
import cn.edu.sicau.czczl.service.MaterialService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-05 12:25
 * point 就是点,一个点是一个关卡,所有又可以说这是关卡相关的controller, 关卡信息, 材料信息, 试题信息等都由这个controller控制
 * 1. 返回所有关卡中的材料信息 /point/material get  匿名
 * 2. 返回某个关卡中的材料信息 /point/{pointid}/material   get  匿名
 * 3. 返回某个材料相关的十道试题 /material/{materialid}/question get 匿名
 * 4. 获取某个材料的支线材料, 若没有则返回空,有则返回 /material/{materialid}/branch
 * 5. 答题,将试题id和十道题的答案传到后台，后台处理，算正确率,生成相关记录存数据库等操作  /point/material/answer
 */
@RequestMapping("/point")
@CrossOrigin
@RestController
@Api(value = "长征点相关的操作由PointController来实现")
public class PointController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private JourneyPointService journeyPointService;
    //返回所有关卡的材料信息
    @GetMapping("/material")
    public ResponseEntity getAllMaterial(){
        ResponseEntity responseEntity = new ResponseEntity();
        List<Material> materialList=materialService.findAll();
//        responseEntity.setData(materialList);
        responseEntity.success(Constant.SUCCESS_CODE, "查询所有关卡中的材料信息成功", materialList);
        return responseEntity;
    }

    //    返回某个关卡的材料
    @GetMapping("/{pointId}/material")
    public ResponseEntity getMaterialByPointId(@PathVariable Long pointId){
        ResponseEntity responseEntity = new ResponseEntity();
        List<Material> materialList=materialService.findByJourneyId(pointId);
        //        responseEntity.success(Constant.SUCCESS_CODE, "绑定成功", user);
        responseEntity.success(Constant.SUCCESS_CODE, "查询某个管卡材料成功", materialList);
        return responseEntity;
    }

    //返回用户当前关卡信息
    @GetMapping("/{pointId}/step")
    public ResponseEntity getInfoByPointId(@PathVariable Long pointId){
        ResponseEntity responseEntity = new ResponseEntity();
        Optional<JourneyPoint> journeyPoint =journeyPointService.findByPointId(pointId);
        responseEntity.success(Constant.SUCCESS_CODE, "查询当前管卡信息成功", journeyPoint);
        return responseEntity;
    }
}
