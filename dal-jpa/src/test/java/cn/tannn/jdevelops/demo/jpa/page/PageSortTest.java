package cn.tannn.jdevelops.demo.jpa.page;

import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.request.PagingSorteds;
import cn.tannn.jdevelops.result.request.Sorted;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 j2service 分页排序
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 上午10:09
 */
@SpringBootTest
@Slf4j
public class PageSortTest {
    @Autowired
    private UserService userService;

    @Test
    void pagingSorteds() {
        // 默认 1,20  order by user0_.id
        userService.findPage(new PagingSorteds()).forEach(System.out::println);
        //  1,2  order by user0_.id
        userService.findPage(new PagingSorteds(1, 2)).forEach(System.out::println);
        //  1,2 user0_.name desc
        userService.findPage(new PagingSorteds(1, 2, new Sorted("name"))).forEach(System.out::println);

        //  limit 2, 2
        userService.findPage(new PagingSorteds(2, 2)).forEach(System.out::println);
    }


    @Test
    void beanPagingSorteds() {
        // 默认 1,20  order by user0_.id
        userService.findPage(new UserFind("重庆"), new PagingSorteds()).forEach(System.out::println);
        //  1,2  order by user0_.id
        userService.findPage(new UserFind("重庆"), new PagingSorteds(1, 2)).forEach(System.out::println);
        //  1,2 user0_.phone desc
        userService.findPage(new UserFind("重庆"), new PagingSorteds(1, 2, new Sorted("phone"))).forEach(System.out::println);
    }

    /**
     * 覆盖默认排序
     */
    @Test void fixSort(){
        // 默认 1,20  order by user0_.phone
        userService.findPage(
                new PagingSorteds().fixSort("phone")
        ).forEach(System.out::println);

        //  1,2  order by user0_.phone
        userService.findPage(
                new PagingSorteds(1, 2).fixSort("phone")
        ).forEach(System.out::println);
    }

    /**
     * 追加排序
     */
    @Test void appendSort(){
        // 为空会覆盖默认排序  order by user0_.phone
        userService.findPage(
                new PagingSorteds().append("phone")
        ).forEach(System.out::println);

        //  1,2  order by user0_.id desc, user0_.phone desc
        userService.findPage(
                new PagingSorteds(1, 2, 1,"id").append("phone")
        ).forEach(System.out::println);

        //  2,2  order by user0_.id desc, user0_.phone desc
        userService.findPage(
                new PagingSorteds(2, 2, 1,"id").append("phone")
        ).forEach(System.out::println);
    }
}
