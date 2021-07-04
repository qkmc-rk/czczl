package cn.edu.sicau.czczl.util;

/**
 * @author qkmc
 */
public interface Constant {

    Integer SUCCESS_CODE = 1;
    /**
     * public final static is default
     */
    Integer FAILURE_CODE = -1;

    String MSG_CLIENT_DATA_ERROR = "客户端数据错误";

    String MSG_SUCCESS = "响应成功";

    String MSG_SERVER_ERROR = "服务器故障";

    String MSG_INVALID_OPERATION = "非法操作";

    //登录时要用的CODE

    /**
     * 成功登录
     */
    Integer LOGIN_SUCCESS = 1;

    /**
     * 未注册
     */
    Integer LOGIN_NO_USER = 0;

    /**
     * 传入code 有误登录失败
     */
    Integer LOGIN_CODE_ERROR = -1;

    /**
     * 传入的code已经被使用了
     */
    Integer LOGIN_CODE_USED = -2;

    /**
     * 服务器在处理一些操作的时候发生了异常
     */
    Integer LOGIN_SERVER_ERROR = -3;

    Integer LOGIN_BLACK_USER = -4;

    Integer AOP_SERVER_ERROR = -99;
    String AOP_SERVER_ERROR_MESSAGE = "AOP SERVER ERROR";

    Integer PERMISSION_DENIED = -10;
    Integer AUTH_ROLE_ERROR = -11;

    /**
     * 登录时微信返回的code
     * 已经被使用的code
     */
    Integer WX_USED_CODE = 40163;
    Integer WX_ERROR_CODE = 40029;

    Integer FORM_NULL_EMPTY_VALUE = -100;

    /**
     * 答题的时间界限和分数
     */
    Integer ANSWER_SCORE = 1;
    /**
     * 根据timeSocre计算时间分数
     */
    Integer TIME1_SCORE = 2;
    Integer TIME2_SCORE = 1;
    Integer TIME3_SCORE = 0;

    /**
     * 时间分界线
     */
    Integer TIME_START = 0;
    Integer TIME_DIVIDE1 = 2;
    Integer TIME_DIVIDE2 = 4;
    Integer TIME_END = 5;
    /**
     * 最低正确率
     */
    Integer PASS_RATE = 6;

    /**
     * 答错一道题扣这么多分数
     */
    Integer WRONG_SCORE = 1;
    /**
     * 答对一道题加这么多分数
     */
    Integer CORRECT_SCORE = 1;

    /**
     * 题型
     */
    Integer STEM_TYPE_CHOICE = 1;
    Integer STEM_TYPE_JUDGE = 2;
    Integer STEM_TYPE_FILL = 3;




}
