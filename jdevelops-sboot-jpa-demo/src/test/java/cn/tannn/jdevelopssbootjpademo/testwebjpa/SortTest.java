package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.result.request.SortDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import cn.tannn.jdevelopssbootjpademo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 排序测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 14:18
 */
@SpringBootTest
public class SortTest {
    @Autowired
    private UserService userService;

    /**
     * 排序查询 返回新的对象
     */
    @Test
    void findByBeanSFVO(){
        SortDTO sort = new SortDTO("userNo", 0);
        User user = new User();
        user.setLoginPwd("123456");
        List<UserVO> complex = userService.findComplex(user, sort, UserVO.class);
        complex.forEach(System.out::println);
    }

    /**
     * 排序查询
     */
    @Test
    void findByBeanS(){
        SortDTO sort = new SortDTO("userNo", 0);
        User user = new User();
        user.setLoginPwd("123456");
        List<User> complex = userService.findComplex(user, sort);
        complex.forEach(System.out::println);
    }

}
