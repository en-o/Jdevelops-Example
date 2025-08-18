package cn.tannn.demo.renewpwd;

import cn.tannn.demo.renewpwd.service.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * 定时测试查询
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/8/11 22:29
 */
//@Component
public class Timer {

    @Autowired
    private TestDemoService testDemoService;
    @Autowired
    private Environment environment;


    @Scheduled(fixedRate = 1100)
    public void jpa(){
        System.out.println("=============jpa 定时任务执行中================================================");
        System.out.println("============="+environment.getProperty("spring.datasource.password")+"================================================");
        System.out.println(testDemoService.jpa2());
    }

}
