package cn.tannn.delayredis;

import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
import cn.tannn.jdevelops.delays.core.service.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tnnn
 */
@SpringBootApplication
public class DelayRedisApplication implements ApplicationRunner {

    @Autowired
    private DelayService<DelayQueueMessage> redisDelayService;

    public static void main(String[] args) {
        SpringApplication.run(DelayRedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 启动就开始消费延迟队列数据
        redisDelayService.consumeDelay();
    }
}
