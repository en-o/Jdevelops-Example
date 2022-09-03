package cn.jdevelops.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 模板启动类
 * @author tn
 */
@SpringBootApplication
@EnableJpaAuditing
public class TestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }

}
