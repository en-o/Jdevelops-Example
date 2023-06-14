package cn.tannn.jdevelopssbootjpademo;

import cn.jdevelops.data.jap.repository.JdevelopsSimpleJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = JdevelopsSimpleJpaRepository.class)
public class JdevelopsSbootJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsSbootJpaDemoApplication.class, args);
    }

}
