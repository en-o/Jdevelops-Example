package cn.tannn.hjpa;

import cn.tannn.hjpa.dao.UserDao;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import cn.tannn.hjpa.vo.UserPO;
import cn.tannn.hjpa.vo.UserVO;
import cn.tannn.hjpa.vo.UserVo2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * 测试在dao层泛化
 * @author tnnn
 * @version V1.0
 * @date 2022-07-13 16:08
 */
@SpringBootTest
public class GenericTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao  userDao;

    @Test
    void tests() {
//        System.out.println(userDao.findByLoginName("admin", User.class));
        System.out.println(userDao.findByLoginName("admin", UserPO.class));
    }


    @Test
    void tests2() {
        System.out.println(userDao.findByLoginNameQuery("admin"));
    }


}
