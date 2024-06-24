//package cn.tannn.demo.jdevelops.daljdbctemplate.service;
//
//import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
//import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
//import cn.tannn.jdevelops.jdectemplate.annotation.JdecTemplate;
//import cn.tannn.jdevelops.jdectemplate.annotation.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author tnnn
// * @version V1.0
// * @date 2022-08-01 13:41
// */
//@Service
//@JdecTemplate
//public  class QueryUserServiceImpl {
//
//    @Query(value = "select * from sys_user",clazz = User.class)
//    public List<User> findAll() {
//        return new ArrayList<>();
//    }
//
//    @Query(value = "select * from sys_user where id = 1 ",clazz = User.class)
//    public User findById() {
//        return new User();
//    }
//
//    @Query(value = "select * from sys_user where id = #{id} ",clazz = User.class)
//    public User findById(Integer id) {
//        return new User();
//    }
//
//
//    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ",clazz = User.class)
//    public User findByBean(UserBO user) {
//        return new User();
//    }
//
//
//
//    @Query(value = "select * from sys_user where name = '#{user.name}' or login_name = '#{user.loginName}' ",clazz = User.class)
//    public List<User> findByBean2(UserBO user) {
//        return new ArrayList<>();
//    }
//
//    @Query(value = "select name from sys_user where  id = #{id} ",clazz = String.class)
//    public String findNameById(Integer id) {
//        return "";
//    }
//
//    @Query(value = "select name from sys_user",clazz = String.class)
//    public List<String> findName() {
//        return new ArrayList<>();
//    }
//
//    @Query(value = "select id from sys_user where name = '#{name}' ",clazz = Integer.class)
//    public Integer findIdByName(String name) {
//        return null;
//    }
//
//    @Query(value = "select id from sys_user",clazz = Integer.class)
//    public List<Integer> findId() {
//        return new ArrayList<>();
//    }
//}
