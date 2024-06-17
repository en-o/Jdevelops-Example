//package cn.tannn.demo.jdevelops.eventswebsocket.verify;
//
//import cn.tannn.jdevelops.events.websocket.core.CommonConstant;
//import cn.tannn.jdevelops.events.websocket.service.VerifyService;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
//
///**
// * websocket连接认证
// * @author tan
// */
//@Service
//public class MyVerifyServiceImpl implements VerifyService {
//
//
//    @Override
//    public boolean verifyLogin(HttpServletRequest request) {
//
//        String servletPath = request.getServletPath();
//        if(servletPath.contains(CommonConstant.VERIFY_PATH_NO)){
//            //  不用登录直接连接
//            return true;
//        }
//        String token = request.getParameter(JwtConstant.TOKEN);
//        if(Objects.isNull(token)){
//            token = request.getHeader(JwtConstant.TOKEN);
//        }
//        return JwtService.validateTokenByBoolean(token);
//    }
//
//
//}
