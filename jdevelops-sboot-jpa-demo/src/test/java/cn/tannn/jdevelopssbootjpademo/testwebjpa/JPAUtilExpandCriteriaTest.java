package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.core.JPAUtilExpandCriteria;
import cn.jdevelops.data.jap.core.criteria.Restrictions;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
import cn.jdevelops.data.jap.util.JpaUtils;
import cn.tannn.jdevelopssbootjpademo.dto.UserFindDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.pg.UserPgsql;
import cn.tannn.jdevelopssbootjpademo.pg.UserPgService;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 16:59
 */
@SpringBootTest
public class JPAUtilExpandCriteriaTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPgService userPgService;

    @Test
    void testJPAUtilExpandCriteria() {
        // from sys_user user0_ where user0_.address=? and user0_.user_no=? and (user0_.user_no like ? or user0_.login_name like ?)
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.or(Restrictions.like("userNo", "admin", true));
        jpaSelect.or(Restrictions.like("loginName", "user", true));
        jpaSelect.add(Restrictions.eq("address", "重庆", true));
        jpaSelect.add(Restrictions.eq("userNo", "admin", true));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
    }


    @Test
    void testTime() {

        // from sys_user user0_ where date_format(user0_.create_time, "%Y-%m-%d %T")=?
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.add(Restrictions.eq("createTime", "2021-11-17 11:08:38", true, SpecBuilderDateFun.DATE_FORMAT));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);

        // from sys_user user0_ where date_format(user0_.create_time, "%Y")=?
        jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.add(Restrictions.eq("createTime", "2021", true, SpecBuilderDateFun.DATE_FORMAT_YEAR));
        userService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);


        // from sys_user_pg userpgsql0_ where to_char(userpgsql0_.create_time, ?)=?
//        JPAUtilExpandCriteria<UserPgsql> jpaSelect = new JPAUtilExpandCriteria<>();
//        jpaSelect = new JPAUtilExpandCriteria<>();
//        jpaSelect.add(Restrictions.eq("createTime", "2021-11-17 11:08:38", true, SpecBuilderDateFun.TO_CHAR));
//        userPgService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
//
//        jpaSelect = new JPAUtilExpandCriteria<>();
//        jpaSelect.add(Restrictions.eq("createTime", "2021", true, SpecBuilderDateFun.TO_CHAR_YEAR));
//        userPgService.getJpaBasicsDao().findAll(jpaSelect).forEach(System.out::println);
    }


    @Test
    void testBean1() {
        // from sys_user user0_ where user0_.phone=? and (user0_.user_no like ? or user0_.name like ? or user0_.address like ? or user0_.user_icon like ?)
        UserFindDTO user = new UserFindDTO();
        user.setUserNo("admin");
        user.setAddress("重庆");
        user.setName("用户1");
        user.setUserIcon("");
        user.setPhone("123");
        userService.findComplex(user).forEach(System.out::println);
    }


    @Test
    void testBean2() {
        // from sys_user user0_ where user0_.phone=? and (user0_.user_no like ? or user0_.name like ? or user0_.address like ? or user0_.user_icon like ?)
        UserFindDTO user = new UserFindDTO();
        user.setUserNo("admin");
        user.setAddress("重庆");
        user.setName("用户1");
        user.setUserIcon("");
        user.setPhone("123");
        JPAUtilExpandCriteria<User> selectRegionBean = JpaUtils.getSelectBean2(user);
        userService.getJpaBasicsDao().findAll(selectRegionBean).forEach(System.out::println);
    }


    @Test
    void testBean3() {
        // from sys_user user0_ where user0_.phone=? and date_format(user0_.create_time, ?)=? and (user0_.user_no like ? or user0_.name like ? or user0_.address like ? or user0_.user_icon like ?)
        UserFindDTO user = new UserFindDTO();
        user.setUserNo("admin");
        user.setAddress("重庆");
        user.setName("用户1");
        user.setUserIcon("");
        user.setPhone("123");
        user.setCreateTime("2021-12-03 13:48:14");
        JPAUtilExpandCriteria<User> selectRegionBean = JpaUtils.getSelectBean2(user);
        userService.getJpaBasicsDao().findAll(selectRegionBean).forEach(System.out::println);

    }
}
