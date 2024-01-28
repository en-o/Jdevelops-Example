package com.example.jdevelopssbootauthenticationjwtdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.authentication.jwt.server.LoginService;
import cn.jdevelops.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.core.JwtService;
import cn.jdevelops.util.jwt.entity.LoginJwtExtendInfo;
import cn.jdevelops.util.jwt.entity.SignEntity;
import com.example.jdevelopssbootauthenticationjwtdemo.bean.TestBean;
import com.example.jdevelopssbootauthenticationjwtdemo.result.ReplaceResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
public class JwtController {

    @Autowired
    private LoginService loginService;

    @GetMapping("stop")
    public String stop() {
        return "拦截我";
    }


    @GetMapping("letGo")
    public ResultVO<String> letGo() {
        return ResultVO.success("通过配置放行我");
    }

    @GetMapping("letGo2")
    public ReplaceResultVO<String> letGo2() {
        return ReplaceResultVO.success(200, "通过配置放行我");
    }

    @ApiMapping(value = "/api/letGo", checkToken = false)
    public String apiLetGo() {
        return "利用注解@ApiMapping放行";
    }


    @GetMapping("/user/login")
    public String defaultLetGo() {
        return "默认放行</user/login>";
    }

    @GetMapping("/login")
    public String token() {
        SignEntity<String> signEntity = new SignEntity<>("tan");
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }

    @ApiMapping(value = "/login2", checkToken = false)
    public String token2() {
        SignEntity<TestBean> signEntity = new SignEntity<>("tan", new TestBean("hi"));
        return loginService.login(signEntity);
    }


    @ApiMapping(value = "/login3", checkToken = false)
    public String token3() {
        Map<String, String> info = new HashMap<>();
        info.put("nickname", "ning");
        SignEntity<Map<String, String>> signEntity = new SignEntity<>("tan", info);
        return loginService.login(signEntity);
    }

    @ApiMapping(value = "/login4", checkToken = false)
    public String token4() {
        Map<String, String> info = new HashMap<>();
        info.put("nickname", "ning");
        SignEntity<LoginJwtExtendInfo<Map<String, String>>> signEntity = new SignEntity<>("tan",
                new LoginJwtExtendInfo("tan", "1", "宁", info));
        return loginService.login(signEntity);
    }

    @GetMapping("/isLogin")
    public boolean isLogin(HttpServletRequest request) {
        return loginService.isLogin(request);
    }

    @GetMapping("/parseJwt")
    public SignEntity<Object> parseJwt(HttpServletRequest request, int type) {
        if(type == 0){
            return JwtWebUtil.getTokenBySignEntity(request, LoginJwtExtendInfo.class);
        }else if (type == 1) {
            return JwtWebUtil.getTokenBySignEntity(request, TestBean.class);
        } else if (type == 2) {
            return JwtWebUtil.getTokenBySignEntity(request, Map.class);
        } else {
            return JwtWebUtil.getTokenBySignEntity(request, String.class);
        }
    }


    @GetMapping("/subject")
    public String subject(HttpServletRequest request) {
        String token = JwtWebUtil.getToken(request);
        return JwtService.getSubjectExpires(token);
    }

}
