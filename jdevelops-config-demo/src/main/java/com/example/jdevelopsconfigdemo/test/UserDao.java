package com.example.jdevelopsconfigdemo.test;


import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsRepository<User, Integer> {


}
