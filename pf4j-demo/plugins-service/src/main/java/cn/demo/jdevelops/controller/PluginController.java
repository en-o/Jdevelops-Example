package cn.demo.jdevelops.controller;

import cn.tannn.demo.pf4j.greeting.api.Greeting;
import cn.tannn.jdevelops.pf4j.service.PluginService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


/**
 * 插件控制器
 *
 * 作者 <a href="https://t.tannn.cn/">tan</a>
 * 版本 V1.0
 * 日期 2024/8/1 上午9:22
 */
@Component
public class PluginController extends cn.tannn.jdevelops.pf4j.controller.PluginController {

    @Autowired
    private PluginService pluginService;


    /**
     * 执行插件方法
     */
    @Operation(summary = "打印所有插件的问候信息")
    @GetMapping("/greetings")
    public void printGreetings() {
        pluginService.extensions(Greeting.class).forEach(x -> System.out.println(x.getGreeting()));
    }

    /**
     * 执行指定插件ID的方法
     */
    @Operation(summary = "打印指定插件ID的问候信息")
    @GetMapping("/greetings/{pluginId}")
    public void greetings(@PathVariable String pluginId) {
        pluginService.extensions(Greeting.class,pluginId).forEach(x -> System.out.println(x.getGreeting()));
    }

}
