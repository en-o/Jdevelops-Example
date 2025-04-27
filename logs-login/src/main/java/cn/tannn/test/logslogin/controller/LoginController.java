package cn.tannn.test.logslogin.controller;

import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.jwt.standalone.service.LoginService;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.logs.LoginLog;
import cn.tannn.jdevelops.logs.constant.LoginType;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;


/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @Operation(summary = "记录登录日志")
    @ApiOperationSupport(order = 6)
    @ApiMapping(value = "/login",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign token(){
        SignEntity<String> signEntity = SignEntity.init("tan");
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }


    @Operation(summary = "记录登录错误日志")
    @ApiMapping(value = "/error",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public void subject(HttpServletRequest request) throws LoginException {
      throw new LoginException("账号密码错误");
    }

}
