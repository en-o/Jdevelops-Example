package cn.tannn.demo.jdevelops.daljdbctemplate.service;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.annotations.jdbctemplate.JdbcTemplate;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.result.request.Paging;
import cn.tannn.jdevelops.result.response.PageResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 无法使用到class 上，只能在接口中使用，这里是作为错误示范的
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
@Service
@JdbcTemplate
public  class QueryUserServiceImpl {

    @Query("select * from sys_user")
    public List<User> findAll() {
        return Collections.emptyList();
    }

    @Query("select * from sys_user where id = 1 ")
    public User findById() {
        return null;
    }


    @Query("select * from sys_user where id = 1 ")
    public UserBO findByIdByBo() {
        return null;
    }

    @Query("select * from sys_user where id = #{id} ")
    public User findById(Integer id) {
        return null;
    }


    @Query("select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ")
    public User findByBean(UserBO user) {
        return null;
    }


    @Query("select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ")
    public List<User> findByBean2(UserBO user) {
        return Collections.emptyList();
    }

    @Query("select name from sys_user where  id = #{id} ")
    public String findNameById(Integer id) {
        return null;
    }

    @Query("select name from sys_user")
    public List<String> findName() {
        return Collections.emptyList();
    }

    @Query("select id from sys_user where name = '#{name}' ")
    public Integer findIdByName(String name) {
        return null;
    }

    @Query("select * from sys_user where name = '#{name}' and address = '#{address}' ")
    public User findIdByNameAndAddress(String name, String address) {
        return null;
    }


    @Query("select id from sys_user")
    public List<Integer> findId() {
        return null;
    }

    // 分页
    @Query("select * from sys_user")
    public PageResult<User> findAllPage(Paging paging) {
        return null;
    }

    // 分页
    @Query("select * from sys_user where name = '#{name}' and address = '#{address}'")
    public PageResult<User> findAllPage(String name, String address, Paging paging) {
        return null;
    }

    // 分页
    @Query("select name from sys_user")
    public PageResult<String> findAllPageByName(Paging paging) {
        return null;
    }

    @Query("select * from sys_user order by id desc ")
    public List<User> findAllOrderD(){
        return Collections.emptyList();
    }

    @Query("select * from sys_user order by id asc ")
    public List<User> findAllOrderA() {
        return Collections.emptyList();
    }
}
