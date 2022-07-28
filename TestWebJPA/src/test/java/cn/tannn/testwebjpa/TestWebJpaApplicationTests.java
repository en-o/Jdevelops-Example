package cn.tannn.testwebjpa;

import cn.jdevelops.jap.core.util.SortUtil;
import cn.tannn.testwebjpa.dao.UserDao;
import cn.tannn.testwebjpa.entity.User;
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
        List<User> all = userDao.findAll(SortUtil.sort(Sort.Direction.DESC, User::getId));
        all.forEach(it -> System.err.println(it.toString()));
    }

}
