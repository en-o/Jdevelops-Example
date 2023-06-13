package cn.tannn.jdevelopssbootjpademo.pg;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
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
public class UserPgServiceImpl extends J2ServiceImpl<UserPgDao, UserPgsql, Integer> implements UserPgService {

}
