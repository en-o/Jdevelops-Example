package cn.tannn.delayredis.execute;

import cn.jdevelops.delay.core.entity.DelayQueueMessage;
import cn.jdevelops.delay.core.execute.DelayExecute;
import cn.tannn.delayredis.CustomDelayQueueMessage;
import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 支付通知
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-01-04 15:59
 */
@Service(RedisDelayMessageChannel.FAIL)
@RequiredArgsConstructor
public class FailDelayExecute_custom_dq_message implements DelayExecute<CustomDelayQueueMessage> {

    private static final Logger logger = LoggerFactory.getLogger(FailDelayExecute_custom_dq_message.class);


    @Override
    public String channel() {
        return RedisDelayMessageChannel.FAIL;
    }

    @Override
    public void delayExecute(CustomDelayQueueMessage delayMessage) {
        logger.info(RedisDelayMessageChannel.FAIL+" => 定时任务开始执行: getBody:{},channel:{}, time:{} , str: {}",
                delayMessage.getBody(),
                delayMessage.getChannel(),
                delayMessage.getDelayTimeStr(),
                delayMessage);
    }


}
