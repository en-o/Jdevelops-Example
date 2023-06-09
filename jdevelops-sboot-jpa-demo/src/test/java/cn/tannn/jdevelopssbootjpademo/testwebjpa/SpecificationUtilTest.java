package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.util.SpecificationUtil;
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
 * @date 2023-03-26 17:44
 */
@SpringBootTest
public class SpecificationUtilTest {

    @Autowired
    private UserService userService;

    @Test
    void testSpecificationUtil() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like(User::getAddress, "重庆", true)
                .and(instance.eq(User::getName, "用户", true)
                        .or(instance.eq(User::getLoginPwd, "123", true)
                                .and(instance.eq(User::getPhone, "123", true))
                                .or(instance.eq(User::getUserNo,"123", true))
                        )
                        .and(
                                instance.eq(User::getPhone, "123", true)
                                        .or(instance.eq(User::getUserNo, "123", true))
                        )
                        .or(instance.eq(User::getPhone, "123", true))
                        .or(instance.eq(User::getUserNo, "123", true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }



    @Test
    void testSpecificationUtil2() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like(User::getAddress, "重庆", true)
                .and(instance.eq(User::getName, "用户", true)
                        .or(instance.eq(User::getLoginPwd, "123", true)
                                .and(instance.eq(User::getPhone, "123", true))
                                .or(instance.eq(User::getUserNo,"123", true))
                        )
                        .and(
                                instance.eq(User::getPhone, "123", true)
                                        .or(instance.eq(User::getUserNo, "123", true))
                        )
                        .or(instance.eq(User::getPhone, "123", true))
                        .or(instance.eq(User::getUserNo, "123", true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }
}
