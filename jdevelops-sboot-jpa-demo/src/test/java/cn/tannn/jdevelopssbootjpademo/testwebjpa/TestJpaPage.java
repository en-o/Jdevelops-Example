package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class TestJpaPage {

    @Autowired
    private UserService userService;

    @Test
    void testDeleteByUnique(){
        userService.deleteByUnique("user3", User::getLoginName);
    }


    @Test
    void testDeleteByUniques(){
        userService.deleteByUnique(Arrays.asList("user6","user7"),User::getLoginName);
    }
}
