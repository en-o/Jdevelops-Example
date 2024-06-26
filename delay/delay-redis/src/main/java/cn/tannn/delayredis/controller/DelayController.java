package cn.tannn.delayredis.controller;

import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
import cn.tannn.jdevelops.delays.core.service.DelayService;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private DelayService<DelayQueueMessage> delayService;

    @Operation(summary = "开始消费延时队列")
    @GetMapping("consume")
    public String consume() {
        delayService.consumeDelay();
        return "开始消费延时队列数据...";
    }

    String   body5Str = "";

    @Operation(summary = "生产延时队列数据")
    @GetMapping("produce")
    @Parameter(name = "timeMillis",description = "延时消费的时间(时间戳<毫秒>)(默认当前+n/s)")
    public String produce(Long timeMillis) {
        Long paramTime = timeMillis == null ? System.currentTimeMillis() : timeMillis;
        //  填充延时队列数据
        DelayQueueMessage body5 = new DelayQueueMessage("body5", RedisDelayMessageChannel.ACTIVITY,
                paramTime + (50 * 1000), new Date(paramTime + (50 * 1000)).toString(), "");
        body5Str = JSON.toJSONString(body5);
        List<DelayQueueMessage> delayTasks = Arrays.asList(
                new DelayQueueMessage("body1", RedisDelayMessageChannel.ACTIVITY,
                        paramTime + (10 * 1000), new Date(paramTime + (10 * 1000)).toString(), ""),
                new DelayQueueMessage("body2", RedisDelayMessageChannel.ACTIVITY,
                        paramTime + (20 * 1000), new Date(paramTime + (20 * 1000)).toString(), ""),
                new DelayQueueMessage("body3", RedisDelayMessageChannel.PAY,
                        paramTime + (30 * 1000), new Date(paramTime + (30 * 1000)).toString(), ""),
                new DelayQueueMessage("body4", RedisDelayMessageChannel.PAY,
                        paramTime + (40 * 1000), new Date(paramTime + (40 * 1000)).toString(), ""),
                body5
                );
        delayService.produce(delayTasks);
        return "生产延时队列数据... => "+paramTime;
    }


    @Operation(summary = "取消队列")
    @GetMapping("cancel")
    public String cancel() {
        delayService.cancel(body5Str);
        return "取消队列...";
    }

}
