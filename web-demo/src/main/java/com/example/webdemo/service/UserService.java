package com.example.webdemo.service;


import cn.jdevelops.data.jap.service.J2Service;
import com.example.webdemo.entity.User;
import com.example.webdemo.service.pojo.UserBO;

/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:24
 */
public interface UserService extends J2Service<User> {


    /**
     * 测试 jdevelops-data-jdbctemplate
     */
    User findById(Integer id);



    /**
     * 测试 jdevelops-data-jdbctemplate
     */
    User findByBean(UserBO user);

}
