package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.DefQueryUserService;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.jdectemplate.config.JdbcTemplateConfig;
import cn.tannn.jdevelops.jdectemplate.util.JdbcProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DalJdbctemplateApplication  {


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DalJdbctemplateApplication.class, args);
		QueryUserService queryUserService = run.getBean(QueryUserService.class);
		queryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
	}


}
