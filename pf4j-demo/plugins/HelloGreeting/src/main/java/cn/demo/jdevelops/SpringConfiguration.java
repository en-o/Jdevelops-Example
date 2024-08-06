package cn.demo.jdevelops;

import cn.demo.jdevelops.service.HelloMessageProvider;
import cn.demo.jdevelops.service.MessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring ioc
 */
@Configuration
public class SpringConfiguration {

    @Bean
    public MessageProvider messageProvider() {
        return new HelloMessageProvider();
    }

}
