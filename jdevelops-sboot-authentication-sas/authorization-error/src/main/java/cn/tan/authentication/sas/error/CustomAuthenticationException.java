package cn.tan.authentication.sas.error;


import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常类
 * @author tan
 */
public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
