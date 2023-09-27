package cn.tannn.jdevelopssbootjpademo.dao;


import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
