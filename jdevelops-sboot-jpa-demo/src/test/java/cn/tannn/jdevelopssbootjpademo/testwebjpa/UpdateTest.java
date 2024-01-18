package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.controller.pojo.UpdateUser;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


    // 如果没有数据，执行resources/init/init.sql

    @Test
    void updateByBean() throws Exception {
        User user = new User();
        user.setUserNo("1466649744075108352");
        user.setName("测试更qq 新2");

        // 测试传入无用，内部过滤了 User 中继承的四个审计字段，更新时间会自动更新
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.parse("2024-01-18 17:08:53", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
