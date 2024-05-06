package com.example.jdevelopsconfigdemo.test;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
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


    private  final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(User.class);
        this.userDao = userDao;
    }


    @Override
    public User findByUserNoCopyDao(String userNo) {
        return userDao.findByUserNo(userNo);
    }



}
