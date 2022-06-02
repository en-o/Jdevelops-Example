package cn.tannn.hjpa;

import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jap.core.util.SpecificationUtil;
import cn.jdevelops.jap.core.util.criteria.Restrictions;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(rollbackFor = Exception.class)
class HjpaApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.or(Restrictions.like("userNo", "admin", true));
        jpaSelect.or(Restrictions.like("phone", "123", true));
        jpaSelect.or(Restrictions.like("name", "用户", true));
        jpaSelect.or(Restrictions.like("loginName", "user", true));
        jpaSelect.add(Restrictions.eq("address", "重庆", true));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
//        JPAUtilExpandCriteria<User> where1 = new JPAUtilExpandCriteria<>();
//        where1.add(Restrictions.eq("address", "重庆", true));
//        JPAUtilExpandCriteria<User> where2= new JPAUtilExpandCriteria<>();
//        where2.or(Restrictions.like("name", "用户", true));
//        where2.or(Restrictions.eq("loginPwd", "1231", true));
//        jpaSelect.and(where1);
//        userService.getJpaBasicsDao().findAll(jpaSelect);
    }


    @Test
    void contextLoads2() {
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.or(Restrictions.like(User::getUserNo, "admin", true));
        jpaSelect.or(Restrictions.like(User::getPhone, "123", true));
        jpaSelect.or(Restrictions.like(User::getName, "用户", true));
        jpaSelect.or(Restrictions.like(User::getLoginName, "user", true));
        jpaSelect.add(Restrictions.eq(User::getAddress, "重庆", true));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
//        JPAUtilExpandCriteria<User> where1 = new JPAUtilExpandCriteria<>();
//        where1.add(Restrictions.eq("address", "重庆", true));
//        JPAUtilExpandCriteria<User> where2= new JPAUtilExpandCriteria<>();
//        where2.or(Restrictions.like("name", "用户", true));
//        where2.or(Restrictions.eq("loginPwd", "1231", true));
//        jpaSelect.and(where1);
//        userService.getJpaBasicsDao().findAll(jpaSelect);
    }


    @Test
    void testSpecificationUtil() {
        /**
         *
         WHERE
         ( address LIKE '%重庆%' )
         AND (
         NAME = '用户'
         OR login_pwd = '123'
         OR phone = '123'
         OR user_no = '123')
         */
//        address().and(
//        name().or(loginPwd()).or(phone()).or(userNo())
//        )
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like("address", "重庆", true)
                .and(instance.eq("name", "用户", true)
                        .or(instance.eq("loginPwd", "123", true))
                        .or(instance.eq("phone", "123", true))
                        .or(instance.eq("userNo", "123", true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }


    @Test
    void testSpecificationUtil2() {
        /**
         *
         WHERE
         ( address LIKE '%重庆%' )
         AND (
         NAME = '用户'
         OR login_pwd = '123'
         OR phone = '123'
         OR user_no = '123')
         */
//        address().and(
//        name().or(loginPwd()).or(phone()).or(userNo())
//        )
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> and = instance.like(User::getAddress, "重庆", true)
                .and(instance.eq(User::getName, "用户", true)
                        .or(instance.eq(User::getLoginPwd, "123", true))
                        .or(instance.eq(User::getPhone, "123", true))
                        .or(instance.eq(User::getUserNo, "123", true))
                );
        userService.getJpaBasicsDao().findAll(and).forEach(System.out::println);
    }


    @Test
    void testAndOr1() {
        /**
         * WHERE
         * 	( address LIKE '重庆' )
         * 	AND (
         * 		NAME LIKE '%用户%'
         * 		OR login_pwd LIKE '123'
         * 	    OR phone LIKE '123'
         * 	    OR phone LIKE '123'
         * 	    )
         */
        userService.getJpaBasicsDao().findAll(
             address().and(name().or(loginPwd()).or(phone()).or(userNo()) )
        ).forEach(System.out::println);
    }


    @Test
    void testAndOr2() {
        userService.getJpaBasicsDao().findAll(
                address().and(
                        loginPwd().or(phone()).or(name())
                ).or(
                        loginPwd().and(
                                phone().or(name())
                        )
                )
        ).forEach(System.out::println);
    }
    @Test
    void test3() {
        Specification<User>  test = (root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction();;
        userService.getJpaBasicsDao().findAll(test).forEach(System.out::println);
    }

    public static Specification<User> name() {
        return (root, query, builder) -> builder.like(root.get("name"), "%用户%");
    }

    public static Specification<User> loginPwd() {
        return (root, query, builder) -> builder.like(root.get("loginPwd"), "123");
    }

    public static Specification<User> address() {
        return (root, query, builder) -> builder.like(root.get("address"), "重庆");
    }

    public static Specification<User> phone() {
        return (root, query, builder) -> builder.like(root.get("phone"), "123");
    }

    public static Specification<User> userNo() {
        return (root, query, builder) -> builder.like(root.get("phone"), "123");
    }


}
