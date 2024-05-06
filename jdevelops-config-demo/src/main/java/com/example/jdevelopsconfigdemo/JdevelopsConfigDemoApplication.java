package com.example.jdevelopsconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EntityScan(basePackageClasses = {Configs.class, User.class})
public class JdevelopsConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsConfigDemoApplication.class, args);
    }
}
