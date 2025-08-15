package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.util.AESUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DalsRenewpwdApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        System.out.println(AESUtil.encrypt("123"));
        System.out.println(AESUtil.encrypt("123456"));
    }
}
