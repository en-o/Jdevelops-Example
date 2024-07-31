package cn.tannn.demo.jdevelops.frameworksquick.test;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<Integer> {

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
