package cn.tannn.demo.jdevelops.utilsvalidation;

import cn.tannn.demo.jdevelops.utilsvalidation.bean.ResultBean;
import cn.tannn.demo.jdevelops.utilsvalidation.bean.UserBean;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class UtilsValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilsValidationApplication.class, args);
	}



	/**
	 * 测试入参验证
	 * @param userBean userBean
	 * @return UserBean
	 */
	@PostMapping("validate")
	public UserBean validate(@RequestBody @Valid UserBean userBean){
		return userBean;
	}


	/**
	 * 测试返回值遮掩
	 * @return UserBean
	 */
	@GetMapping("cover")
	public ResultBean cover(){
		return new ResultBean();
	}

}
