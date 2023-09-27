package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import cn.tannn.jdevelopssbootjpademo.dao.UserDao;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 公共测试
 */
@SpringBootTest
class CommonTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    void testSort(){
        Sort sort = Sort.by("id").ascending();
        List<User> all = userDao.findAll(sort);
        all.forEach(it -> System.err.println(it.getId()+it.toString()));
    }

    @Test
    void getJpaBasicsDao(){
        System.out.println(userService.getJpaBasicsDao().findById(5));
    }

    @Test
    void findOne(){
        System.out.println(userService.findBeanOne(User::getLoginPwd, "1234561"));
    }


    @Test
    void autoDao(){
        UserDao jpaBasicsDao = userService.getJpaBasicsDao();
        System.out.println("dao=============> "+jpaBasicsDao.findByUserNo("admin"));
        System.out.println("dao2=============> "+userService.getJpaBasicsDao().findById(1));
        System.out.println("server=============> "+userService.findByUserNoCopyDao("admin"));
        System.out.println("server2=============> "+userService.findByUserNoCopy2Dao("admin"));
    }


    @Test
    void customSqlTest(){
        UserDao jpaBasicsDao = userService.getJpaBasicsDao();
        jpaBasicsDao.customSql().forEach(System.out::println);
    }

}
