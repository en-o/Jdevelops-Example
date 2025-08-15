package cn.tannn.demo.renewpwd;

import cn.tannn.demo.renewpwd.service.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
