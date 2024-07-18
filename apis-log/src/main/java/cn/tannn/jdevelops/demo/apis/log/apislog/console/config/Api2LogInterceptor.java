package cn.tannn.jdevelops.demo.apis.log.apislog.console.config;

import cn.tannn.jdevelops.apis.log.constants.ApiLogConstants;
import cn.tannn.jdevelops.apis.log.module.LoggerPrint;
import cn.tannn.jdevelops.apis.log.util.IpUtil;
import cn.tannn.jdevelops.webs.interceptor.ApiBeforeInterceptor;
import cn.tannn.jdevelops.webs.interceptor.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.util.Objects;

/**
 * 接口日志
 *
 * @author tan
 */
//@Component
@Order(2)
public class Api2LogInterceptor implements ApiBeforeInterceptor {

    /**
     * logback-spring.xml中定义 appender api-log 完成自定api文件输出
     */
    private static final Logger logger = LoggerFactory.getLogger(ApiLogConstants.LOGGER_NAME);

    @Override
    public boolean before(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求参数
        LoggerPrint loggerEntity = null;
        try {
            String requestParams = RequestUtil.requestParams(request);
            loggerEntity = new LoggerPrint(IpUtil.httpRequestIp(request),
                    request.getRequestURL().toString()
                    , request.getMethod()
                    , requestParams
                    , System.currentTimeMillis()
            );
        }catch (Exception e){
            logger.error("接口日志记录失败", e);
        }
        logger.info("aa{}",Objects.isNull(loggerEntity)? "" : loggerEntity.toString());
        return true;
    }
}
