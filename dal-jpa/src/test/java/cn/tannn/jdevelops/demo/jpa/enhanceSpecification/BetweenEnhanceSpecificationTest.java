package cn.tannn.jdevelops.demo.jpa.enhanceSpecification;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.enhanceSpecification.pojo.TestLoginPwdBetween;
import cn.tannn.jdevelops.demo.jpa.enhanceSpecification.pojo.TestTimeBetween;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind;
import cn.tannn.jdevelops.demo.jpa.lists.dto.UserComplexFind2;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
import cn.tannn.jdevelops.jpa.utils.JpaUtils;
import lombok.Data;
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
public class BetweenEnhanceSpecificationTest {

    @Autowired
    private UserDao userDao;


    @Test
    void betweenTime() {
        // from sys_user user0_ where date_format(user0_.create_time, '%Y-%m-%d') between '2024-01-18' and '2024-03-18'
        TestTimeBetween test = new TestTimeBetween();
        test.setCreateTime("2024-01-18,2024-03-18");
        Specification<User> spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("传值: "+x));

        // 不传值，就不会带这个条件，从而查询所有
        test = new TestTimeBetween();
        spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("不传值: "+x));


        // 传空值，就不会带这个条件，从而查询所有
        test = new TestTimeBetween();
        test.setCreateTime("");
        spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("传空值: "+x));
    }

    @Test
    void betweenLoginPwd() {
        // sys_user user0_ where user0_.login_pwd between '1231' and '1234'
        TestLoginPwdBetween test = new TestLoginPwdBetween();
        test.setLoginPwd("1231,1234");
        Specification<User> spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("传值: "+x));

        // 不传值，就不会带这个条件，从而查询所有
        test = new TestLoginPwdBetween();
        spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("不传值: "+x));


        // 传空值，就不会带这个条件，从而查询所有
        test = new TestLoginPwdBetween();
        test.setLoginPwd("");
        spec = EnhanceSpecification.beanWhere(test);
        userDao.findAll(spec).forEach(x -> System.out.println("传空值: "+x));
    }


}
