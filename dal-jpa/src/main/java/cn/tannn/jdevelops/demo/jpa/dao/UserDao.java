package cn.tannn.jdevelops.demo.jpa.dao;


import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.projections.RcUser;
import cn.tannn.jdevelops.demo.jpa.projections.RiUser;
import cn.tannn.jdevelops.demo.jpa.projections.RrUser;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsRepository<User, Long> {

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

    /**
     * @param name
     * @param rclazz 返回类型
     * @return
     * @param <S>
     */
    <S> List<S> findByNameLike(String name, Class<S> rclazz);


    /**
     * @param name
     * @return
     */
    List<RrUser> findByNameLike(String name);


    /**
     * @param address
     * @return
     */
    List<RiUser> findByAddress(String address);

    /**
     * @param address
     * @return
     */
    @Query("select new cn.tannn.jdevelops.demo.jpa.projections.RcUser(name,address,loginName) from User where address = ?1")
    List<RcUser> findByAddress2(String address);
}
