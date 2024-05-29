package cn.tannn.delayredis.execute;

import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
import cn.tannn.jdevelops.delays.core.execute.DelayExecute;
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
@Service(RedisDelayMessageChannel.PAY)
@RequiredArgsConstructor
public class PayDelayExecute implements DelayExecute {

    private static final Logger logger = LoggerFactory.getLogger(PayDelayExecute.class);


    @Override
    public String channel() {
        return RedisDelayMessageChannel.PAY;
    }

    @Override
    public void delayExecute(DelayQueueMessage delayMessage) {
        logger.info(RedisDelayMessageChannel.PAY+" => 定时任务开始执行: getBody:{},channel:{}, time:{}",
                delayMessage.getBody(),
                delayMessage.getChannel(),
                delayMessage.getDelayTimeStr());
    }


}
