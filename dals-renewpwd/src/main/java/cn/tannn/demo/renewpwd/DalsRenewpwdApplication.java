package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.PwdCheckDetector;
import cn.tannn.jdevelops.renewpwd.annotation.EnableRenewpwd;
import cn.tannn.jdevelops.renewpwd.pojo.PwdExpireInfo;
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

    @Value("${spring.datasource.username}")
    private String currentUsername;

    @Value("${spring.datasource.password}")
    private String currentPassword;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DalsRenewpwdApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(PwdCheckDetector detector =  PwdCheckDetector.builder()
                .pwdExpireSupplier(()-> new PwdExpireInfo(currentPassword,checkPassword()))
                .build() ) {
            // 触发器会自动循环运行
            detector.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        checkPassword();
    }



    public boolean checkPassword(){
        String sql = "SELECT password_expired FROM mysql.user WHERE user = ?";
        String expired = jdbcTemplate.queryForObject(sql, new Object[]{currentUsername}, String.class);
        if ("Y".equalsIgnoreCase(expired)) {
            System.out.println("当前账户密码已过期");
            return true;
        } else {
            System.out.println("当前账户密码未过期");
            return false;
        }
    }


}
