package com.example.loggerleveltest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LoggerLevelTestApplication {

    private static final Logger logger = LoggerFactory.getLogger("APILOG");
    public static void main(String[] args) {
        SpringApplication.run(LoggerLevelTestApplication.class, args);
        logger.info("This is a custom log message");
    }

}
