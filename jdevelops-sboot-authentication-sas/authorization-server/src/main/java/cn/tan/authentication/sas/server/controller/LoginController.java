package cn.tan.authentication.sas.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义登录
 * @author Steve Riesenberg
 * @since 1.1
 */
@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

}
