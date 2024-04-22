package cn.tannn.delayredis;

import cn.jdevelops.delay.core.service.DelayService;
import cn.jdevelops.delay.redis.RedisDelayService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author tnnn
 */
@SpringBootApplication
public class CDelayRedisApplication implements ApplicationRunner {

    private RedisDelayService<CustomDelayQueueMessage> redisDelayService;

    public static void main(String[] args) {
        SpringApplication.run(CDelayRedisApplication.class, args);
    }

    /**
     * 重新加载 bean
     * @return DelayService
     */
    @Bean
    public DelayService<CustomDelayQueueMessage> delayService() {
        this.redisDelayService = new RedisDelayService<>(CustomDelayQueueMessage.class);
        return redisDelayService;
    }


    /**
     * 启动就开始消费延迟队列数据
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 启动就开始消费延迟队列数据
        redisDelayService.consumeDelay();
    }
}
