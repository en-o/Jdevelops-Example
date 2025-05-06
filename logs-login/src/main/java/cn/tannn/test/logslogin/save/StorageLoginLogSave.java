package cn.tannn.test.logslogin.save;

import cn.tannn.jdevelops.logs.model.LoginLogRecord;
import cn.tannn.jdevelops.logs.service.LoginLogSave;
import cn.tannn.test.logslogin.storage.LoginLog;
import cn.tannn.test.logslogin.storage.LoginLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * 保存，需要时重写里面的保存方法即可实现数据自定义存入
 * 保存
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @date 2024/5/20 下午3:01
 */
@Service
public class StorageLoginLogSave implements LoginLogSave {

    @Autowired
    private LoginLogDao loginLogDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageLoginLogSave.class);

    @Override
    @Async
    public void saveLog(LoginLogRecord audit) {
//        try {
//            SignEntity<Object> tokenBySignEntity = JwtWebUtil.getTokenBySignEntity(audit.getRequest());
//            System.out.println("=======================");
//            System.out.println(tokenBySignEntity);
//            System.out.println("=======================");
//        }catch (Exception e){
//
//        }

//        LOGGER.info("{}-日志自定义输出:{}", System.currentTimeMillis(), audit.toStyle());
        loginLogDao.save(new LoginLog().value(audit));
    }
}
