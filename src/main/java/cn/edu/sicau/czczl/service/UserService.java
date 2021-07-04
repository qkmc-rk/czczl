package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.User;
import cn.edu.sicau.czczl.vo.BindUserInfoVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;

/**
 * @author qkmc
 */
public interface UserService {

    /**
     *  通过userId查询user的信息
     * @param userId 用户的id
     * @return 用户信息
     */
    User findUserById(Long userId);

    /**
     *  登录接口, 传入小程序的code, 返回登录的状态
     * @param code 小程序code
     * @return 成功返回 userId，其它情况返回错误代码
     */
    Integer login(String code);

    /**
     * 拿到用户需要绑定的信息 然后绑定到数据库
     *  1 . 判断是否绑定, 若已绑定,返回已经绑定过
     *  2 . 判断是否存在空值, 若存在空值,则不给绑定.
     * @param token
     * @param bindUserInfoVO
     * @return
     */
    ResponseEntity bindUserInfo(String token, BindUserInfoVO bindUserInfoVO);

    /**
     * 根据user的id获取user的基本信息
     * @param token
     * @return 返回用户的基本信息
     */
    ResponseEntity getUserInfo(String token);

    /**
     * 根据user的id获取user的积分信息
     * @param token
     * @return 返回用户的基本信息
     */
    ResponseEntity getUserScore(String token);

    /**
     * 根据user的id或者user的关卡信息
     * @param token token
     * @return 关卡信息
     */
    ResponseEntity getUserStep(String token);

    /**
     * 保存一个用户
     * @param user
     * @return
     */
    User saveUser(User user);
}
