package cn.tan.authentication.sas.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Steve Riesenberg
 * @since 1.1
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
	public String login() {
		return "login";
	}

}
