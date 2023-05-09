package com.example.jdevelopsdataautoschemademo;

import cn.jdevelops.schema.scan.EnableAutoSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoSchema
public class JdevelopsDataAutoSchemaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsDataAutoSchemaDemoApplication.class, args);
    }

}
