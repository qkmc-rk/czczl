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
import cn.edu.sicau.czczl.vo.AnswerVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * 4. 获取某个关卡的支线材料, 若没有则返回空,有则返回 /material/{materialid}/branch   （这个接口由/point/{pointid}/material代替）
 * 5. 答题,将试题id和十道题的答案传到后台，后台处理，算正确率,生成相关记录存数据库等操作  /point/material/answer
 */
@RequestMapping("/point")
@CrossOrigin
@RestController
@Api(value = "长征点和点相关材料、问题、答案的操作由PointController来实现")
public class PointController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private JourneyPointService journeyPointService;

    /**
     *     //返回所有关卡的材料信息
     * @return
     */
    @GetMapping("/material")
    @ApiOperation("获取所有关卡的材料信息")
    public ResponseEntity getAllMaterial(){
        ResponseEntity responseEntity = new ResponseEntity();
        List<Material> materialList=materialService.findAll();
        responseEntity.success(Constant.SUCCESS_CODE, "查询所有关卡中的材料信息成功", materialList);
        return responseEntity;
    }

    /**
     * 返回某个关卡的材料
     * @param pointId
     * @return
     */
    @GetMapping("/{pointId}/material")
    @ApiOperation("返回某个关卡的材料信息")
    public ResponseEntity getMaterialByPointId(@ApiParam("某个关卡的ID") @PathVariable Long pointId){
        ResponseEntity responseEntity = new ResponseEntity();
        List<Material> materialList=materialService.findByJourneyId(pointId);
        responseEntity.success(Constant.SUCCESS_CODE, "查询某个关卡材料成功", materialList);
        return responseEntity;
    }

    /**
     * 用户关卡信息
     * 迁移到 /user /step
     *
     * 这个接口是返回某个point的详细信息
     *   返回用户当前关卡信息
     */
    @GetMapping("/{pointId}/step")
    @ApiOperation("返回某个point的详细信息")
    public ResponseEntity getInfoByPointId(@ApiParam("某个关卡的ID") @PathVariable Long pointId){
        ResponseEntity responseEntity = new ResponseEntity();
        Optional<JourneyPoint> journeyPoint =journeyPointService.findByPointId(pointId);
        responseEntity.success(Constant.SUCCESS_CODE, "查询当前关卡信息成功", journeyPoint);
        return responseEntity;
    }

    /**
     * 答题
     *
     */
    @PostMapping("/material/answer")
    @ApiOperation(value="回答问题的接口",notes = "后台根据materialid拿到10道试题，然后拿q{x}_answer和数据库的anwser对比知道是否是正确答案，10道题目判断完后，生成答题结果。\n" +
            "答题成功（正确率大于60%），生成答题记录存到数据库，用户表step加一，根据正确率加减积分；将答题结果返回前端。\n" +
            "答题失败：进行积分清算；然后直接返回答题失败信息给前端\n,注意，答题时间5分钟以内,超过就超时了，答题无效")
    public ResponseEntity answerMyQuestion(@RequestHeader String token, @ApiParam("回答问题时的请求参数") @RequestParam AnswerVO answerVO){
        return journeyPointService.answerMyQuestion(token, answerVO);
    }
}
