package cn.tannn.demo.jdevelops.eventsredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EventsRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsRedisApplication.class, args);
    }

}
