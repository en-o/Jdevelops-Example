package cn.tannn.delayjdk;


import cn.jdevelops.delay.core.service.DelayService;
import cn.jdevelops.delay.task.DelayTask;
import cn.tannn.delayjdk.constant.JdkDelayMessageChannel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2023-01-08 00:08
 */
@SpringBootApplication

public class DelayJdkApplication {



    public static void main(String[] args) {
        SpringApplication.run(DelayJdkApplication.class, args);
    }




}
