package cn.demo.jdevelops;

import cn.tannn.jdevelops.pf4j.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Pf4jWebsApplication implements ApplicationRunner {

    @Autowired
    private PluginService pluginService;

    public static void main(String[] args) {
        // 设置运行模式
//        System.setProperty(AbstractPluginManager.MODE_PROPERTY_NAME, RuntimeMode.DEVELOPMENT.toString());

        SpringApplication.run(Pf4jWebsApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("====================starter plugins ids============ ");
        System.out.println(pluginService.pluginsId());
        System.out.println("====================end plugins ids============ ");
    }
}
