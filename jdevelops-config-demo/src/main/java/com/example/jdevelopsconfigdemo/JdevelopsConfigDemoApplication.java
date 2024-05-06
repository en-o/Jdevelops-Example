package com.example.jdevelopsconfigdemo;

import cn.jdevelops.config.standalone.dao.ConfigsDao;
import cn.jdevelops.config.standalone.model.Configs;
import com.example.jdevelopsconfigdemo.test.User;
import com.example.jdevelopsconfigdemo.test.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {ConfigsDao.class, UserDao.class})
@EntityScan(basePackageClasses = {Configs.class, User.class})
public class JdevelopsConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsConfigDemoApplication.class, args);
    }

}
