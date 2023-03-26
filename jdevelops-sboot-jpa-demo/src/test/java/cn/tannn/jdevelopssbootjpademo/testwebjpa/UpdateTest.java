package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 更新
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 15:19
 */
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserService userService;

    @Test
    void updateByBean() throws Exception {
        User user = new User();
        user.setUserNo("123");
        user.setName("测试更qq 新");
        userService.updateByBean(user,User::getUserNo);
    }
    @Test
    void updateByBeanById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setName("ID测试更新");
        userService.updateByBean(user);
    }
}
