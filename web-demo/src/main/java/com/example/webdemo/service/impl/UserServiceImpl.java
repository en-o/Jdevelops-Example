package com.example.webdemo.service.impl;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
import com.example.webdemo.dao.UserDao;
import com.example.webdemo.entity.User;
import com.example.webdemo.service.UserService;
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

}
