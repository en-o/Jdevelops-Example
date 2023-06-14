package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.hutool.core.lang.Assert;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertFalse;
import java.util.Arrays;
import java.util.Objects;

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
    void testDeleteBySpec(){
        /*
        INSERT INTO `test`.`sys_user` ( `create_time`, `update_time`, `create_user_name`, `update_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon` )
            VALUES
	        (  '2023-05-30 09:26:29', '2023-05-30 09:26:29', 'tan', 'tan', 'delete', 'test', '123', 'test', '123', '123', '' );
         */
//        long delete = userService.getJpaBasicsDao()
//                .delete((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userNo"), "delete"));
//        Assert.isTrue(delete>0);


        boolean deleteb = userService.deleteTest((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userNo"), "delete"));
        Assert.isTrue(deleteb);
    }


    @Test
    void testDeleteByUnique(){
        userService.deleteByUnique("6", User::getUserNo);
    }


    @Test
    void testDeleteByUniques(){
        userService.deleteByUnique(Arrays.asList("123","2"),User::getUserNo);
    }
}
