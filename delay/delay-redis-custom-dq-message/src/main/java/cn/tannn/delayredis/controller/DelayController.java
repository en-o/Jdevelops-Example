package cn.tannn.delayredis.controller;

import cn.jdevelops.delay.core.service.DelayService;
import cn.tannn.delayredis.CustomDelayQueueMessage;
import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 延时
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-01-08 00:18
 */
@RestController
@Tag(name = "测试redis延时")
public class DelayController {
    @Autowired
    private DelayService<CustomDelayQueueMessage> delayService;

    @Operation(summary = "开始消费延时队列")
    @GetMapping("consume")
    public String consume() {
        delayService.consumeDelay();
        return "开始消费延时队列数据...";
    }


    @Operation(summary = "生产延时队列数据,测试自定义消息体")
    @GetMapping("produce/custom_message")
    @Parameter(name = "timeMillis",description = "延时消费的时间(时间戳<毫秒>)(默认当前+n/s)")
    @Parameter(name = "dataId",description = "数据ID")
    public String produce_custom_message(@RequestParam("dataId") Long dataId, Long timeMillis) {
        Long paramTime = timeMillis == null ? System.currentTimeMillis() : timeMillis;
        //  填充延时队列数据
        delayService.produce( new CustomDelayQueueMessage(dataId, RedisDelayMessageChannel.FAIL,
                paramTime + (10 * 1000), "测试自定义消息体"));
        return "生产延时队列数据... => "+paramTime;
    }
}
