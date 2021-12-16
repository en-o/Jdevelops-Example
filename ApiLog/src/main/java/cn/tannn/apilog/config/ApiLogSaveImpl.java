package cn.tannn.apilog.config;

import cn.jdevelops.apilog.bean.ApiMonitoring;
import cn.jdevelops.apilog.server.ApiLogSave;
import org.springframework.stereotype.Component;

/**
 * 重新实现ApiLog日志保存方法
 *
 * @author tn
 * @version 1
 * @date 2021-12-16 12:34
 */
@Component
public class ApiLogSaveImpl implements ApiLogSave {
    @Override
    public void saveLog(ApiMonitoring apilog) {
        System.out.println(apilog.toString());
    }
}
