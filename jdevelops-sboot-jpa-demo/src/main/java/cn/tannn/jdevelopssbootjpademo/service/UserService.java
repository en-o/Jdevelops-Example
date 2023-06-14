package cn.tannn.jdevelopssbootjpademo.service;


import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.service.J2Service;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:24
 */
public interface UserService extends J2Service<User> {


    boolean deleteTest(Specification spec);



    /**
     * 查询
     * @param userNo userNo
     * @return User
     */
    User findByUserNoCopyDao(String userNo);

    User findByUserNoCopy2Dao(String userNo);
}
