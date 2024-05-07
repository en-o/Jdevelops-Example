package com.example.jdevelopsconfigdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author <a href="https://tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/5/4 下午10:00
 */
@RestController
public class DemoController {
    @Autowired
    private TDemoconfig tDemoconfig;

    @Value("${tan.a:tan_a_0001}")
    private String a;

    @Value("${tan.b:tan_b_0001}")
    private String b;

    @GetMapping("/demo")
    public String demo() {
        return "tt.a = " + a + "\n"
                + "tt.b = " + b + "\n"
                + "demo.a = " + tDemoconfig.getA() + "\n"
                + "demo.b = " + tDemoconfig.getB() + "\n";
    }
}
