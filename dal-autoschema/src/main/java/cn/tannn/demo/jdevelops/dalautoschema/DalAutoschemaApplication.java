package cn.tannn.demo.jdevelops.dalautoschema;

import cn.tannn.jdevelops.autoschema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoSchema
public class DalAutoschemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DalAutoschemaApplication.class, args);
    }

}
