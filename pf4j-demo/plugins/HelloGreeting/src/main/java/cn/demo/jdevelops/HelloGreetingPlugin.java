package cn.demo.jdevelops;

import org.apache.commons.lang.StringUtils;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 插件生命周期
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/8/1 上午10:01
 */
public class HelloGreetingPlugin extends SpringPlugin  {

    public HelloGreetingPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("HelloPlugin.start()");
        // for testing the development mode(用于测试开发模式)
        // @see https://pf4j.org/doc/development-mode.html
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("HelloPlugin development"));
        }
    }

    @Override
    public void stop() {
        System.out.println("HelloPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        // 将当前自己编写的注册到spring bean
        applicationContext.register(SpringConfiguration.class);
        applicationContext.refresh();
        return applicationContext;
    }

    @Override
    public void delete() {
        System.out.println("HelloPlugin.delete()");
    }
}
