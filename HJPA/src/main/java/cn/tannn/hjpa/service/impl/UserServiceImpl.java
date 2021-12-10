package cn.tannn.hjpa.service.impl;

import cn.jdevelops.jpa.server.service.impl.J2ServiceImpl;
import cn.tannn.hjpa.dao.UserDao;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
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
