package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.annotation.EnableRenewpwd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableRenewpwd
@EnableScheduling
public class DalsRenewpwdApplication{

    private static final Logger log = LoggerFactory.getLogger(DalsRenewpwdApplication.class);

    public static void main(String[] args) {
        log.warn("不用定时器，用异常代替定时器，直接在异常中处理密码过期");
        SpringApplication.run(DalsRenewpwdApplication.class, args);
    }

}
