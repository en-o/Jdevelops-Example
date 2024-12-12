package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.DefQueryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DalJdbctemplateApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DalJdbctemplateApplication.class, args);
	}

	private DefQueryUserService defQueryUserService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
			defQueryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
	}
}
