package cn.tannn.demo.jdevelops.frameworksquick;

import cn.tannn.jdevelops.autoschema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoSchema
public class FrameworksQuickApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworksQuickApplication.class, args);
    }

}
