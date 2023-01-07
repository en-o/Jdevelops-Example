package cn.tannn.delayjdk.execute;

import cn.jdevelops.delay.core.entity.DelayQueueMessage;
import cn.jdevelops.delay.core.execute.DelayExecute;
import cn.tannn.delayjdk.constant.JdkDelayMessageChannel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 活动状态改变
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-01-04 15:59
 */
@Service(JdkDelayMessageChannel.ACTIVITY)
@RequiredArgsConstructor
public class ActivityDelayExecute implements DelayExecute {

    private static final Logger logger = LoggerFactory.getLogger(ActivityDelayExecute.class);


    @Override
    public String channel() {
        return JdkDelayMessageChannel.ACTIVITY;
    }

    @Override
    public void delayExecute(DelayQueueMessage delayMessage) {
        logger.info(JdkDelayMessageChannel.ACTIVITY+" => 定时任务开始执行: getBody:{},channel:{}, time:{}",
                delayMessage.getBody(),
                delayMessage.getChannel(),
                delayMessage.getDelayTimeStr());
    }


}
