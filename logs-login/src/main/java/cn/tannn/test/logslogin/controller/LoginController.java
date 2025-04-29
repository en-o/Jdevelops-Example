package cn.tannn.test.logslogin.controller;

import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.jwt.standalone.service.LoginService;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.logs.LoginLog;
import cn.tannn.jdevelops.logs.constant.LoginType;
import cn.tannn.jdevelops.logs.context.LoginContext;
import cn.tannn.jdevelops.logs.context.LoginContextHolder;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "登录日志")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @Operation(summary = "记录登录日志")
    @ApiOperationSupport(order = 6)
    @ApiMapping(value = "/login",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login(){
        SignEntity<String> signEntity = SignEntity.init("tan");
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }


    @Operation(summary = "记录登录错误日志")
    @ApiMapping(value = "/error",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public void login_error(HttpServletRequest request) throws LoginException {
      throw new LoginException("账号密码错误");
    }



    @Operation(summary = "从参数中获取登录名")
    @ApiMapping(value = "/login/p1",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login_p1(String loginName){
        SignEntity<String> signEntity = SignEntity.init(loginName);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @Operation(summary = "从参数获取登录名-两个非参数")
    @ApiMapping(value = "/login/p2",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login_p2(String paw , String loginName){
        SignEntity<String> signEntity = SignEntity.init(loginName);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @Operation(summary = "从参数获取登录名-两个非参数-自定义登录名key")
    @ApiMapping(value = "/login/p3",checkToken = false)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD,loginNameKey = "name")
    public TokenSign login_p3(String paw , String name){
        SignEntity<String> signEntity = SignEntity.init(name);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }



    @Operation(summary = "获取实体参数中的登录名")
    @ApiMapping(value = "/login/b1",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login_b1(@RequestBody LoginDto1 name){
        SignEntity<String> signEntity = SignEntity.init(name.loginName);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @Operation(summary = "获取实体参数中的登录名-自定义登录名key ")
    @ApiMapping(value = "/login/b2",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD,loginNameKey = "username")
    public TokenSign login_b2(@RequestBody LoginDto2 name){
        SignEntity<String> signEntity = SignEntity.init(name.username);
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }



    @Operation(summary = "上下文设置")
    @ApiOperationSupport(order = 7)
    @ApiMapping(value = "/login6",checkToken = false, method = RequestMethod.POST)
    @LoginLog(type = LoginType.ADMIN_ACCOUNT_PASSWORD)
    public TokenSign login7(@RequestBody LoginDto1 name) throws LoginException {
        SignEntity<String> signEntity = SignEntity.init(name.loginName);
        LoginContext loginContext = LoginContextHolder.getContext()
                .setLoginName(name.loginName)
                .setUserId(name.getId())
                .setName(name.name);
        if("user42".equals(name.getLoginName())){
            loginContext.setPlatform(PlatformConstant.APPLET);
        } else if ("demo55".equals(name.getLoginName())) {
            throw new LoginException("登录失败");
        }
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }
}
