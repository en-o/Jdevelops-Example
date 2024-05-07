package com.example.jdevelopsconfigdemo;

import com.example.jdevelopsconfigdemo.config.TDemoconfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;


@SpringBootApplication
@Slf4j
public class JdevelopsConfigDemoApplication {
    @Autowired
    private TDemoconfig tDemoconfig;


    @Value("${tan.a:tan_a_0001}")
    private String a;

    @Value("${tan.b:tan_b_0001}")
    private String b;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsConfigDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner test_runner() {
        log.info("system env active profiles {}", Arrays.toString(environment.getActiveProfiles()));
        return args -> {
            log.info("Value  ==> a {}, b {}", a, b);
            log.info("tDemoconfig {}",tDemoconfig);
        };
    }
}
