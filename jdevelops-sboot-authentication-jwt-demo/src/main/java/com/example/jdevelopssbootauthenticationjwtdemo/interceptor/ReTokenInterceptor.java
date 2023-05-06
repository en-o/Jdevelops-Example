package com.example.jdevelopssbootauthenticationjwtdemo.interceptor;

import cn.jdevelops.sboot.authentication.jwt.server.CheckTokenInterceptor;
import cn.jdevelops.spi.JoinSPI;
import cn.jdevelops.util.jwt.util.JwtContextUtil;
import com.example.jdevelopssbootauthenticationjwtdemo.service.TokenVerify;

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
        TokenVerify tokenVerify = JwtContextUtil.getBean(TokenVerify.class);
        return tokenVerify.verity(token);
    }

    @Override
    public void refreshToken(String userCode) {
        TokenVerify tokenVerify = JwtContextUtil.getBean(TokenVerify.class);
        tokenVerify.refreshUserToken(userCode);
    }
}
