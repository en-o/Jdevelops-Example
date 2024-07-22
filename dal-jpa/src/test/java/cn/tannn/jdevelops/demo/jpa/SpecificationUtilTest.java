package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.utils.JpaUtils;
import cn.tannn.jdevelops.jpa.utils.SpecificationUtil;
import jakarta.persistence.criteria.Path;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 测试 {@link SpecificationUtil}
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 17:44
 */
@SpringBootTest
public class SpecificationUtilTest {

    @Autowired
    private UserService userService;

    /**
     <sql>
     from sys_userwhere (user0_.address like ?)
     and (
         (user0_.name=? or user0_.login_pwd=? and user0_.phone=? or user0_.user_no=?)
        and (user0_.phone=? or user0_.user_no=?)
        or user0_.phone=? or user0_.user_no=?
     )
     </sql>
     */
    @Test
    void testSpecificationUtil() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like(User::getAddress, "重庆", true,true)
                .and(instance.eq(User::getName, "用户", true,true)
                        .or(instance.eq(User::getLoginPwd, "123", true,true)
                                .and(instance.eq(User::getPhone, "123", true,true))
                                .or(instance.eq(User::getUserNo,"123", true,true))
                        )
                        .and(
                                instance.eq(User::getPhone, "123", true,true)
                                        .or(instance.eq(User::getUserNo, "123", true,true))
                        )
                        .or(instance.eq(User::getPhone, "123", true,true))
                        .or(instance.eq(User::getUserNo, "123", true,true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }

    /**
     <sql>
     from sys_user user0_ where (user0_.address like ?)
     and (
         (user0_.name=? or user0_.login_pwd=? and user0_.phone=? or user0_.user_no=?)
         and (user0_.phone=? or user0_.user_no=?)
         or user0_.phone=? or user0_.user_no=?
     )
     </sql>
     */

    @Test
    void testSpecificationUtil2() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like(User::getAddress, "重庆", true,true)
                .and(instance.eq(User::getName, "用户", true,true)
                        .or(instance.eq(User::getLoginPwd, "123", true,true)
                                .and(instance.eq(User::getPhone, "123", true,true))
                                .or(instance.eq(User::getUserNo,"123", true,true))
                        )
                        .and(
                                instance.eq(User::getPhone, "123", true,true)
                                        .or(instance.eq(User::getUserNo, "123", true,true))
                        )
                        .or(instance.eq(User::getPhone, "123", true,true))
                        .or(instance.eq(User::getUserNo, "123", true,true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }

    /**
     <sql>
     from sys_user user0_ where user0_.name not in  (? , ?)
     </sql>
     */
    @Test
    void testSpecNotIn() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        List<String> list = java.util.Arrays.asList("超级管理员", "111");
        Specification<User> not = instance.not("name", list , true,true);
        userService.getJpaBasicsDao().findAll(not).forEach(System.out::println);
    }


    /**
     <sql>
        from sys_user user0_ where user0_.login_pwd between ? and ?
     </sql>
     */
    @Test
    void testSpecBetween() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> between = instance.between("loginPwd", "1","1231" ,true, true);
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }


    /**
     <sql>
     from sys_user user0_ where date_format(user0_.create_time, ?)=?
     </sql>
     */
    @Test
    void testSpecCustomTime() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> between = instance.specification((r,q,b)->
                b.equal(JpaUtils.functionTimeFormat(
                        SpecBuilderDateFun.DATE_FORMAT
                        ,r
                        ,b,"createTime"),"2021-11-17 11:08:38"));
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }


    /**
     <sql>
     from sys_user user0_ where user0_.name in (? , ?)
     </sql>
     */
    @Test
    void testSpecCustomIn() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> in = instance.specification((r,q,b)->{
            Path<User> path = r.get("name");
            return path.in("111", "超级管理员");
        });
        userService.getJpaBasicsDao().findAll(in).forEach(System.out::println);
    }
}
