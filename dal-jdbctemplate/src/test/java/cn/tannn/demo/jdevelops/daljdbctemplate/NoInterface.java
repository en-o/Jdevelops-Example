package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.NoInterfaceQueryImpl;
import cn.tannn.jdevelops.jdectemplate.core.CreateProxy;
import cn.tannn.jdevelops.result.request.Paging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 适配 class
 */
@SpringBootTest
class NoInterface {

    private NoInterfaceQueryImpl noInterfaceQuery;
    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;


    @BeforeEach
    void x() {
        noInterfaceQuery = (NoInterfaceQueryImpl) CreateProxy.createQueryProxy(NoInterfaceQueryImpl.class, jdbcTemplate);
    }


    @Test
    void findAll() {
        noInterfaceQuery.findAll().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void findById() {
        System.out.println(noInterfaceQuery.findById());
    }

    @Test
    void findByIdByBo() {
        System.out.println(noInterfaceQuery.findByIdByBo());
    }

    @Test
    void findById2() {
        System.out.println(noInterfaceQuery.findById(2));
    }

    @Test
    void findById3() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            noInterfaceQuery.findById(10);
//        });
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        System.out.println(noInterfaceQuery.findByBean(userBO));
    }

    @Test
    void findByBean2() {
        UserBO userBO = new UserBO();
        userBO.setName("111");
        userBO.setLoginName("SH-01");
        noInterfaceQuery.findByBean2(userBO).forEach(it -> System.out.println(it.toString()));
    }

    @Test
    void findName() {
        noInterfaceQuery.findName().forEach(System.out::println);
    }


    @Test
    void findId() {
        System.out.println(noInterfaceQuery.findId());
    }


    @Test
    void findIdByName() {
        System.out.println(noInterfaceQuery.findIdByName("用户1"));
    }

    @Test
    void findIdByName2() {
        System.out.println(noInterfaceQuery.findIdByNameAndAddress("111", "重庆"));
    }

    @Test
    void findAllPage() {
//        noInterfaceQuery.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + noInterfaceQuery.findAllPage(new Paging(1, 2)));
        System.out.println("第二页：" + noInterfaceQuery.findAllPage(new Paging(2, 2)));
    }

    @Test
    void findAllPage_2() {
//        noInterfaceQuery.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + noInterfaceQuery.findAllPage("111", "重庆", new Paging(1, 2)));
        System.out.println("第二页：" + noInterfaceQuery.findAllPage("111", "重庆", new Paging(2, 2)));
    }

    @Test
    void findAllPageByName() {
//        noInterfaceQuery.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + noInterfaceQuery.findAllPageByName(new Paging(1, 2)));
        System.out.println("第二页：" + noInterfaceQuery.findAllPageByName(new Paging(2, 2)));
    }

    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderD() {
        noInterfaceQuery.findAllOrderD().forEach(it -> System.out.printf(it.toString()));
    }

    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderA() {
        noInterfaceQuery.findAllOrderA().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void noQueryTest() {
        System.out.println(noInterfaceQuery.noQuery());
    }
}
