package cn.tannn.demo.jdevelops.frameworksweb;

import cn.tannn.jdevelops.autoschema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoSchema
public class FrameworksWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworksWebApplication.class, args);
    }

}
