package cn.tannn.cachesjdbctemplate.service;

import cn.jdevelops.jdbctemplate.annotation.Query;
import cn.tannn.cachesjdbctemplate.UserBO;
import cn.tannn.cachesjdbctemplate.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
public  interface UserService {


    List<User> findAll();


    User findById();

    User findById(Integer id);

    User findByBean(UserBO user);
}
