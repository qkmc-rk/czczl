package cn.edu.sicau.czczl.service.impl;

import cn.edu.sicau.czczl.entity.*;
import cn.edu.sicau.czczl.repository.JourneyPointRepository;
import cn.edu.sicau.czczl.service.ExamItemService;
import cn.edu.sicau.czczl.service.JourneyPointService;
import cn.edu.sicau.czczl.service.QuestionRecordService;
import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.vo.AnswerVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author qkmc/wang
 */
@Service
public class JourneyPointServiceImpl implements JourneyPointService {

    private final static Integer LIMIT = 5;

    @Autowired
    private JourneyPointRepository journeyPointRepository;

    @Autowired
    RedisService redisService;

    @Autowired
    ExamItemService examItemService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionRecordService questionRecordService;

    @Override
    public Optional<JourneyPoint> findByPointId(Long pointId) {
        Optional<JourneyPoint> journeyPoint = journeyPointRepository.findById(pointId);
        return journeyPoint;
    }

    /**
     * 后台根据materialid拿到10道试题，然后拿q{x}_answer和数据库的anwser对比知道是否是正确答案，10道题目判断完后，生成答题结果。\n" +
     *             "答题成功（正确率大于60%），生成答题记录存到数据库，用户表step加一，根据正确率加减积分；将答题结果返回前端。\n" +
     *             "答题失败：进行积分清算；然后直接返回答题失败信息给前端\n,注意，答题时间5分钟以内,超过就超时了，答题无效
     */
    @Transactional
    @Override
    public ResponseEntity answerMyQuestion(String token, AnswerVO answerVO) {
        ResponseEntity responseEntity = new ResponseEntity();
        // 第一步判断时间是否超时
        if (answerVO.getTimeSpent() > LIMIT){
            responseEntity.error(Constant.FAILURE_CODE, "答题时间超时", answerVO);
            return responseEntity;
        }
        // 第二步准备工作完成, 拿到用户信息, 拿到数据库的试题信息
        Long userId = Long.parseLong(redisService.readDataFromRedis(token));
        User user = userService.findUserById(userId);
        assert user != null;
        List<ExamItem> list = examItemService.findQuestionByMaterialId(answerVO.getMaterialId());

        // 判断用户的10道试题的回答情况
        // stemType-
        // 根据qxid判断哪道试题的答案
        List<Boolean> answers = answerQuestions(list, answerVO);
        Integer timeScore = getTimeScore(answerVO.getTimeSpent());
        //计算总分数
        Integer score = timeScore;
        // 用于记录正确答题的个数
        Integer flag = 0;
        for (Boolean rs : answers) {
            if (rs) { score += Constant.CORRECT_SCORE; flag += 1;}
        }
        if (flag < Constant.PASS_RATE){
            //不及格 积分清算，然后返回前端
            Integer decreaseScore = (10 - flag) * Constant.WRONG_SCORE;
            user.setScore(user.getScore() - decreaseScore);
            user.setSubScore(user.getSubScore() + decreaseScore);
            userService.saveUser(user);
            responseEntity.success(Constant.SUCCESS_CODE, "正确率太低,闯关失败,正确率：" + flag * 10 + "%,增加积分" + score, user);
            return responseEntity;
        }
        // 闯关成功, STEP+1, 分数加加, 生成答题记录,返回结果
        user.setStep(user.getStep() + 1);
        user.setScore(user.getScore() + score);
        user.setAddScore(user.getAddScore() + score);
        QuestionRecord questionRecord = generateQuestionRecord(userId,answerVO.getPointId(),answers);
        userService.saveUser(user);
        questionRecordService.saveQuestionRecord(questionRecord);
        responseEntity.success(Constant.SUCCESS_CODE, "正确率合格,闯关成功,正确率：" + flag * 10 + "%,增加积分" + score, user);
        return responseEntity;
    }

    /**
     * 回答问题
     * @param examItems
     * @param answerVO
     * @return
     */
    private List<Boolean> answerQuestions(List<ExamItem> examItems, AnswerVO answerVO){
        // 10道试题 循环10次
        List<Boolean> answers = new ArrayList<>();
        for(int i = 0; i < answerVO.getAnswers().size(); i++){
            Long questionId = answerVO.getQuestionIds().get(i);
            String answer = answerVO.getAnswers().get(i);
            //试题类型(选择判断填空123)
            ExamItem examItem = getExamItem(examItems, questionId);
            assert examItem != null;
            Boolean answerX = answerXQuestion(examItem, answer);
            answers.add(answerX);
        }
        return answers;
    }


    /**
     * 回答第x个问题
     * @param examItem
     * @param answer
     * @return
     */
    private Boolean answerXQuestion(ExamItem examItem, String answer){
        Integer stemType = examItem.getStemType();
        if (stemType == Constant.STEM_TYPE_CHOICE){
            return answerChoiceQuestion(examItem, answer);
        }
        if (stemType == Constant.STEM_TYPE_JUDGE){
            return answerJudgeQuestion(examItem, answer);
        }
        if (stemType == Constant.STEM_TYPE_FILL){
            return answerFillQuestion(examItem, answer);
        }
        return null;
    }

    /**
     * 回答选择题
     * @param examItem
     * @param answer
     * @return
     */
    private Boolean answerChoiceQuestion(ExamItem examItem, String answer){
        if (examItem.getChoiceAnswer().equals(answer)){
            return true;
        }
        return false;
    }

    /**
     * 回答判断题目
     * @param examItem
     * @param answer
     * @return
     */
    private Boolean answerJudgeQuestion(ExamItem examItem, String answer){
        if (examItem.getJudgeAnswer().equals(answer)){
            return true;
        }
        return false;
    }

    /**
     * 回答填空题
     * @param examItem
     * @param answer
     * @return
     */
    private Boolean answerFillQuestion(ExamItem examItem, String answer){
        if (examItem.getFillAnswer().equals(answer)){
            return true;
        }
        return false;
    }

    /**
     * 拿到相关试题
     * @param examItems
     * @param id
     * @return
     */
    private ExamItem getExamItem(List<ExamItem> examItems, Long id){
        for (ExamItem e :
                examItems) {
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    /**
     * 根据消耗的时间计算答题时间的分数
     *
     * @param time
     * @return
     */
    private Integer getTimeScore(Integer time) {
        if (time < Constant.TIME_DIVIDE1 && time > Constant.TIME_START) {
            return 3;
        } else if (time < Constant.TIME_DIVIDE2) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * 根据已知的信息生成QuestionRecord
     * @return QuestionRecord
     */
    private QuestionRecord generateQuestionRecord(Long userId, Long pointId, List<Boolean> answers){
        QuestionRecord questionRecord = new QuestionRecord();
        questionRecord.setUserId(userId);
        questionRecord.setPointId(pointId);
        questionRecord.setQ1(answers.get(0));
        questionRecord.setQ2(answers.get(1));
        questionRecord.setQ3(answers.get(2));
        questionRecord.setQ4(answers.get(3));
        questionRecord.setQ5(answers.get(4));
        questionRecord.setQ6(answers.get(5));
        questionRecord.setQ7(answers.get(6));
        questionRecord.setQ8(answers.get(7));
        questionRecord.setQ9(answers.get(8));
        questionRecord.setQ10(answers.get(9));
        questionRecord.setIsAnswer(true);
        return questionRecord;
    }
}
