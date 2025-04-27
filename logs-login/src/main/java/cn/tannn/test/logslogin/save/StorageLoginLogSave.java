package cn.tannn.test.logslogin.save;

import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.logs.model.LoginLogRecord;
import cn.tannn.jdevelops.logs.service.DefLoginLogSave;
import cn.tannn.jdevelops.logs.service.LoginLogSave;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;


/**
 * 保存，需要时重写里面的保存方法即可实现数据自定义存入
 * 保存
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @date 2024/5/20 下午3:01
 */
@Service
@Slf4j
public class StorageLoginLogSave implements LoginLogSave {


    @Override
    public void saveLog(LoginLogRecord audit) {
        SignEntity<Object> tokenBySignEntity = JwtWebUtil.getTokenBySignEntity(audit.getRequest());
        System.out.println("=======================");
        System.out.println(tokenBySignEntity);
        System.out.println("=======================");
        log.info("{}-日志自定义输出:{}", System.currentTimeMillis(), audit.toString());
    }
}
