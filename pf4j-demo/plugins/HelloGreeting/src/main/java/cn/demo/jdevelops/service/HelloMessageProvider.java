package cn.demo.jdevelops.service;

import java.io.IOException;
import java.util.Properties;

public class HelloMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "hello:"+projectVersion();
    }

    @Override
    public String getFood() {
        return "rice:"+projectVersion();
    }

    public String projectVersion() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("version.properties"));
            return properties.getProperty("project.version");
        }catch (Exception e){
           return "0.0.1";
        }
    }

}
