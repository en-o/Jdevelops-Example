package cn.tannn.jdevelopssbootjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JdevelopsSbootJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsSbootJpaDemoApplication.class, args);
    }

}
