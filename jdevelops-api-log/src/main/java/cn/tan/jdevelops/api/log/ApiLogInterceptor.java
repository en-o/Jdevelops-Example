//package cn.tan.jdevelops.api.log;
//
//
//import cn.jdevelops.interceptor.api.ApiBeforeInterceptor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 接口日志
// *
// * @author tan
// */
//@Component
//@Order(1)
//public class ApiLogInterceptor implements ApiBeforeInterceptor {
//
//    /**
//     * logback-spring.xml中定义 appender api-log 完成自定api文件输出
//     */
//    private static final Logger logger = LoggerFactory.getLogger("api-log");
//
//    @Override
//    public boolean before(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //获取请求参数
//        logger.info("获取请求参数");
//        return true;
//    }
//}
