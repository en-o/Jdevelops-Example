//package cn.tan.jdevelops.api.log.console.config;
//
//import cn.jdevelops.api.log.constants.ApiLogConstants;
//import cn.jdevelops.api.log.entity.LoggerEntity;
//import cn.jdevelops.util.http.IpUtil;
//import cn.jdevelops.util.interceptor.api.ApiBeforeInterceptor;
//import cn.jdevelops.util.interceptor.util.RequestUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Objects;
//
///**
// * 接口日志
// *
// * @author tan
// */
//@Component
//@Order(2)
//public class Api2LogInterceptor implements ApiBeforeInterceptor {
//
//    /**
//     * logback-spring.xml中定义 appender api-log 完成自定api文件输出
//     */
//    private static final Logger logger = LoggerFactory.getLogger(ApiLogConstants.LOGGER_NAME);
//
//    @Override
//    public boolean before(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //获取请求参数
//        LoggerEntity loggerEntity = null;
//        try {
//            String requestParams = RequestUtil.requestParams(request);
//            loggerEntity = new LoggerEntity(IpUtil.getPoxyIpEnhance(request),
//                    request.getRequestURL().toString(), requestParams, System.currentTimeMillis());
//        }catch (Exception e){
//            logger.error("接口日志记录失败", e);
//        }
//        logger.info("aa{}",Objects.isNull(loggerEntity)? "" : loggerEntity.toString());
//        return true;
//    }
//}
