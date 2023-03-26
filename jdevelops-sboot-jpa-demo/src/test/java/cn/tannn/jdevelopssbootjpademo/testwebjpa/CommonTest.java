package cn.tannn.jdevelopssbootjpademo.testwebjpa;

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
        all.forEach(it -> System.err.println(it.toString()));
    }

    @Test
    void getJpaBasicsDao(){
        System.out.println(userService.getJpaBasicsDao().findById(5));
    }

    @Test
    void findOne(){
        System.out.println(userService.findBeanOne(User::getLoginPwd, "1234561"));
    }

}
