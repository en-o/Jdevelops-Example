package cn.tannn.demo.jdevelops.daljdbctemplate.service;


import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.annotations.jdbctemplate.JdbcTemplate;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.result.request.Paging;
import cn.tannn.jdevelops.result.response.PageResult;
import java.util.Collections;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试 def 可以的
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
@Service
public interface DefQueryUserService {


    @Query("select * from sys_user")
    default List<User> findAll(){
        return Collections.emptyList();
    }


}
