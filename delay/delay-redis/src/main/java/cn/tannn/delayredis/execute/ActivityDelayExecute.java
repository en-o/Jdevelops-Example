package cn.tannn.delayredis.execute;

import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
import cn.tannn.jdevelops.delays.core.execute.DelayExecute;
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
@Service(RedisDelayMessageChannel.ACTIVITY)
@RequiredArgsConstructor
public class ActivityDelayExecute implements DelayExecute {

    private static final Logger logger = LoggerFactory.getLogger(ActivityDelayExecute.class);


    @Override
    public String channel() {
        return RedisDelayMessageChannel.ACTIVITY;
    }

    @Override
    public void delayExecute(DelayQueueMessage delayMessage) {
        logger.info(RedisDelayMessageChannel.ACTIVITY+" => 定时任务开始执行: getBody:{},channel:{}, time:{}",
                delayMessage.getBody(),
                delayMessage.getChannel(),
                delayMessage.getDelayTimeStr());
    }


}
