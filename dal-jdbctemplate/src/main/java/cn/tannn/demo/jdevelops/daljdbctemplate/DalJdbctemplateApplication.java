package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import cn.tannn.jdevelops.annotations.jdbctemplate.proxysql.JdbcTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DalJdbctemplateApplication implements ApplicationRunner  {

	@JdbcTemplate
	QueryUserService queryUserService;
	// 目前无法通过注入的方式，创建bean太晚导致 直接注入使用失败，只能通过下面的getbean加载
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DalJdbctemplateApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// jdbc template 注入测试， 可以在接口上写sql完成查询动作
		queryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
	}
}
