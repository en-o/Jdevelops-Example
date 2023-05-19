package com.example.jdevelopssbootjdbctemplatedemo;

import com.example.jdevelopssbootjdbctemplatedemo.service.QueryUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;


@SpringBootTest
class QueryTest {

    @Autowired
    private QueryUserService userService;

    @Test
    void findAll() {
        userService.findAll().forEach(it -> System.out.printf(it.toString()));
    }
    @Test
    void findById() {
        System.out.println(userService.findById());
    }

    @Test
    void findById2() {
        System.out.println(userService.findById(2));
    }

    @Test
    void findById3() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->{
            userService.findById(10);
        });
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        System.out.println(userService.findByBean(userBO));
    }

    @Test
    void findByBean2() {
        UserBO userBO = new UserBO();
        userBO.setName("111");
        userBO.setLoginName("SH-01");
        userService.findByBean2(userBO).forEach(it -> System.out.println(it.toString()));
    }

    @Test
    void findName() {
        userService.findName().forEach(System.out::println);
    }


    @Test
    void findId() {
        System.out.println(userService.findId());
    }


    @Test
    void findIdByName() {
        System.out.println(userService.findIdByName("超级管理员"));
    }

}
