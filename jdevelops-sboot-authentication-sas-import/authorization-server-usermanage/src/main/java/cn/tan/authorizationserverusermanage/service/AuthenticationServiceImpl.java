package cn.tan.authorizationserverusermanage.service;

import cn.jdevelops.authentication.sas.server.user.entity.AuthenticationAccount;
import cn.jdevelops.authentication.sas.server.user.service.AuthenticationService;
import cn.tan.authorizationserverusermanage.dao.UserInfoDao;
import cn.tan.authorizationserverusermanage.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/25 13:35
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserInfoDao userInfoDao;

    public AuthenticationServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public Optional<AuthenticationAccount> findUser(String loginName) {
        AuthenticationAccount authenticationAccount = userInfoDao.findByLoginName(loginName)
                .map(UserInfo::toAuthenticationAccount)
                .orElse(null);
        return Optional.ofNullable(authenticationAccount);
    }

    @Override
    public Optional<AuthenticationAccount> findUserByPhoneNumber(String phoneNumber) {
        AuthenticationAccount authenticationAccount = userInfoDao.findByPhone(phoneNumber)
                .map(UserInfo::toAuthenticationAccount)
                .orElse(null);
        return Optional.ofNullable(authenticationAccount);
    }

    @Override
    public String findSmsCode(String phoneNumber) {
        return "8888";
    }
}
