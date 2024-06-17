package cn.tannn.demo.jdevelops.eventsredis;

import cn.tannn.jdevelops.result.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EventsRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsRedisApplication.class, args);
    }

}
