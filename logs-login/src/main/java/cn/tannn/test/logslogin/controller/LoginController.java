package cn.tannn.test.logslogin.controller;

import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.jwt.standalone.service.LoginService;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.logs.LoginLog;
import cn.tannn.jdevelops.logs.constant.LoginType;
import cn.tannn.jdevelops.logs.context.LoginContextHolder;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public TokenSign login1(){
        SignEntity<String> signEntity = SignEntity.init("tan");
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }


    @Operation(summary = "记录登录错误日志")
    @ApiMapping(value = "/error",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public void login2(HttpServletRequest request) throws LoginException {
      throw new LoginException("账号密码错误");
    }



    @Operation(summary = "记录登录日志 - 登录名 1 ")
    @ApiOperationSupport(order = 6)
    @ApiMapping(value = "/login3",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login3(String name){
        SignEntity<String> signEntity = SignEntity.init(name);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @Operation(summary = "记录登录日志 - 登录名 error (必须放到第一位 ")
    @ApiMapping(value = "/login6",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login6(String paw , String name){
        SignEntity<String> signEntity = SignEntity.init(name);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }


    @Operation(summary = "记录登录日志 - 登录名 2 ")
    @ApiOperationSupport(order = 6)
    @ApiMapping(value = "/login4",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login4(@RequestBody LoginDto1 name){
        SignEntity<String> signEntity = SignEntity.init(name.loginName);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @Operation(summary = "记录登录日志 - 登录名 3 ")
    @ApiOperationSupport(order = 6)
    @ApiMapping(value = "/login5",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD,loginNameKey = "username")
    public TokenSign login5(@RequestBody LoginDto2 name){
        SignEntity<String> signEntity = SignEntity.init(name.username);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }



    @Operation(summary = "记录登录日志 - 登录名 3 ")
    @ApiOperationSupport(order = 7)
    @ApiMapping(value = "/login6",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD,loginNameKey = "username")
    public TokenSign login7(@RequestBody LoginDto2 name){
        SignEntity<String> signEntity = SignEntity.init(name.username);
        LoginContextHolder.getContext().setLoginName("taaaaaaaaa").setUserId(name.getId());
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }
}
