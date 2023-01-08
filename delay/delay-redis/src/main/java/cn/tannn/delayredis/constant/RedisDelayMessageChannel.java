package cn.tannn.delayredis.constant;

/**
 * 延迟队列类型
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-01-04 15:39
 */
public interface RedisDelayMessageChannel {

    /**
     * 支付 延迟通知
     */
    String PAY = "pay";

    /**
     * 活动 延迟通知
     */
    String ACTIVITY = "activity";

}
