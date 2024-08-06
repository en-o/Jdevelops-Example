package cn.demo.jdevelops;

import org.apache.commons.lang.StringUtils;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

/**
 * 插件生命周期
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/8/1 上午10:01
 */
public class WelcomePlugin extends Plugin {

    public WelcomePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("WelcomePlugin.start()");
        // for testing the development mode(用于测试开发模式)
        // @see https://pf4j.org/doc/development-mode.html
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("WelcomePlugin：development"));
        }
    }

    @Override
    public void stop() {
        System.out.println("HelloPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    public void delete() {
        System.out.println("WelcomePlugin.delete()");
    }
}
