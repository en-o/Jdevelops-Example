package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.module.RcUser;
import cn.tannn.jdevelops.demo.jpa.module.RiUser;
import cn.tannn.jdevelops.demo.jpa.module.RrUser;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 投影
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/11/22 下午1:29
 */
@SpringBootTest
public class ProjectionsTest {

    @Autowired
    private UserDao userDao;

    @Test
    void testx(){
        // error
//        userDao.findByNameLike("用%", RcUser.class).forEach(System.out::println);
//        List<RiUser> byNameLike = userDao.findByNameLike("用%", RiUser.class);
//        System.out.println(JSON.toJSONString(byNameLike));

        List<RrUser> byNameLike2 = userDao.findByNameLike("用%", RrUser.class);
        System.out.println(JSON.toJSONString(byNameLike2));
    }
}
