package cn.demo.jdevelops.service;


/**
 * 使用  SpringConfiguration 注入到 spring 后在 WelcomeGreeting 里面用spring的方式使用
 */
public interface MessageProvider {

    String getMessage();

    String getFood();
}

