package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;


@SpringBootTest
class QueryTest {

    @Autowired
    private QueryUserService queryUserServiceImpl;

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
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->{
            queryUserServiceImpl.findById(10);
        });
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
        System.out.println(queryUserServiceImpl.findIdByNameAndAddress("111","重庆"));
    }

}
