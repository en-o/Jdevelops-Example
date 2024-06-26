package cn.tannn.demo.jdevelops.authenticationsjwt.interceptor;


import cn.tannn.jdevelops.jwt.standalone.service.CheckTokenInterceptor;
import cn.tannn.jdevelops.spi.JoinSPI;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        LOG.info("========> 定义token验证");
        try {
            return JwtService.validateTokenByBoolean(token);
        }catch (Exception e){
            LOG.error("验证token",e);
        }
        return false;
    }

}
