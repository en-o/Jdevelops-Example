package cn.jdevelops.build.interceptor;

import cn.jdevelops.jredis.entity.LoginTokenRedis;
import cn.jdevelops.jredis.exception.ExpiredRedisException;
import cn.jdevelops.jredis.interceptor.RedisInterceptor;
import cn.jdevelops.jredis.service.RedisService;
import cn.jdevelops.jwt.util.ContextUtil;
import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.jwtweb.server.CheckTokenInterceptor;
import cn.jdevelops.spi.JoinSPI;
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
        RedisService redisService = ContextUtil.getBean(RedisService.class);
        LoginTokenRedis loginTokenRedis = redisService.verifyUserTokenByToken(token);
        return Objects.nonNull(loginTokenRedis) && loginTokenRedis.getToken().equalsIgnoreCase(token);
    }
    @Override
    public void refreshToken(String userCode) {
        RedisService redisService = ContextUtil.getBean(RedisService.class);
        redisService.refreshUserToken(userCode);
    }

    /**
     * 2.0.6 版本才有的功能
     * @param userCode 用户唯一编码
     * @throws ExpiredRedisException Exception
     */
    @Override
    public void checkUserStatus(String userCode) throws ExpiredRedisException {
        RedisService redisService = ContextUtil.getBean(RedisService.class);
        redisService.verifyUserStatus(userCode);
    }
}
