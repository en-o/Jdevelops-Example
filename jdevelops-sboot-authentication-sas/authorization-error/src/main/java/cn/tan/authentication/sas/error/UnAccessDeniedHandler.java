package cn.tan.authentication.sas.error;

import cn.jdevelops.api.result.emums.ExceptionCode;
import cn.jdevelops.api.result.emums.TokenExceptionCode;
import cn.tan.authentication.sas.error.respone.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权失败处理器
 */
public class UnAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionCode unauthenticated = TokenExceptionCode.UNAUTHENTICATED;
        if(request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken){
            ResponseUtil.exceptionResponse(response,unauthenticated);
        }else {
            ResponseUtil.exceptionResponse(response, new ExceptionCode(unauthenticated.getCode(),
                    ResponseUtil.getExceptionMessage(accessDeniedException)));
        }
    }

}
