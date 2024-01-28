package cn.tan.authorizationserverusermanage;

import cn.tan.authorizationserverusermanage.dao.UserInfoDao;
import cn.tan.authorizationserverusermanage.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class AuthorizationServerUsermanageApplicationTests {

	@Autowired
	private UserInfoDao userInfoDao;
	@Resource
	private  PasswordEncoder passwordEncoder;
//	@Test
	void registerUser() {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginName("admin");
		userInfo.setPassword(passwordEncoder.encode("123"));
		userInfo.setNickname("authorization");
		userInfo.setNo(1000L);
		userInfo.setStatus(1);
		userInfo.setPhone("133");
		userInfo.setRoles("admin,dev,test");
		userInfoDao.save(userInfo);
	}

}
