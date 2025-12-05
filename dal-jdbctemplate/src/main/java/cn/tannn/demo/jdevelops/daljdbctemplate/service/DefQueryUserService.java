package cn.tannn.demo.jdevelops.daljdbctemplate.service;


import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.jdevelops.annotations.jdbctemplate.proxysql.Query;

import java.util.Collections;
import java.util.List;

/**
 * 测试 def 可以的
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
public interface DefQueryUserService {

    @Query("select * from sys_user")
    default List<User> findAll(){
        return Collections.emptyList();
    }


}
