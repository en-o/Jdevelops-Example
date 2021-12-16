package cn.tannn.testwebjpa.service.impl;

import cn.jdevelops.jpa.server.service.impl.J2ServiceImpl;
import cn.tannn.testwebjpa.dao.UserDao;
import cn.tannn.testwebjpa.entity.User;
import cn.tannn.testwebjpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

}
