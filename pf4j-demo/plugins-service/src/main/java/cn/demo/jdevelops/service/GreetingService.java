package cn.demo.jdevelops.service;

import cn.tannn.demo.pf4j.greeting.api.Greeting;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginManager;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Service;

import javax.management.Descriptor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 插件服务
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/8/1 上午9:22
 */
@Service
public class GreetingService {

    private final PluginManager pluginManager;

    public GreetingService(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    /**
     * 执行插件方法
     */
    public void printGreetings() {

        // 启动插件 - 可传入 pluginId 指定启动
//        pluginManager.startPlugins();

        // 手动获取Greeting.class扩展点的扩展
        pluginManager.getExtensions(Greeting.class).forEach(greeting ->
                System.out.println(greeting.getGreeting())
        );


        // 停止插件 - 可传入 pluginId 指定停止
//        pluginManager.stopPlugins();
    }

    /**
     * 执行指定插件ID的方法（我猜测是同一个 id, 可以有不同的版本
     */
    public void greetings(String pluginId) {
        pluginManager.getExtensions(Greeting.class, pluginId).forEach(greeting -> {
            System.out.println(greeting.getGreeting());
        });
    }


    /**
     * 获取所有插件
     */
    public List<PluginWrapper> greetings() {
        return pluginManager.getPlugins();
    }

    /**
     * 获取所有插件Descriptor
     */
    public List<PluginDescriptor> greetingsDescriptor() {
         return pluginManager.getPlugins().stream().map(PluginWrapper::getDescriptor).collect(Collectors.toList());
    }

    /**
     * 获取所有插件 id
     */
    public List<String> greetingsId() {
        return pluginManager.getPlugins().stream().map(p -> p.getDescriptor().getPluginId()).collect(Collectors.toList());
    }

    /**
     * 启动插件
     */
    public PluginState startPlugin(String pluginId) {
        return pluginManager.startPlugin(pluginId);
    }

    /**
     * 启用插件
     */
    public boolean enablePlugin(String pluginId) {
        return pluginManager.enablePlugin(pluginId);
    }

    /**
     * 停止插件
     */
    public PluginState stopPlugin(String pluginId) {
        return pluginManager.stopPlugin(pluginId);
    }

    /**
     * 禁用插件
     */
    public boolean disablePlugin(String pluginId) {
        return pluginManager.disablePlugin(pluginId);
    }

    /**
     * 加载插件
     * <p> 1. 加载前要先写卸载原来的
     * <p> 2. 要启用插件才开始工作
     * @param pluginPath H:/test/pf4j/plugins/HelloPlugin-0.0.2-SNAPSHOT.jar
     */
    public String loadPlugin(String pluginPath) {
        return pluginManager.loadPlugin(Paths.get(pluginPath));
    }

    /**
     * 卸载插件
     */
    public boolean unloadPlugin(String pluginId) {
        return pluginManager.unloadPlugin(pluginId);
    }

    /**
     * 获取插件路径
     */
    public List<Path> pluginsRoots() {
        return pluginManager.getPluginsRoots();
    }

}
