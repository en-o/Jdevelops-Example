package cn.tannn.demo.jdevelops.daljdbctemplate.service;


import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.annotations.jdbctemplate.JdbcTemplate;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.result.request.Paging;
import cn.tannn.jdevelops.result.response.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
@Service
@JdbcTemplate
public interface QueryUserService {


    @Query("select * from sys_user")
    List<User> findAll();

    @Query("select * from sys_user where id = 1 ")
    User findById();


    @Query("select * from sys_user where id = 1 ")
    UserBO findByIdByBo();

    @Query("select * from sys_user where id = #{id} ")
    User findById(Integer id);


    @Query("select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ")
    User findByBean(UserBO user);


    @Query("select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ")
    List<User> findByBean2(UserBO user);

    @Query("select name from sys_user where  id = #{id} ")
    String findNameById(Integer id);

    @Query("select name from sys_user")
    List<String> findName();

    @Query("select id from sys_user where name = '#{name}' ")
    Integer findIdByName(String name);

    @Query("select * from sys_user where name = '#{name}' and address = '#{address}' ")
    User findIdByNameAndAddress(String name, String address);


    @Query("select id from sys_user")
    List<Integer> findId();

    // 分页
    @Query("select * from sys_user")
    PageResult<User> findAllPage(Paging paging);

    // 分页
    @Query("select * from sys_user where name = '#{name}' and address = '#{address}'")
    PageResult<User> findAllPage(String name, String address, Paging paging);


    @Query("select * from sys_user order by id desc ")
    List<User> findAllOrderD();

    @Query("select * from sys_user order by id asc ")
    List<User> findAllOrderA();
}
