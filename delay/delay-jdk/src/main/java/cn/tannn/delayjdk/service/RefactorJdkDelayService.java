/*
package cn.tannn.delayjdk.service;

import cn.tannn.jdevelops.delays.core.factory.DelayFactory;
import cn.tannn.jdevelops.delays.core.service.DelayService;
import cn.tannn.jdevelops.delays.jdk.constant.DelayQueueConstant;
import cn.tannn.jdevelops.delays.jdk.task.DelayTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

*/
/**
 * 重构内置的 JdkDelayService
 * @author tnnn
 * @version V1.0
 * @date 2023-01-05 16:34
 *//*

@Service
@Slf4j
public class RefactorJdkDelayService implements DelayService<DelayTask> {


    @Resource
    private DelayFactory<DelayTask> delayFactory;

    */
/**
     * 线程池
     *//*

    private static final String NAME = "RefactorJdkDelayMessageTask-thread-";
    private final AtomicInteger seq = new AtomicInteger(1);
    private final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1, r ->
            new Thread(r, NAME + seq.getAndIncrement()));


    @Override
    public void produce(DelayTask delayMessage) {
        DelayQueueConstant.DELAY_QUEUE.put(delayMessage);
    }

    @Override
    public void produce(List<DelayTask> delayMessage) {
        // 定义一个延时队列
        DelayQueue<DelayTask> queue = DelayQueueConstant.DELAY_QUEUE;
        //  存在且相等的不在加入队列 - 还没写
        delayMessage.forEach(queue::put);
    }

    @Override
    public void cancel(String delayMessage) {
        log.warn("===> jdk delay 暂不支持取消操作");
    }

    @Override
    public void cancel(DelayTask delayMessage) {
        log.warn("===> jdk delay 暂不支持取消操作");
    }


    @Override
    public void consumeDelay() {
        // IllegalArgumentException 的话 initialDelay = 1， period = 1 直接写死
        // 初始化
        long initialDelay =Math.round(Math.random()*10+10);
        // 周期 周期小于或等于零时会抛异常
        long period = Math.round(Math.random()*10);
        log.info("重构。开始消费延时队列数据...");
        pool.scheduleAtFixedRate(()->{
            try {
                DelayQueue<DelayTask> queue  = DelayQueueConstant.DELAY_QUEUE;
                if (!queue.isEmpty()){
                    DelayTask delayTask = queue.take();
                    delayFactory.delayExecute(delayTask);
                }
            }catch (Exception e){
                log.error("execute function error..",e);
            }
        }, initialDelay,period, TimeUnit.SECONDS);
    }
}
*/
