package cn.edu.sicau.czczl;

import cn.edu.sicau.czczl.entity.User;
import cn.edu.sicau.czczl.util.FieldValidator;
import cn.edu.sicau.czczl.vo.BindUserInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CzczlApplicationTests {

	@Test
	void contextLoads() {
		FieldValidator f = new FieldValidator();
		BindUserInfoVO bindUserInfoVO = new BindUserInfoVO();
		bindUserInfoVO.setName("ruan");
		bindUserInfoVO.setClazz("ss");
		bindUserInfoVO.setCollege("ss");
		bindUserInfoVO.setGrade("");
		bindUserInfoVO.setIdcard("ss");
		bindUserInfoVO.setMajor("ss");
		bindUserInfoVO.setStuno("ss");
		bindUserInfoVO.setUniversity("ss");
		System.out.println(f.hasNullOrEmptyField(bindUserInfoVO));

		User user = new User();
		user = (User)f.updateAWithB(user, bindUserInfoVO);
		System.out.println(user);
	}

}
