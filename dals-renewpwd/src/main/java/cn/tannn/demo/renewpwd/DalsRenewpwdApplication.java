package cn.tannn.demo.renewpwd;

import cn.tannn.jdevelops.renewpwd.PwdCheckDetector;
import cn.tannn.jdevelops.renewpwd.RenewPwdRefresh;
import cn.tannn.jdevelops.renewpwd.annotation.EnableRenewpwd;
import cn.tannn.jdevelops.renewpwd.pojo.PwdExpireInfo;
import cn.tannn.jdevelops.renewpwd.refresh.RenewpwdDataSourceConfig;
import cn.tannn.jdevelops.renewpwd.refresh.dataconfig.DataSourceConfigStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
@EnableRenewpwd
@Slf4j
public class DalsRenewpwdApplication implements ApplicationRunner {


    @Value("${spring.datasource.username}")
    private String currentUsername;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DalsRenewpwdApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("密码续命触发器启动");
        try {
            PwdCheckDetector detector = PwdCheckDetector.builder()
                    .pwdExpireSupplier(() -> new PwdExpireInfo(checkPassword()))
                    .retryIntervalMinutes(1) // 探测间隔，默认5分钟
                    .applicationContext(applicationContext)
                    .build();
            // 触发器会自动循环运行
            detector.start();
        } catch (Exception e) {
            log.error("密码续命触发器启动失败", e);
        }
        checkPassword();
    }


    public boolean checkPassword() {
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
