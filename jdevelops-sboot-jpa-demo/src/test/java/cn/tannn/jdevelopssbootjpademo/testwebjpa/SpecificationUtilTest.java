package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
import cn.jdevelops.data.jap.util.JpaUtils;
import cn.jdevelops.data.jap.util.SpecificationUtil;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

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


    @Test
    void testSpecNotIn() {
        // from sys_user user0_ where user0_.name not in  (? , ?)from sys_user user0_ where user0_.name not in  (? , ?)
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        List<String> list = java.util.Arrays.asList("超级管理员", "111");
        Specification<User> not = instance.not("name", list , true);
        userService.getJpaBasicsDao().findAll(not).forEach(System.out::println);
    }

    @Test
    void testSpecBetween() {
        // from sys_user user0_ where user0_.login_pwd between ? and ?
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> between = instance.between("loginPwd", "1","1231" , true);
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }

    @Test
    void testSpecCustomTime() {
        // from sys_user user0_ where date_format(user0_.create_time, ?)=?
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> between = instance.specification((r,q,b)->
                b.equal(JpaUtils.functionTimeFormat(
                        SpecBuilderDateFun.DATE_FORMAT
                        ,r
                        ,b,"createTime"),"2021-11-17 11:08:38"));
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }

    @Test
    void testSpecCustomIn() {
        // from sys_user user0_ where user0_.name in (? , ?)
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> in = instance.specification((r,q,b)->{
            Path<User> path = r.get("name");
            return path.in("111", "超级管理员");
        });
        userService.getJpaBasicsDao().findAll(in).forEach(System.out::println);
    }
}
