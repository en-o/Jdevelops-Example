package cn.tan.jdevelops.api.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tan
 */
@Slf4j
@SpringBootApplication
public class JdevelopsApiLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsApiLogApplication.class, args);
    }
}
