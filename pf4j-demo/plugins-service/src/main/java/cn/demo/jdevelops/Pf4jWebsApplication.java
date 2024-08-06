package cn.demo.jdevelops;

import cn.demo.jdevelops.service.GreetingService;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;

@SpringBootApplication
public class Pf4jWebsApplication implements ApplicationRunner {

    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) {
        // 设置运行模式
//        System.setProperty(AbstractPluginManager.MODE_PROPERTY_NAME, RuntimeMode.DEVELOPMENT.toString());

        SpringApplication.run(Pf4jWebsApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("====================starter============ ");
        System.out.println(greetingService.greetingsId());
        System.out.println("====================end============ ");
    }
}
