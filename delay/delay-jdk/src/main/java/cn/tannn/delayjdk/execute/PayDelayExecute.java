package cn.tannn.delayjdk.execute;

import cn.jdevelops.delay.core.entity.DelayQueueMessage;
import cn.jdevelops.delay.core.execute.DelayExecute;
import cn.tannn.delayjdk.constant.JdkDelayMessageChannel;
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
@Service(JdkDelayMessageChannel.PAY)
@RequiredArgsConstructor
public class PayDelayExecute implements DelayExecute {

    private static final Logger logger = LoggerFactory.getLogger(PayDelayExecute.class);


    @Override
    public String channel() {
        return JdkDelayMessageChannel.PAY;
    }

    @Override
    public void delayExecute(DelayQueueMessage delayMessage) {
        logger.info(JdkDelayMessageChannel.PAY+" => 定时任务开始执行: getBody:{},channel:{}, time:{}",
                delayMessage.getBody(),
                delayMessage.getChannel(),
                delayMessage.getDelayTimeStr());
    }


}
