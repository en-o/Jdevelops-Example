package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserServiceImpl;
import cn.tannn.jdevelops.annotations.jdbctemplate.JdbcTemplate;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.jdectemplate.core.CreateProxy;
import cn.tannn.jdevelops.result.request.Paging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 这样是不行，  我没有适配 class 只支持 interface ，也不打算支持，因为不美观
 */
@SpringBootTest
class QueryImplTest {

    @JdbcTemplate
    private QueryUserServiceImpl queryUserServiceImpl;
    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;


    @BeforeEach
    void x(){
        queryUserServiceImpl = (QueryUserServiceImpl) CreateProxy.createQueryProxy(QueryUserServiceImpl.class, jdbcTemplate, Query.class);
    }


    @Test
    void findAll() {
        queryUserServiceImpl.findAll().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void findById() {
        System.out.println(queryUserServiceImpl.findById());
    }

    @Test
    void findByIdByBo() {
        System.out.println(queryUserServiceImpl.findByIdByBo());
    }

    @Test
    void findById2() {
        System.out.println(queryUserServiceImpl.findById(2));
    }

    @Test
    void findById3() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            queryUserServiceImpl.findById(10);
//        });
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        System.out.println(queryUserServiceImpl.findByBean(userBO));
    }

    @Test
    void findByBean2() {
        UserBO userBO = new UserBO();
        userBO.setName("111");
        userBO.setLoginName("SH-01");
        queryUserServiceImpl.findByBean2(userBO).forEach(it -> System.out.println(it.toString()));
    }

    @Test
    void findName() {
        queryUserServiceImpl.findName().forEach(System.out::println);
    }


    @Test
    void findId() {
        System.out.println(queryUserServiceImpl.findId());
    }


    @Test
    void findIdByName() {
        System.out.println(queryUserServiceImpl.findIdByName("用户1"));
    }

    @Test
    void findIdByName2() {
        System.out.println(queryUserServiceImpl.findIdByNameAndAddress("111", "重庆"));
    }

    @Test
    void findAllPage() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserServiceImpl.findAllPage(new Paging(1, 2)));
        System.out.println("第二页：" + queryUserServiceImpl.findAllPage(new Paging(2, 2)));
    }

    @Test
    void findAllPage_2() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserServiceImpl.findAllPage("111", "重庆", new Paging(1, 2)));
        System.out.println("第二页：" + queryUserServiceImpl.findAllPage("111", "重庆", new Paging(2, 2)));
    }

    @Test
    void findAllPageByName() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserServiceImpl.findAllPageByName(new Paging(1, 2)));
        System.out.println("第二页：" + queryUserServiceImpl.findAllPageByName(new Paging(2, 2)));
    }

    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderD() {
        queryUserServiceImpl.findAllOrderD().forEach(it -> System.out.printf(it.toString()));
    }
    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderA() {
        queryUserServiceImpl.findAllOrderA().forEach(it -> System.out.printf(it.toString()));
    }
}
