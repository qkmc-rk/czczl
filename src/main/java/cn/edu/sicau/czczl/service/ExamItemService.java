package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.ExamItem;

import java.util.List;

public interface ExamItemService {

    List<ExamItem> findQuestionByMaterialId(Long materialid);
}
