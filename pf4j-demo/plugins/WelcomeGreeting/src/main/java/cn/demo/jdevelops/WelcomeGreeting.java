package cn.demo.jdevelops;

import cn.tannn.demo.pf4j.greeting.api.Greeting;
import org.pf4j.Extension;

import java.util.Properties;

@Extension
public class WelcomeGreeting implements Greeting {

    @Override
    public String getGreeting() {
        return "welcome:" +projectVersion();
    }

    @Override
    public String food() {
        return "noodle:" +projectVersion();
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
