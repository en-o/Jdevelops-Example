package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 删除
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 15:19
 */
@SpringBootTest
public class ListTest {
    @Autowired
    private UserService userService;

    /**
     * 查询 返回新的对象
     */
    @Test
    void findComplex(){
        User user = new User();
        user.setLoginPwd("123456");
        List<User> complex = userService.findComplex(user);
        complex.forEach(System.out::println);
    }

    /**
     * 查询 返回新的对象
     */
    @Test
    void findBeanList(){
        List<User> complex = userService.findBeanList(User::getLoginPwd,"123456");
        complex.forEach(System.out::println);
    }
}
