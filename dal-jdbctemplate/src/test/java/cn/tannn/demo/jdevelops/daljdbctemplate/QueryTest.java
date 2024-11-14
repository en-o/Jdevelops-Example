package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import cn.tannn.jdevelops.result.request.Paging;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;


@SpringBootTest
class QueryTest {

    @Autowired
    private QueryUserService queryUserService;

    @Test
    void findAll() {
        queryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void findById() {
        System.out.println(queryUserService.findById());
    }

    @Test
    void findByIdByBo() {
        System.out.println(queryUserService.findByIdByBo());
    }

    @Test
    void findById2() {
        System.out.println(queryUserService.findById(2));
    }

    @Test
    void findById3() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            queryUserService.findById(10);
        });
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        System.out.println(queryUserService.findByBean(userBO));
    }

    @Test
    void findByBean2() {
        UserBO userBO = new UserBO();
        userBO.setName("111");
        userBO.setLoginName("SH-01");
        queryUserService.findByBean2(userBO).forEach(it -> System.out.println(it.toString()));
    }

    @Test
    void findName() {
        queryUserService.findName().forEach(System.out::println);
    }


    @Test
    void findId() {
        System.out.println(queryUserService.findId());
    }


    @Test
    void findIdByName() {
        System.out.println(queryUserService.findIdByName("用户1"));
    }

    @Test
    void findIdByName2() {
        System.out.println(queryUserService.findIdByNameAndAddress("111", "重庆"));
    }

    @Test
    void findAllPage() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserService.findAllPage(new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findAllPage(new Paging(2, 2)));
    }

    @Test
    void findAllPage_2() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserService.findAllPage("111", "重庆", new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findAllPage("111", "重庆", new Paging(2, 2)));
    }

    @Test
    void findAllPageByName() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserService.findAllPageByName(new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findAllPageByName(new Paging(2, 2)));
    }

    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderD() {
        queryUserService.findAllOrderD().forEach(it -> System.out.printf(it.toString()));
    }
    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderA() {
        queryUserService.findAllOrderA().forEach(it -> System.out.printf(it.toString()));
    }


    /**
     * page - like
     */
    @Test
    void findLikePage() {
        // currentPage=2, pageSize=2, totalPages=2, total=4

        // sql select * from sys_user where name like '%用户%'
        // sql SELECT COUNT(*) from sys_user where name like '%用户%'
        System.out.println("第一页：" + queryUserService.findLikePage("%用户%", new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findLikePage("%用户%", new Paging(2, 2)));
    }
}
