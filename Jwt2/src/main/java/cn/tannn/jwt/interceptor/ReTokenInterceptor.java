package cn.tannn.jwt.interceptor;

import cn.jdevelops.jwt.util.ContextUtil;
import cn.jdevelops.jwtweb.server.CheckTokenInterceptor;
import cn.jdevelops.spi.JoinSPI;
import cn.tannn.jwt.service.TokenVerify;

/**
 * redis 验证token
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-24 11:51
 */
@JoinSPI
public class ReTokenInterceptor implements CheckTokenInterceptor {

    @Override
    public boolean checkToken(String token) {
        TokenVerify tokenVerify = ContextUtil.getBean(TokenVerify.class);
        return tokenVerify.verity(token);
    }

    @Override
    public void refreshToken(String userCode) {
        TokenVerify tokenVerify = ContextUtil.getBean(TokenVerify.class);
        tokenVerify.refreshUserToken(userCode);
    }
}
