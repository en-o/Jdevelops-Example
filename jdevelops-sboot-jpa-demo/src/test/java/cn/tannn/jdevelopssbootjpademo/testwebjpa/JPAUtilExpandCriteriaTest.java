package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.hutool.core.util.ReflectUtil;
import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.core.JPAUtilExpandCriteria;
import cn.jdevelops.data.jap.core.criteria.Restrictions;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

/**
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 16:59
 */
@SpringBootTest
public class JPAUtilExpandCriteriaTest {

    @Autowired
    private UserService userService;

    @Test
    void testJPAUtilExpandCriteria(){
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.or(Restrictions.like("userNo", "admin", true));
        jpaSelect.or(Restrictions.like("loginName", "user", true));
        jpaSelect.add(Restrictions.eq("address", "重庆", true));
        jpaSelect.add(Restrictions.eq("userNo", "admin", true));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
    }

}
