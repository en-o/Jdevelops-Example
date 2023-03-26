package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.core.Specifications;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

/**
 * 复杂查询
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 18:06
 */
@SpringBootTest
public class SpecificationsTest {
    @Autowired
    private UserService userService;

    @Test
    void testSpec() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.likes(User::getName, "用户");
            e.or(e2 -> {
                        e2.eq(User::getPhone, "123");
                        e2.likes(User::getAddress, "重");
                    }
            );
            e.isNull(User::getUserIcon);
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }
}
