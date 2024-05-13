package cn.tannn.jdevelops.demo.jpa.dao;


import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsRepository<User, Integer> {

    /**
     * 查询
     * @param userNo userNo
     * @return User
     */
    User findByUserNo(String userNo);


    /**
     * 自定义sql
     */
    @Query("select u from User u ")
    List<User> customSql();
}
