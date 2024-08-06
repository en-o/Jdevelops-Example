package cn.demo.jdevelops.service;

public class HelloMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "hello";
    }

    @Override
    public String getFood() {
        return "rice";
    }

}
