package cn.demo.jdevelops.controller;

import cn.demo.jdevelops.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 插件控制器
 *
 * 作者 <a href="https://t.tannn.cn/">tan</a>
 * 版本 V1.0
 * 日期 2024/8/1 上午9:22
 */
@RestController
@RequestMapping("/api/plugins")
@Tag(name = "PluginController", description = "管理和执行插件")
public class PluginController {

    @Autowired
    private GreetingService greetingService;


    /**
     * 执行插件方法
     */
    @Operation(summary = "打印所有插件的问候信息")
    @GetMapping("/greetings")
    public void printGreetings() {
        greetingService.printGreetings();
    }

    /**
     * 执行指定插件ID的方法
     */
    @Operation(summary = "打印指定插件ID的问候信息")
    @GetMapping("/greetings/{pluginId}")
    public void greetings(@PathVariable String pluginId) {
        greetingService.greetings(pluginId);
    }

    /**
     * 获取所有插件
     */
    @Operation(summary = "获取所有插件")
    @GetMapping
    public List<PluginDescriptor> getPlugins() {
        return greetingService.greetings().stream().map(PluginWrapper::getDescriptor).collect(Collectors.toList());
    }

    /**
     * 启动插件
     */
    @Operation(summary = "启动插件")
    @PostMapping("/start/{pluginId}")
    public PluginState startPlugin(@PathVariable String pluginId) {
        return greetingService.startPlugin(pluginId);
    }

    /**
     * 启用插件
     */
    @Operation(summary = "启用插件")
    @PostMapping("/enable/{pluginId}")
    public boolean enablePlugin(@PathVariable String pluginId) {
        return greetingService.enablePlugin(pluginId);
    }

    /**
     * 停止插件
     */
    @Operation(summary = "停止插件")
    @PostMapping("/stop/{pluginId}")
    public PluginState stopPlugin(@PathVariable String pluginId) {
        return greetingService.stopPlugin(pluginId);
    }

    /**
     * 禁用插件
     */
    @Operation(summary = "禁用插件")
    @PostMapping("/disable/{pluginId}")
    public boolean disablePlugin(@PathVariable String pluginId) {
        return greetingService.disablePlugin(pluginId);
    }

    /**
     * 加载插件
     */
    @Operation(summary = "加载插件")
    @PostMapping("/load")
    public String loadPlugin(@RequestParam String pluginPath) {
        return greetingService.loadPlugin(pluginPath);
    }

    /**
     * 卸载插件
     */
    @Operation(summary = "卸载插件")
    @PostMapping("/unload/{pluginId}")
    public boolean unloadPlugin(@PathVariable String pluginId) {
        return greetingService.unloadPlugin(pluginId);
    }

    /**
     * 获取插件路径
     */
    @Operation(summary = "获取插件路径")
    @GetMapping("/roots")
    public List<Path> pluginsRoots() {
        return greetingService.pluginsRoots();
    }
}
