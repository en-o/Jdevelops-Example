package cn.tannn.demo;

import cn.tannn.jdevelops.log.audit.AuditContext;
import cn.tannn.jdevelops.log.audit.service.AuditSave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/2/6 14:41
 */
@Service
@Slf4j
public class StorageRecord implements AuditSave {
    @Override
    public void saveLog(AuditContext audit) {
        log.info("重置存储方式{}", audit.toString());
    }
}
