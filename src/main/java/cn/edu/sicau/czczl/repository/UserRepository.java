package cn.edu.sicau.czczl.repository;

import cn.edu.sicau.czczl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qkmc
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过openId查询用户
     * @param openId
     * @return
     */
    User findUserByOpenid(String openId);

}
