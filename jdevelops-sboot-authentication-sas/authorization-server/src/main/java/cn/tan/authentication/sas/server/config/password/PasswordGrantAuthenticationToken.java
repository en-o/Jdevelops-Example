package cn.tan.authentication.sas.server.config.password;

import cn.tan.authentication.sas.server.constant.OAuth2Constant;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * 密码模式（自定义） - 自定义授权码
 * @author tan
 */
public class PasswordGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    public PasswordGrantAuthenticationToken(Authentication clientPrincipal,
                                            @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(OAuth2Constant.GRANT_TYPE_PASSWORD),
                clientPrincipal, additionalParameters);
    }
}
