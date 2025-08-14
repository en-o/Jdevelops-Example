package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.annotation.EnableRenewpwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableRenewpwd
public class DalsRenewpwdApplication implements ApplicationRunner {

    private String currentPassword;
    @Value("${spring.datasource.username}")
    private String currentUsername;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DalsRenewpwdApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkPassword();
    }



    public void checkPassword(){
        String sql = "SELECT password_expired FROM mysql.user WHERE user = ?";
        String expired = jdbcTemplate.queryForObject(sql, new Object[]{currentUsername}, String.class);
        if ("Y".equalsIgnoreCase(expired)) {
            System.out.println("当前账户密码已过期");
        } else {
            System.out.println("当前账户密码未过期");
        }
    }


}
