package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.jpa.utils.SpecificationUtil;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * Fluent API
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/11/22 上午11:58
 * @see https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html#query-by-example.fluent
 */
@SpringBootTest
public class FluentAPITest {
    @Autowired
    private UserDao userDao;

    @Test
    void testFluentPage() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> in = instance.specification((r, q, b) -> {
            return b.like(r.get("name"), "%用户%");
        });
        Page<User> page = userDao.findBy(in, r -> r.page(Pageable.ofSize(2)));
        System.out.println("page:" + JSON.toJSONString(page));
        page.getContent().forEach(System.out::println);
    }


    @Test
    void testFluentFirst() {
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> in = instance.specification((r, q, b) -> {
            return b.like(r.get("name"), "%用户%");
        });
        userDao.findBy(in, r -> r.sortBy(Sort.by("name").descending())
                .first()).ifPresent(System.out::println);
    }


    /**
     * 无效 的 as
     */
//    @Test
//    void testFluentAs() {
//        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
//        Specification<User> in = instance.specification((r, q, b) -> {
//            return b.like(r.get("name"), "%用户%");
//        });
//         userDao.findBy(in, r -> r.project("name","address","loginName").as(RcUser.class).all()).forEach(System.out::println);
//    }

    // 定义投影接口
    public interface NameOnly {
        String getName();
    }
}
