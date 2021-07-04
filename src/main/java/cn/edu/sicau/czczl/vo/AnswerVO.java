package cn.edu.sicau.czczl.vo;//                            _ooOoo_
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-28 14:49
 */
@ApiModel("包含耗费的时间timeSpent, 长征点的ID pointId, MaterialId, 每个问题的答案")
public class AnswerVO {

    @ApiModelProperty("耗费的时间")
    private Integer timeSpent;
    @ApiModelProperty("长征点的id")
    private Long pointId;
    @ApiModelProperty("材料的ID, 根据材料ID可以拿到10道题目")
    private Long MaterialId;

    @ApiModelProperty("答案, 第一题到最后一道题, 依次按照顺序排列")
    private List<String> answers;

//    @ApiModelProperty("问题1答案")
//    private String q1Answer;
//    @ApiModelProperty("问题2答案")
//    private String q2Answer;
//    @ApiModelProperty("问题3答案")
//    private String q3Answer;
//    @ApiModelProperty("问题4答案")
//    private String q4Answer;
//    @ApiModelProperty("问题5答案")
//    private String q5Answer;
//    @ApiModelProperty("问题6答案")
//    private String q6Answer;
//    @ApiModelProperty("问题7答案")
//    private String q7Answer;
//    @ApiModelProperty("问题8答案")
//    private String q8Answer;
//    @ApiModelProperty("问题9答案")
//    private String q9Answer;
//    @ApiModelProperty("问题10答案")
//    private String q10Answer;

    @ApiModelProperty("问题的Id,根据Id后台可以从数据库拿到具体的数据")
    private List<Long> questionIds;

//    @ApiModelProperty("问题1Id")
//    private Long q1Id;
//    @ApiModelProperty("问题2Id")
//    private Long q2Id;
//    @ApiModelProperty("问题3Id")
//    private Long q3Id;
//    @ApiModelProperty("问题4Id")
//    private Long q4Id;
//    @ApiModelProperty("问题5Id")
//    private Long q5Id;
//    @ApiModelProperty("问题6Id")
//    private Long q6Id;
//    @ApiModelProperty("问题7Id")
//    private Long q7Id;
//    @ApiModelProperty("问题8Id")
//    private Long q8Id;
//    @ApiModelProperty("问题9Id")
//    private Long q9Id;
//    @ApiModelProperty("问题10Id")
//    private Long q10Id;

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(Long materialId) {
        MaterialId = materialId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    @Override
    public String toString() {
        return "AnswerVO{" +
                "timeSpent=" + timeSpent +
                ", pointId=" + pointId +
                ", MaterialId=" + MaterialId +
                ", answers=" + answers +
                ", questionIds=" + questionIds +
                '}';
    }
}
