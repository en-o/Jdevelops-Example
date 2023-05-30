package cn.tannn.jdevelopssbootjpademo;

import cn.jdevelops.sboot.jpa.core.auditor.AuditorNameService;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdevelopsSbootJpaDemoApplicationTests {

    @Autowired
    private UserService userService;


    @Test
    void contextLoads() {
    }


    /**
     * 测试 AuditorNameService
     */
    @Test
    void auditorNameServiceTest() {
        User user = new User();
        user.setName("test");
        user.setPhone("123");
        user.setUserIcon("");
        user.setUserNo("test");
        user.setLoginName("test");
        user.setLoginPwd("123");
        user.setAddress("123");
        userService.saveByBean(user);

    }

}
