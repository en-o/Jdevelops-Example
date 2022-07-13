package cn.tannn.hjpa.dao;


import cn.jdevelops.jpa.server.dao.JpaBasicsDao;
import cn.tannn.hjpa.entity.User;

import java.util.Collection;

/**
 * 用户表
 *
 * @author tan
 * @date 2021-09-10 1StudentDao1:08
 */
public interface UserDao extends JpaBasicsDao<User, Integer> {

    <T> T findByLoginName(String name, Class<T> type);
}
