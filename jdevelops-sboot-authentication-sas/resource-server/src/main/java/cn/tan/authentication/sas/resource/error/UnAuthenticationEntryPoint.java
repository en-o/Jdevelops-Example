package cn.tan.authentication.sas.resource.error;

import cn.hutool.json.JSONUtil;
import cn.jdevelops.api.result.emums.TokenExceptionCode;
import cn.jdevelops.api.result.response.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(UnAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof InvalidBearerTokenException) {
            logger.info("Token 登录失效");
        }

        if (response.isCommitted()) {
            return;
        }

        // 401, 未认证
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 返回响应信息
        ServletOutputStream outputStream = response.getOutputStream();
//        Response fail = Response.fail(HttpServletResponse.SC_UNAUTHORIZED,
//                authException.getMessage() + "-UnAuthenticationEntryPoint-认证失败", "uri-" + request.getRequestURI());
        ResultVO<String> fail = ResultVO.of(TokenExceptionCode.TOKEN_ERROR);
        outputStream.write(JSONUtil.toJsonStr(fail).getBytes(StandardCharsets.UTF_8));
        // 关闭流
        outputStream.flush();
        outputStream.close();
    }
}
