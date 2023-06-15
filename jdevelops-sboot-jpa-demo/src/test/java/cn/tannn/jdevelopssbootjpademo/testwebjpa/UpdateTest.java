package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.controller.pojo.UpdateUser;
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
//@Transactional
//@Rollback(false)
public class UpdateTest {
    @Autowired
    private UserService userService;

    @Test
    void updateByBean() throws Exception {
        User user = new User();
        user.setUserNo("1466649744075108352");
        user.setName("测试更qq 新");
        userService.updateByBean(user,User::getUserNo);
    }
    @Test
    void updateByBeanById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setName("ID测试更新2");
        userService.updateByBean(user);
    }

    @Test
    void updateByBeanByIdTestIgnore() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUserIcon("测试被忽略");
        user.setName("ID测试更新aa");
        userService.updateByBean(user);
    }

    @Test
    void updateCustom() throws Exception {
        UpdateUser user = new UpdateUser();
        user.setUserNo("admin");
        user.setUserIcon("头像");
        user.setName("测试自定义更新aaa");
        userService.updateBeanTest(user,"userNo");
    }
}
