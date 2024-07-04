package cn.tannn.demo.frameworksfile;

import cn.tannn.jdevelops.autoschema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoSchema
public class FrameworksFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworksFileApplication.class, args);
    }

}
