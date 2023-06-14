package cn.tannn.jdevelopssbootjpademo.service.impl;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
import cn.tannn.jdevelopssbootjpademo.dao.UserDao;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:41
 */
@Slf4j
@Service
public class UserServiceImpl extends J2ServiceImpl<UserDao, User, Integer> implements UserService {


    @Autowired
    private UserDao userDao;


    @Override
    public boolean deleteTest(Specification specifications) {
//         getJpaBasicsDao().delete(specifications)>=0;
        return true;
    }

    @Override
    public User findByUserNoCopyDao(String userNo) {
        return userDao.findByUserNo(userNo);
    }

    @Override
    public User findByUserNoCopy2Dao(String userNo) {
        return getJpaBasicsDao().findByUserNo(userNo);
    }
}
