package cn.tannn.cachesjdbctemplate.service;

import cn.jdevelops.jdbctemplate.annotation.Query;
import cn.tannn.cachesjdbctemplate.UserBO;
import cn.tannn.cachesjdbctemplate.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
@Service
public  class UserServiceImpl implements UserService{

    @Override
    @Query(value = "select * from sys_user",clazz = User.class)
    public List<User> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Query(value = "select * from sys_user where id = 1 ",clazz = User.class)
    public User findById() {
        return new User();
    }

    @Override
    @Query(value = "select * from sys_user where id = {id} ",clazz = User.class)
    public User findById(Integer id) {
        return new User();
    }


    @Override
    @Query(value = "select * from sys_user where name = #{user.name} or loginName = #{user.loginName}",clazz = User.class)
    public User findByBean(UserBO user) {
        return new User();
    }
}
