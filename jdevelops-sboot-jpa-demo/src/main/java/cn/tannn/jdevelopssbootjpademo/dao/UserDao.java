package cn.tannn.jdevelopssbootjpademo.dao;


import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsRepository<User, Integer> {
}
