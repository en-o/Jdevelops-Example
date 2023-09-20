package com.example.jdevelopsdataddssdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
public class JdevelopsDataDdssDemoApplication {

    public static void main(String[] args) throws MalformedURLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(JdevelopsDataDdssDemoApplication.class, args);
        // 需要加载的jar包路径
        URL url = new URL("file:///G/tool/maven/repo/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar");
        ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, sysLoader);
        applicationContext.setClassLoader(urlClassLoader);

    }

}
