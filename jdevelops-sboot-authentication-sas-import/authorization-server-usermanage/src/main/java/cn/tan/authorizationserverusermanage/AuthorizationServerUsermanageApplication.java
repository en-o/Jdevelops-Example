package cn.tan.authorizationserverusermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthorizationServerUsermanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerUsermanageApplication.class, args);
	}




	/**
	 * 登录就行
	 */
	@GetMapping("/messages1")
	public String getMessages1() {
		return " hello Message 1";
	}


	/**
	 * PreAuthorize(如果有指定权限, 则返回 true)
	 */
	@GetMapping("/messages2")
	@PreAuthorize("hasAuthority('SCOPE_profile')")
	public String getMessages2() {
		return " hello Message 2";
	}


	/**
	 * PreAuthorize(如果有指定权限, 则返回 true)
	 */
	@GetMapping("/messages3")
	@PreAuthorize("hasAuthority('SCOPE_Message')")
	public String getMessages3() {
		return " hello Message 3";
	}



	/**
	 * PreAuthorize(如果有指定权限, 则返回 true)
	 */
	@GetMapping("/messages4")
	@PreAuthorize("hasAuthority('SCOPE_tan')")
	public String getMessages4() {
		return " hello Message 4";
	}
}
