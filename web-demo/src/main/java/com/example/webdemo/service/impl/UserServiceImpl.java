package com.example.webdemo.service.impl;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
import cn.jdevelops.data.jdbctemplate.annotation.Query;
import com.example.webdemo.dao.UserDao;
import com.example.webdemo.entity.User;
import com.example.webdemo.service.UserService;
import com.example.webdemo.service.pojo.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:41
 */
@Slf4j
@Service
public class UserServiceImpl extends J2ServiceImpl<UserDao, User, Integer> implements UserService {


    @Query(value = "select * from sys_user where id = #{id} ",clazz = User.class)
    @Override
    public User findById(Integer id) {
        return null;
    }


    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ",clazz = User.class)
    @Override
    public User findByBean(UserBO user) {
        return null;
    }
}
