package cn.tannn.jdevelops.demo.apis.log.apislog.save.config;

import cn.tannn.jdevelops.apis.log.ApiLogSave;
import cn.tannn.jdevelops.apis.log.module.ApiMonitoring;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 重新实现ApiLog日志保存方法
 *
 * @author tn
 * @version 1
 * @date 2021-12-16 12:34
 */
//@Service
@Slf4j
public class ApiLogSaveImpl implements ApiLogSave {
    @Override
    public void saveLog(ApiMonitoring apilog) {
       log.info("你可以做入库操作同时也可做数据库打印操作："+apilog.toString());
    }
}
