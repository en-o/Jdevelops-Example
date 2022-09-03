package cn.tannn.hjpa.dao;


import cn.jdevelops.jpa.server.dao.JpaBasicsDao;
import cn.jdevelops.retrun.annotation.JpaReturn;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.vo.UserPO;
import cn.tannn.hjpa.vo.UserVo2;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Map;

/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsDao<User, Integer> {

    <T> T findByLoginName(String name, Class<T> type);

    @Query("select new cn.tannn.hjpa.vo.UserPO(u.userNo,u.name,u.address) from User u where u.loginName = ?1 ")
    UserPO findByLoginNameQuery(String name);

}
