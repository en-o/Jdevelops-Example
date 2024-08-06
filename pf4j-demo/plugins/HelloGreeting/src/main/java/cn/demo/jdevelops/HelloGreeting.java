package cn.demo.jdevelops;

import cn.demo.jdevelops.service.MessageProvider;
import cn.tannn.demo.pf4j.greeting.api.Greeting;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;

@Extension
public class HelloGreeting implements Greeting {

    @Autowired
    private MessageProvider messageProvider;

    @Override
    public String getGreeting() {
        return messageProvider.getMessage();
    }

    @Override
    public String food() {
        return messageProvider.getFood();
    }
}
