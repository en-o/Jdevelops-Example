package cn.tannn.jdevelops.demo.jpa.update;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.demo.jpa.update.dto.UserUpdate;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 测试 j2service 更新
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 下午2:26
 */
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserService userService;

    private String userNo;
    // 准备数据
    @Test
    void add(){
        List<User> saves = userService.saves(Arrays.asList(
                User.create("update_tan", "cq_update", "update_tan", "1234")
        ));
        saves.forEach(System.out::println);
        userNo = saves.get(0).getUserNo();
    }

    @Test
    void update(){
        UserUpdate userUpdate = new UserUpdate();
//        userUpdate.setUserNo(userNo);
        userUpdate.setUserNo("1791001704666087424");
        userUpdate.setName("update_tan_2");
        userUpdate.setPhone("123444");
        // where user_no=?
        userService.update(userUpdate, SQLOperator.EQ);
    }

    @Test
    void update2(){
        UserUpdate userUpdate = new UserUpdate();
//        userUpdate.setUserNo(userNo);
        userUpdate.setUserNo("1791001704666087424");
        userUpdate.setName("update_tan_2");
        userUpdate.setPhone("123444");
        // unique存在的话以当前输入为准 update_user_name=? where name=?
        userService.update(userUpdate, SQLOperator.EQ,"name");
    }
}

