package cn.tannn.jdevelops.demo.jpa.enhanceSpecification;

import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind2;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
import cn.tannn.jdevelops.jpa.utils.JpaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;

/**
 * 复杂查询核心方法的使用示例
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/17 上午9:39
 */
@SpringBootTest
public class EnhanceSpecificationTest {

    @Autowired
    private UserDao userDao;

    @Test
    void emptyBean() {
        // from sys_user user0_
        Specification<User> spec = EnhanceSpecification.beanWhere(null);
        System.out.println(userDao.findAll(spec));

        // from sys_user user0_
        spec = EnhanceSpecification.beanWhere(new UserFind());
        System.out.println(userDao.findAll(spec));
    }

    ;

    @Test
    void bean() {
        // from sys_user user0_ where user0_.address=?
        Specification<User> spec = EnhanceSpecification.beanWhere(new UserFind("重庆"));
        System.out.println(userDao.findAll(spec));
    }

    ;

    /**
     * 只看sql结构就行了
     */
    @Test
    void complexBean() {
        /*
        from sys_user user0_ where
        (
            ( user0_.user_no like ? or user0_.name in (? , ?) )
             and user0_.login_name=?
             and (user0_.user_icon like ?) or user0_.login_pwd=?
         )
         and user0_.address=?
         and date_format(user0_.create_time, ?)=?
         */
        UserComplexFind userComplexFind = new UserComplexFind();
        userComplexFind.setUserNo("weq");
        userComplexFind.setName(Arrays.asList("a", "b"));
        userComplexFind.setLoginName("ta");
        userComplexFind.setUserIcon("1");
        userComplexFind.setLoginPwd("23");
        userComplexFind.setPhone("123");
        userComplexFind.setAddress("1");
        userComplexFind.setCreateTime("2024-05-17 10:44:57");
        Specification<User> spec = EnhanceSpecification.beanWhere(userComplexFind);
        System.out.println(userDao.findAll(spec));


        /**
         * from sys_user user0_ where
         * (
         * 	user0_.user_no like ? or user0_.name in (? , ?)
         *     or user0_.login_name=? or user0_.user_icon like ?
         *  )
         *  and user0_.login_pwd=?
         *  and user0_.address=?
         *  and date_format(user0_.create_time, ?)=?
         */
        UserComplexFind2 userComplexFind2 = new UserComplexFind2();
        userComplexFind2.setUserNo("weq");
        userComplexFind2.setName(Arrays.asList("a", "b"));
        userComplexFind2.setLoginName("ta");
        userComplexFind2.setUserIcon("1");
        userComplexFind2.setLoginPwd("23");
        userComplexFind2.setPhone("123");
        userComplexFind2.setAddress("1");
        userComplexFind2.setCreateTime("2024-05-17 10:44:57");
        Specification<User> spec2 = EnhanceSpecification.beanWhere(userComplexFind2);
        System.out.println(userDao.findAll(spec2));
    }

    ;


    /**
     * 只看sql结构就行了
     */
    @Test
    void customComplex() {
        /*
        sys_user user0_
        WHERE
            user0_.NAME =?
            AND (
                user0_.address IN (?,
                ?))
            AND user0_.login_name =?
            AND (
            user0_.login_name LIKE ?)
            AND user0_.phone >= 1
            AND (
            user0_.phone LIKE ?)
            AND user0_.user_no =?
            OR date_format(
            user0_.create_time,
            ?)=?
         */
        System.out.println(userDao.findAll(EnhanceSpecification.where(e -> {
            e.eq(true, "name", "tan");
            e.in(true, "address", "1", "2");
            e.and(eand1 -> {
                eand1.eq(true, "loginName", "anonymous")
                        .or(eand1or -> eand1or.like(true, "loginName", "anonymous"));
                eand1.ge(true, "phone", 1);
                eand1.like(true, "phone", "2");
            });
            e.or(eor -> {
                eor.eq(true, "userNo", "anonymous");
            });
            if (false) {
                e.eq(true, "userIcon", "anonymous");
            }
            ;
            if (true) {
                e.eq(JpaUtils.functionTimeFormat(
                        SpecBuilderDateFun.DATE_FORMAT
                        , e.getRoot()
                        , e.getBuilder(), "createTime"), "2021-11-17 11:08:38");
            }
        })));
    }

    ;


    /**
     * 只看sql结构就行了
     */
    @Test
    void customComplexBean() {
        /*
	sys_user user0_
WHERE
	((
			user0_.user_no LIKE ?
			OR user0_.NAME IN (?,
			?))
		AND user0_.login_name =?
            AND (
            user0_.user_icon LIKE ?)
        OR user0_.login_pwd =?)
        AND user0_.address =?
        AND date_format(
            user0_.create_time,
        ?)=?
        AND user0_.NAME =?
        AND (
            user0_.address IN (?,
            ?))
        AND user0_.login_name =?
        AND (
        user0_.login_name LIKE ?)
        AND user0_.phone >= 1
        AND (
        user0_.phone LIKE ?)
        AND user0_.user_no =?
        OR date_format(
        user0_.create_time,
        ?)=?
         */
        UserComplexFind userComplexFind = new UserComplexFind();
        userComplexFind.setUserNo("weq");
        userComplexFind.setName(Arrays.asList("a", "b"));
        userComplexFind.setLoginName("ta");
        userComplexFind.setUserIcon("1");
        userComplexFind.setLoginPwd("23");
        userComplexFind.setPhone("123");
        userComplexFind.setAddress("1");
        userComplexFind.setCreateTime("2024-05-17 10:44:57");
        System.out.println(userDao.findAll(EnhanceSpecification.beanWhere(userComplexFind, e -> {
            e.eq(true, "name", "tan");
            e.in(true, "address", "1", "2");
            e.and(eand1 -> {
                eand1.eq(true, "loginName", "anonymous")
                        .or(eand1or -> eand1or.like(true, "loginName", "anonymous"));
                eand1.ge(true, "phone", 1);
                eand1.like(true, "phone", "2");
            });
            e.or(eor -> {
                eor.eq(true, "userNo", "anonymous");
            });
            if (false) {
                e.eq(true, "userIcon", "anonymous");
            }
            ;
            if (true) {
                e.eq(JpaUtils.functionTimeFormat(
                        SpecBuilderDateFun.DATE_FORMAT
                        , e.getRoot()
                        , e.getBuilder(), "createTime"), "2021-11-17 11:08:38");
            }
        })));
    }


    @Test
    void likeLongInt() {
        //  question: long int not like
        // error
//        userDao.findAll(EnhanceSpecification.where(e ->{
//            e.likes(true,"id",1+"");
//        })).forEach(System.out::println);

        // ok
        userDao.findAll(EnhanceSpecification.where(e ->{
            // 将 Long 类型字段转换为字符串进行 like 操作
            e.likes(e.getBuilder().function("str", String.class, e.getRoot().get("id")),1+"");
        })).forEach(System.out::println);


    }
}
