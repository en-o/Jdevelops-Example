package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * 删除
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 15:19
 */
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserService userService;

    @Test
    void testDeleteByUnique(){
        userService.deleteByUnique("6", User::getUserNo);
    }


    @Test
    void testDeleteByUniques(){
        userService.deleteByUnique(Arrays.asList("123","2"),User::getUserNo);
    }
}
