package com.example.jdevelopsconfigdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "tan")
@Configuration
@Data
public class TDemoconfig {
    String a;
    String b;
}
