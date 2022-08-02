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
public  class QueryUserServiceImpl implements QueryUserService {

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
    @Query(value = "select * from sys_user where id = #{id} ",clazz = User.class)
    public User findById(Integer id) {
        return new User();
    }


    @Override
    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ",clazz = User.class)
    public User findByBean(UserBO user) {
        return new User();
    }



    @Override
    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ",clazz = User.class)
    public List<User> findByBean2(UserBO user) {
        return new ArrayList<>();
    }

    @Override
    @Query(value = "select name from sys_user where  id = #{id} ",clazz = String.class)
    public String findNameById(Integer id) {
        return "";
    }

    @Override
    @Query(value = "select name from sys_user",clazz = String.class)
    public List<String> findName() {
        return new ArrayList<>();
    }

    @Override
    @Query(value = "select id from sys_user where name = '#{name}' ",clazz = Integer.class)
    public Integer findIdByName(String name) {
        return null;
    }

    @Override
    @Query(value = "select id from sys_user",clazz = Integer.class)
    public List<Integer> findId() {
        return new ArrayList<>();
    }
}
