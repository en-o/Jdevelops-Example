package cn.tannn.jdevelopssbootjpademo.pg;


import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserPgDao extends JpaBasicsRepository<UserPgsql, Integer> {

}
