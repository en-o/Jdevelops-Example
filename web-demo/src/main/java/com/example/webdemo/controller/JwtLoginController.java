package com.example.webdemo.controller;


import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.sboot.authentication.jredis.util.RsJwtWebUtil;
import cn.jdevelops.sboot.authentication.jwt.server.LoginService;
import cn.jdevelops.util.jwt.entity.SignEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 由于集体测试愿意不方便开启 authentication 所以，将文档管理->个性化设置->开启动态请求参数
 *  *  在请求时，handler 中添加 token 属性
 */
@Tag(name = "测试jwt登录")
@PathRestController("/jwt/login")
@RequiredArgsConstructor
public class JwtLoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String token(){
        SignEntity signEntity = new SignEntity("tan","tan","tan","tan");
        return loginService.login(signEntity);
    }


    @GetMapping("stop")
    public String stop(){
        return "拦截还是放行！";
    }


    @GetMapping("/parseJwt")
    public SignEntity<String> parseJwt(HttpServletRequest request){
        SignEntity<String> tokenBySignEntity = RsJwtWebUtil.getTokenBySignEntity(request, String.class);
        if(tokenBySignEntity.getMap() != null){
            System.out.println(tokenBySignEntity.getMap());
        }
        return tokenBySignEntity;
    }
}
