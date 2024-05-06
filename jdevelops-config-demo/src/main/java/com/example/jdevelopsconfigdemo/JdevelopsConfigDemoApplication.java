package com.example.jdevelopsconfigdemo;

import cn.jdevelops.config.standalone.model.Configs;
import com.example.jdevelopsconfigdemo.test.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackageClasses = {Configs.class, User.class})
public class JdevelopsConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsConfigDemoApplication.class, args);
    }
}
