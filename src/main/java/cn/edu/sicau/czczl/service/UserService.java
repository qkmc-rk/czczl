package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.User;

/**
 * @author qkmc
 */
public interface UserService {

    /**
     *  通过userId查询user的信息
     * @param userId 用户的id
     * @return 用户信息
     */
    User findUserById(Integer userId);

    /**
     *  登录接口, 传入小程序的code, 返回登录的状态
     * @param code 小程序code
     * @return 成功返回 userId，其它情况返回错误代码
     */
    Integer login(String code);
}
