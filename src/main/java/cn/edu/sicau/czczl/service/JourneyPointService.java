package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.JourneyPoint;
import cn.edu.sicau.czczl.vo.AnswerVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author qkmc/wang
 */
@Service
public interface JourneyPointService {

    /**
     * 找点信息
     * @param pointId
     * @return
     */
    Optional<JourneyPoint> findByPointId(Long pointId);

    /**
     * 回答问题
     * @param answerVO 回答问题所需要的参数都写这里面了
     * @param token
     * @return
     */
    ResponseEntity answerMyQuestion(String token, AnswerVO answerVO);
}
