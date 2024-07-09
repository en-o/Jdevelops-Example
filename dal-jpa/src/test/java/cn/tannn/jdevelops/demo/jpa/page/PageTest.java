package cn.tannn.jdevelops.demo.jpa.page;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.request.Pagings;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 测试 j2service 分页
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 上午10:08
 */
@SpringBootTest
@Slf4j
public class PageTest {

    @Autowired
    private UserService userService;

    @Test
    void sortPagings() {
        // 默认 1,20
        userService.findPage(new Pagings()).forEach(System.out::println);
        //  1,2
        userService.findPage(new Pagings(1, 2)).forEach(System.out::println);
    }

    @Test
    void sortPagingsSort() {
        // JpaBasicsDao().findAll order by user0_.id
        Page<User> id = userService.getJpaBasicsDao().findAll(new Pagings().pageable(Sort.by("id")));
        log.info("JpaBasicsDao().findAll: {}, bean: {}",id.getTotalElements(), id.getContent());
        // findPage(Pagings) order by user0_.name
        userService.findPage(new Pagings().sort(Sort.by("name"))).forEach(System.out::println);
        //  1,2 order by user0_.name
        userService.findPage(new Pagings(1, 2).sort(Sort.by("name"))).forEach(System.out::println);

    }

    @Test
    void sortBeanPagingsSort() {
//        // findPage(Pagings) order by user0_.phone
        userService.findPage(new UserFind("重庆"), new Pagings().sort(Sort.by("phone")))
                .forEach(System.out::println);
//        //  1,2 order by user0_.phone
        userService.findPage(new UserFind("重庆"), new Pagings(1, 2)
                .sort(Sort.by("phone"))).forEach(System.out::println);

        //  limit 2, 2
        userService.findPage(new UserFind("重庆"), new Pagings(2, 2)
                .sort(Sort.by("phone"))).forEach(System.out::println);
        // limit 4, 2
        userService.findPage(new UserFind("重庆"), new Pagings(3, 2)
                .sort(Sort.by("phone"))).forEach(System.out::println);
    }
}
