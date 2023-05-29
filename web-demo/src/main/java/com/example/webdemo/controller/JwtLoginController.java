package com.example.webdemo.controller;


import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jwt.server.LoginService;
import cn.jdevelops.util.jwt.entity.SignEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "测试jwt登录")
@PathRestController("/jwt/login")
public class JwtLoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String token(){
        SignEntity signEntity = new SignEntity("tan","tan","tan","tan");
        return loginService.login(signEntity);
    }


    @GetMapping("stop")
    public String stop(){
        return "拦截还是放行！";
    }
}
