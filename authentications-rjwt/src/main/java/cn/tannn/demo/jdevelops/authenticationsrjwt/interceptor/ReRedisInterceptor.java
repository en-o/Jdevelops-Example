package cn.tannn.demo.jdevelops.authenticationsrjwt.interceptor;

import cn.tannn.jdevelops.jwt.redis.entity.only.StorageToken;
import cn.tannn.jdevelops.jwt.redis.service.RedisToken;
import cn.tannn.jdevelops.jwt.redis.service.RedisUserState;
import cn.tannn.jdevelops.jwt.standalone.exception.ExpiredRedisException;
import cn.tannn.jdevelops.jwt.standalone.service.CheckTokenInterceptor;
import cn.tannn.jdevelops.spi.JoinSPI;
import cn.tannn.jdevelops.utils.jwt.util.JwtContextUtil;
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
        LOG.info("自定义 checkToken");
        RedisToken jwtRedisService = JwtContextUtil.getBean(RedisToken.class);
        StorageToken storageToken = jwtRedisService.verifyByToken(token);
        return Objects.nonNull(storageToken) && storageToken.getToken().equalsIgnoreCase(token);
    }
    @Override
    public void refreshToken(String token) {
        LOG.info("自定义 refreshToken");
        RedisToken redisToken = JwtContextUtil.getBean(RedisToken.class);
        redisToken.refreshByToken(token);
    }

    /**
     * @param token token
     * @throws ExpiredRedisException Exception
     */
    @Override
    public void checkUserStatus(String token) throws ExpiredRedisException {
        LOG.info("自定义 checkUserStatus");
        RedisUserState redisUserState = JwtContextUtil.getBean(RedisUserState.class);
        redisUserState.verifyByToken(token);
    }
}
