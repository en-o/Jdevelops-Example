package com.example.jdevelopsconfigdemo;

import cn.jdevelops.config.standalone.dao.ConfigsDao;
import cn.jdevelops.config.standalone.model.Configs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = ConfigsDao.class)
public class JdevelopsConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsConfigDemoApplication.class, args);
    }

}
