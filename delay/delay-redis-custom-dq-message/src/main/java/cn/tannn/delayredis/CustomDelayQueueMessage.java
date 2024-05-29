package cn.tannn.delayredis;

import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
import lombok.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 自定义消息体
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/4/22 13:27
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CustomDelayQueueMessage extends DelayQueueMessage {

    /**
     * 数据ID
     */
    private Long dataId;

    public CustomDelayQueueMessage(Long dataId,
                                String channel,
                                Long delayTime,
                                String desc) {
        super(dataId+desc, channel, delayTime, getDateTimeStr(delayTime), desc);
        this.dataId = dataId;
    }


    /**
     * yyyy-MM-dd, HH:mm:ss 格式的字符串转为long
     * @param timestamp 时间戳
     * @return long
     */
    public static String getDateTimeStr(Long timestamp) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.print(timestamp);
    }


}
