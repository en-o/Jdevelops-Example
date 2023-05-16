package cn.tannn.delayjdk;

import cn.jdevelops.delay.core.service.DelayService;
import cn.jdevelops.delay.jdk.task.DelayTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author tnnn
 * @version V1.0
 * @date 2023-01-08 00:08
 */
@SpringBootApplication

public class DelayJdkApplication  implements ApplicationRunner {

    @Autowired
    private DelayService<DelayTask> delayService;


    public static void main(String[] args) {
        SpringApplication.run(DelayJdkApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args)  {
        // 启动就开始消费延迟队列数据
        delayService.consumeDelay();
    }
}
