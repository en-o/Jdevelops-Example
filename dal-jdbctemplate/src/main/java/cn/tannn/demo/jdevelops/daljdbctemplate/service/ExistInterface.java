package cn.tannn.demo.jdevelops.daljdbctemplate.service;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;

import java.util.Collections;
import java.util.List;

/**
 * 无法使用到class 上，只能在接口中使用，这里是作为错误示范的
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
public interface ExistInterface {
    @Query("select * from sys_user")
    default List<User> findAll() {
        return Collections.emptyList();
    }

    @Query("select * from sys_user where id = 1 ")
    User findById();


    UserBO findByIdByBo();
}
