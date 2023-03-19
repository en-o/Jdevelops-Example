package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.util.JpaUtils;
import cn.tannn.jdevelopssbootjpademo.dao.UserDao;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class TestWebJpaApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() throws Exception {

    }


    @Test
    void testUpdate() throws Exception {
        User user = new User();
        user.setUserNo("123");
        user.setName("根据指定key更新");
        userDao.updateEntity(user,"userNo");
    }


    @Test
    void testUpdate2() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("根据ID更新");
        userDao.updateEntity(user);
    }

    @Test
    void testSortUtil(){
        Sort sort = Sort.by("id").ascending();
        List<User> all = userDao.findAll(sort);
        all.forEach(it -> System.err.println(it.toString()));
    }

}
