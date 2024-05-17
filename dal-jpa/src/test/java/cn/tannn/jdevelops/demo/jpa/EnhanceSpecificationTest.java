package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind2;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
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
    void emptyBean(){
        // from sys_user user0_
        Specification<User> spec = EnhanceSpecification.beanWhere(null);
        System.out.println(userDao.findAll(spec));

        // from sys_user user0_
        spec = EnhanceSpecification.beanWhere(new UserFind());
        System.out.println(userDao.findAll(spec));
    };

    @Test
    void bean(){
        // from sys_user user0_ where user0_.address=?
        Specification<User> spec = EnhanceSpecification.beanWhere(new UserFind("重庆"));
        System.out.println(userDao.findAll(spec));
    };

    /**
     * 只看sql结构就行了
     */
    @Test
    void complexBean(){
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
        userComplexFind.setName(Arrays.asList("a","b"));
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
        userComplexFind2.setName(Arrays.asList("a","b"));
        userComplexFind2.setLoginName("ta");
        userComplexFind2.setUserIcon("1");
        userComplexFind2.setLoginPwd("23");
        userComplexFind2.setPhone("123");
        userComplexFind2.setAddress("1");
        userComplexFind2.setCreateTime("2024-05-17 10:44:57");
        Specification<User> spec2 = EnhanceSpecification.beanWhere(userComplexFind2);
        System.out.println(userDao.findAll(spec2));
    };

}
