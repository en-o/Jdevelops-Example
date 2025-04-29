package cn.tannn.test.logslogin;

import cn.tannn.jdevelops.autoschema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAutoSchema
public class LogsLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogsLoginApplication.class, args);
    }

}
