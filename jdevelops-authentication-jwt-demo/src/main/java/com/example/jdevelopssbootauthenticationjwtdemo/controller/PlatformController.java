package com.example.jdevelopssbootauthenticationjwtdemo.controller;

import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiPlatform;
import cn.jdevelops.sboot.authentication.jwt.server.LoginService;
import cn.jdevelops.util.jwt.constant.PlatformConstant;
import cn.jdevelops.util.jwt.entity.SignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试 apiPlatform注解
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/10/31 13:46
 */
@RestController
public class PlatformController {



    @Autowired
    private LoginService loginService;

    @ApiMapping(value = "/loginPlatform",checkToken = false, method = RequestMethod.POST)
    public String loginPlatform(@RequestBody List<PlatformConstant> platform){
        SignEntity<String> signEntity = new SignEntity<>("tan", platform);
        return loginService.login(signEntity);
    }

    @GetMapping("/no/ApiPlatform")
    @ApiPlatform()
    public String NoApiPlatform(){
        return "NoApiPlatform";
    }


    @GetMapping("/COMMON")
    @ApiPlatform()
    public String COMMON(){
       return "COMMON";
    }

    @GetMapping("/WEB_ADMIN")
    @ApiPlatform(platform = {PlatformConstant.WEB_ADMIN})
    public String WEB_ADMIN(){
        return "WEB_ADMIN";
    }

    @GetMapping("/APPLET/WEB_ADMIN")
    @ApiPlatform(platform = {PlatformConstant.APPLET,PlatformConstant.WEB_ADMIN})
    public String APPLET_WEB_ADMIN(){
        return "APPLET_WEB_ADMIN";
    }
}
