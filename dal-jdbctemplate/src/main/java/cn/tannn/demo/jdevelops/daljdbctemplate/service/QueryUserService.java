package cn.tannn.demo.jdevelops.daljdbctemplate.service;


import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.jdectemplate.annotation.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
@Service
public interface QueryUserService {


    @Query(value = "select * from sys_user", clazz = User.class)
    public List<User> findAll();

    @Query(value = "select * from sys_user where id = 1 ", clazz = User.class)
    public User findById();

    @Query(value = "select * from sys_user where id = #{id} ", clazz = User.class)
    public User findById(Integer id);


    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ", clazz = User.class)
    public User findByBean(UserBO user);


    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ", clazz = User.class)
    public List<User> findByBean2(UserBO user);

    @Query(value = "select name from sys_user where  id = #{id} ", clazz = String.class)
    public String findNameById(Integer id);

    @Query(value = "select name from sys_user", clazz = String.class)
    public List<String> findName();

    @Query(value = "select id from sys_user where name = '#{name}' ", clazz = Integer.class)
    public Integer findIdByName(String name);

    @Query(value = "select id from sys_user", clazz = Integer.class)
    public List<Integer> findId();
}
