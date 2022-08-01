package cn.tannn.cachesjdbctemplate;

import cn.tannn.cachesjdbctemplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CachesJdbctemplateApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        userService.findAll().forEach(it -> System.out.printf(it.toString()));
    }
    @Test
    void findById() {
        System.out.println(userService.findById());
    }

    @Test
    void findById2() {
        System.out.println(userService.findById(2));
    }

    @Test
    void findById3() {
        System.out.println(userService.findById(3));
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("根据指定key更新1");
        userBO.setLoginName("user7");
        System.out.println(userService.findByBean(userBO));
    }

}
