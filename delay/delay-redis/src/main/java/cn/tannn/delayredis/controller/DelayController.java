package cn.tannn.delayredis.controller;

import cn.jdevelops.delay.core.entity.DelayQueueMessage;
import cn.jdevelops.delay.core.service.DelayService;
import cn.tannn.delayredis.constant.RedisDelayMessageChannel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"测试redis延时"})
public class DelayController {
    @Autowired
    private DelayService<DelayQueueMessage> delayService;

    @ApiOperation("开始消费延时队列")
    @GetMapping("consume")
    public String consume() {
        delayService.consumeDelay();
        return "开始消费延时队列数据...";
    }


    @ApiOperation("生产延时队列数据")
    @GetMapping("produce")
    public String produce(Long timeMillis) {
        Long paramTime = timeMillis == null ? System.currentTimeMillis() : timeMillis;
        //  填充延时队列数据
        List<DelayQueueMessage> delayTasks = Arrays.asList(
                new DelayQueueMessage("body1", RedisDelayMessageChannel.ACTIVITY,
                        paramTime + (10 * 1000), new Date(paramTime + (10 * 1000)).toString(), ""),
                new DelayQueueMessage("body2", RedisDelayMessageChannel.ACTIVITY,
                        paramTime + (20 * 1000), new Date(paramTime + (20 * 1000)).toString(), ""),
                new DelayQueueMessage("body3", RedisDelayMessageChannel.PAY,
                        paramTime + (30 * 1000), new Date(paramTime + (30 * 1000)).toString(), ""),
                new DelayQueueMessage("body4", RedisDelayMessageChannel.PAY,
                        paramTime + (40 * 1000), new Date(paramTime + (40 * 1000)).toString(), ""),
                new DelayQueueMessage("body5", RedisDelayMessageChannel.ACTIVITY,
                        paramTime + (50 * 1000), new Date(paramTime + (50 * 1000)).toString(), "")
                );
        delayService.produce(delayTasks);
        return "生产延时队列数据... => "+paramTime;
    }
}
