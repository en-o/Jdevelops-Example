package cn.tannn.delayjdk.controller;

import cn.jdevelops.delay.core.service.DelayService;
import cn.jdevelops.delay.task.DelayTask;
import cn.tannn.delayjdk.constant.JdkDelayMessageChannel;
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
@Api(tags = {"测试jdk延时"})
public class DelayController {
    @Autowired
    private DelayService<DelayTask> jdkDelayService;

    @ApiOperation("开始消费延时队列")
    @GetMapping("consume")
    public String consume() {
        jdkDelayService.consumeDelay();
        return "开始消费延时队列数据...";
    }



    @ApiOperation("生产延时队列数据")
    @GetMapping("produce")
    public String produce(Long timeMillis)  {
        Long paramTime = timeMillis == null ? System.currentTimeMillis() : timeMillis;
        System.out.println(paramTime);
        //  填充延时队列数据
        List<DelayTask> delayTasks = Arrays.asList(
                new DelayTask("test1", JdkDelayMessageChannel.PAY,
                        paramTime+(10*1000),new Date(paramTime+(10*1000)).toString(),""),
                new DelayTask("test2",JdkDelayMessageChannel.PAY,
                        paramTime+(20*1000),new Date(paramTime+(20*1000)).toString(),""),
                new DelayTask("test3",JdkDelayMessageChannel.PAY,
                        paramTime+(30*1000),new Date(paramTime+(30*1000)).toString(),""),
                new DelayTask("test4",JdkDelayMessageChannel.PAY,
                        paramTime+(40*1000),new Date(paramTime+(40*1000)).toString(),""),
                new DelayTask("test5",JdkDelayMessageChannel.PAY,
                        paramTime+(50*1000),new Date(paramTime+(50*1000)).toString(),"")
        );
        jdkDelayService.produce(delayTasks);
        return "生产延时队列数据...";
    }
}
