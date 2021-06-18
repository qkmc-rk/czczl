package cn.edu.sicau.czczl.controller;


import cn.edu.sicau.czczl.entity.ExamItem;
import cn.edu.sicau.czczl.service.ExamItemService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 3. 返回某个材料相关的十道试题 /material/{materialid}/question get 匿名(路径问题)
* 4. 获取某个材料的支线材料, 若没有则返回空,有则返回 /material/{materialid}/branch
*/

@RequestMapping("/material")
@CrossOrigin
@RestController
@Api(value = "材料相关的操作由MaterialController来实现")
public class MaterialController {
    @Autowired
    private ExamItemService examItemService;

    //返回某个材料相关的试题
    @GetMapping("/{materialid}/question")
    public ResponseEntity getQuestionByMaterialIdQuestion(@PathVariable Long materialid){
        ResponseEntity responseEntity = new ResponseEntity();
        List<ExamItem> examItemList=examItemService.findQuestionByMaterialId(materialid);
        responseEntity.success(Constant.SUCCESS_CODE, "查询某个材料相关试题成功", examItemList);
        return responseEntity;
    }

    //获取直线材料，没有返回为空
}
