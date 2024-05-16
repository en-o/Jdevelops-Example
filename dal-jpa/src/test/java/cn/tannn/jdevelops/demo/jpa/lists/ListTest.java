package cn.tannn.jdevelops.demo.jpa.lists;

import cn.tannn.jdevelops.demo.jpa.page.SortTest;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 j2service finds 排序相关看{@link SortTest}
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 下午3:15
 */
@SpringBootTest
public class ListTest {
    @Autowired
    private UserService userService;


    @Test
    void finds(){
        userService.finds().forEach(System.out::println);
    }

    @Test
    void findsSQLOperator(){
        System.out.println("=========================EQ=================================");
        userService.finds("address", SQLOperator.EQ,"重庆").forEach(System.out::println);
        System.out.println("=========================ISNULL=================================");
        userService.finds("userIcon", SQLOperator.ISNULL).forEach(System.out::println);

        System.out.println("=========================IN=================================");
        userService.finds("address", SQLOperator.IN, "重庆","222").forEach(System.out::println);

        System.out.println("=========================like=================================");
        userService.finds("loginPwd", SQLOperator.LIKE, "123").forEach(System.out::println);
    }

    @Test
    void findsBean(){
        // findPage(Pagings) order by user0_.phone
        userService.finds(new UserFind("重庆")).forEach(System.out::println);
    }
}
