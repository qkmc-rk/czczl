package cn.edu.sicau.czczl.util;

public interface Constant {

    Integer SUCCESS_CODE = 1;
    //public final static is default
    Integer FAILURE_CODE = -1;

    String MSG_CLIENT_DATA_ERROR = "客户端数据错误";

    String MSG_SUCCESS = "响应成功";

    String MSG_SERVER_ERROR = "服务器故障";

    String MSG_INVALID_OPERATION = "非法操作";

    //登录时要用的CODE

    //成功登录
    Integer LOGIN_SUCCESS = 1;

    //未注册
    Integer LOGIN_NO_USER = 0;

    //传入code 有误登录失败
    Integer LOGIN_CODE_ERROR = -1;

    //传入的code已经被使用了
    Integer LOGIN_CODE_USED = -2;

    //服务器在处理一些操作的时候发生了异常
    Integer LOGIN_SERVER_ERROR = -3;

    Integer LOGIN_BLACK_USER = -4;

}