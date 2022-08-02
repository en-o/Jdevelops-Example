package cn.tannn.cachesjdbctemplate.service;

import cn.tannn.cachesjdbctemplate.UserBO;
import cn.tannn.cachesjdbctemplate.entity.User;

import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-08-01 13:41
 */
public  interface QueryUserService {


    List<User> findAll();


    User findById();

    User findById(Integer id);

    User findByBean(UserBO user);

    List<User> findByBean2(UserBO user);

    String  findNameById(Integer id);
    List<String>  findName();

    Integer  findIdByName(String name);
    List<Integer>  findId();
}
