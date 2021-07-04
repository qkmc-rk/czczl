package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.QuestionRecord;

/**
 * @author qkmc
 */
public interface QuestionRecordService {

    /**
     * 保存一个questionRecord
     * @param questionRecord
     * @return
     */
    QuestionRecord saveQuestionRecord(QuestionRecord questionRecord);
}
