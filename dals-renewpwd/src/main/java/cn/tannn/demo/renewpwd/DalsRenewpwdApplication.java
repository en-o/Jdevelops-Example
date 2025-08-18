package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.annotation.EnableRenewpwd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableRenewpwd
@Slf4j
@EnableScheduling
public class DalsRenewpwdApplication{


    public static void main(String[] args) {
//        log.warn("不用定时器，用异常代替定时器，直接在异常中处理密码过期");
        SpringApplication.run(DalsRenewpwdApplication.class, args);
    }

}
