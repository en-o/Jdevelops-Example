package cn.tannn.demo;

import cn.tannn.jdevelops.log.audit.AuditContext;
import cn.tannn.jdevelops.log.audit.AuditContextHolder;
import cn.tannn.jdevelops.log.audit.BatchAuditContext;
import cn.tannn.jdevelops.log.audit.annotations.AuditLog;
import cn.tannn.jdevelops.log.audit.constant.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志记录
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/2/6 14:16
 */
@RestController
public class LogRecordController {

    @GetMapping("hi")
    @AuditLog(auditType = "测试"
            , operationType = OperationalType.DELETE
            , description = "测试"
    )
    public String hi() {
        AuditContextHolder.getContext().setUniqueCode("1")
                .setDataTitle("测试")
                .setUniqueIndexType(UniqueIndexType.MYSQL)
                .setUniqueIndex("数据表一")
                .appendOptUser("tan", "tan");
        return "不上班就没钱";
    }


    @GetMapping("batch")
    @AuditLog(auditType = "batch"
            , operationType = OperationalType.DELETE
            , description = "batch"
            , batch = true
    )
    public String batch() {
        BatchAuditContext batchContext = AuditContextHolder.getBatchContext();
        for (int i = 0; i < 2; i++) {
            AuditContext auditContext = new AuditContext().setUniqueCode("1")
                    .setDataTitle("测试batch" + i)
                    .setUniqueIndexType(UniqueIndexType.MYSQL)
                    .setUniqueIndex("数据表二")
                    .appendOptUser("tan", "tan");
            batchContext.addContext(auditContext);
        }
        return "不上班就没钱";
    }
}
