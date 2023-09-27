package cn.tannn.jdevelopssbootjpademo.service;


import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.service.J2Service;
import cn.tannn.jdevelopssbootjpademo.controller.pojo.UpdateUser;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:24
 */
public interface UserService extends J2Service<User> {

    /**
     * 查询
     * @param userNo userNo
     * @return User
     */
    User findByUserNoCopyDao(String userNo);

    User findByUserNoCopy2Dao(String userNo);

    /**
     * 更新
     * @param user 准备更新的数据
     * @param uniqueKey  更新键
     */
    void updateBeanTest(UpdateUser user, String uniqueKey);
}
