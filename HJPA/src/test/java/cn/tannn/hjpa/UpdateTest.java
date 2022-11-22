package cn.tannn.hjpa;

import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试更行
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-06 10:26
 */
@SpringBootTest
//@Transactional(rollbackFor = Exception.class)
public class UpdateTest {

    @Autowired
    private UserService userService;


    @Test
    public void  updateByBean() throws Exception {
        User user = new User();
        user.setName("超级管理员6");
        user.setLoginPwd("updateByBean1231");
        user.setPhone("1312");
        System.out.println(userService.updateByBean(user, User::getPhone));
    }


    @Test
    public void  updateByBeanForBean() throws Exception {
        User user = new User();
        user.setName("超级管理员6");
        user.setLoginPwd("updateByBean2");
        user.setPhone("1312");
        System.out.println(userService.updateByBeanForBean(user, User::getPhone));
    }
}
