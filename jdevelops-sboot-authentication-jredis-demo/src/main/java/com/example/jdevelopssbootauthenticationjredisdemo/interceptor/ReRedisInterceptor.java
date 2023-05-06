package com.example.jdevelopssbootauthenticationjredisdemo.interceptor;

import cn.jdevelops.sboot.authentication.jredis.entity.only.StorageUserTokenEntity;
import cn.jdevelops.sboot.authentication.jredis.service.JwtRedisService;
import cn.jdevelops.sboot.authentication.jwt.exception.ExpiredRedisException;
import cn.jdevelops.sboot.authentication.jwt.server.CheckTokenInterceptor;
import cn.jdevelops.spi.JoinSPI;
import cn.jdevelops.util.jwt.util.JwtContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * redis 验证token 默认的redisinterceptor可以实现完整功能，这里只是测试下能不能在实现一次
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-24 11:51
 */
@JoinSPI
public class ReRedisInterceptor implements CheckTokenInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ReRedisInterceptor.class);

    @Override
    public boolean checkToken(String token) {
        JwtRedisService JwtRedisService = JwtContextUtil.getBean(JwtRedisService.class);
        StorageUserTokenEntity storageUserTokenEntity = JwtRedisService.verifyUserTokenByToken(token);
        return Objects.nonNull(storageUserTokenEntity) && storageUserTokenEntity.getToken().equalsIgnoreCase(token);
    }
    @Override
    public void refreshToken(String userCode) {
        JwtRedisService JwtRedisService = JwtContextUtil.getBean(JwtRedisService.class);
        JwtRedisService.refreshUserToken(userCode);
    }

    /**
     * 2.0.6 版本才有的功能
     * @param userCode 用户唯一编码
     * @throws ExpiredRedisException Exception
     */
    @Override
    public void checkUserStatus(String userCode) throws ExpiredRedisException {
        JwtRedisService JwtRedisService = JwtContextUtil.getBean(JwtRedisService.class);
        JwtRedisService.verifyUserStatus(userCode);
    }
}
