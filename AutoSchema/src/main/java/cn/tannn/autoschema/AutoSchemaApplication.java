package cn.tannn.autoschema;

import cn.jdevelops.spring.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tnnn 
 */
@SpringBootApplication
@EnableAutoSchema
public class AutoSchemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoSchemaApplication.class, args);
    }

}
