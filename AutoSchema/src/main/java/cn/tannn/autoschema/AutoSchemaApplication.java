package cn.tannn.autoschema;

import cn.jdevelops.schema.scan.EnableAutoSchema;
//import cn.jdevelops.spring.scan.EnableAutoSchema; <=2.0.5
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
