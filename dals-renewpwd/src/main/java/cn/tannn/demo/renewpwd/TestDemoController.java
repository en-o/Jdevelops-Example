package cn.tannn.demo.renewpwd;

import cn.tannn.demo.renewpwd.service.TestDemoService;
import cn.tannn.jdevelops.renewpwd.RenewPwdRefresh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 测试数据库操作
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/8/15 13:40
 */
@RestController
@RequestMapping("/test-demo")
public class TestDemoController {

    @Autowired
    private TestDemoService testDemoService;

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private RenewPwdRefresh refresh;


    @GetMapping("/environmentPassword")
    public Object environmentPassword(){
       return environment.getProperty("spring.datasource.password");
    }

    @GetMapping("/jdbc")
    public Object jdbcTemplateQuery() {
        return testDemoService.jdbcTemplateQuery();
    }

    @GetMapping("/named-jdbc")
    public Object namedParameterJdbcTemplateQuery() {
        return testDemoService.namedParameterJdbcTemplateQuery();
    }

    @GetMapping("/jpa")
    public Object jpa() {
        return testDemoService.jpa();
    }


    @GetMapping("/fixPassword")
    public Object fixPassword() {
         refresh.fixPassword();
         return "修改成功";
    }
}
