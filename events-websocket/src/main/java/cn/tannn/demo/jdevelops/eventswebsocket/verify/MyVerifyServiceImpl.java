package cn.tannn.demo.jdevelops.eventswebsocket.verify;

import cn.tannn.jdevelops.events.websocket.config.WebSocketConfig;
import cn.tannn.jdevelops.events.websocket.core.CommonConstant;
import cn.tannn.jdevelops.events.websocket.service.VerifyService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * 自定义websocket连接认证
 * @author tan
 */
//@Service
public class MyVerifyServiceImpl implements VerifyService {


    private final WebSocketConfig webSocketConfig;


    public MyVerifyServiceImpl(WebSocketConfig webSocketConfig) {
        this.webSocketConfig = webSocketConfig;
    }

    @Override
    public boolean verifyLogin(HttpServletRequest request) {
        // 默认配置
        String servletPath = request.getServletPath();
        if(servletPath.contains(CommonConstant.VERIFY_PATH_NO)){
            //  不用登录直接连接
            return true;
        }
        // 获取密钥
        String token = request.getParameter(webSocketConfig.getTokenName());
        if(Objects.isNull(token)){
            token = request.getHeader(webSocketConfig.getTokenName());
        }
        // 验证密钥
        return "tt123".equals(token);
    }


}
