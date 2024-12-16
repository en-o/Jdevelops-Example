package cn.tannn.demo.jdevelops.daljdbctemplate.service;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import org.springframework.stereotype.Service;

/**
 * xx
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/12/16 09:59
 */
@Service
public class ExistInterfaceImpl implements ExistInterface {
    @Override
    public User findById() {
        return null;
    }

    // 这种写法不行。 必须写在接口方法上
    @Query("select * from sys_user where id = 1 ")
    @Override
    public UserBO findByIdByBo() {
        UserBO userBO = new UserBO();
        userBO.setName("这种写法不行。 必须写在接口方法上");
        return userBO;
    }
}
