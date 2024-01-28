package com.example.jdevelopssbootauthenticationjwtdemo.controller;

import cn.jdevelops.authentication.jwt.annotation.ApiPlatform;
import cn.jdevelops.util.jwt.constant.PlatformConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试 apiPlatform注解
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/10/31 13:46
 */
@RestController
@ApiPlatform(platform = {PlatformConstant.APPLET,PlatformConstant.WEB_ADMIN})
public class Platform2Controller {

    @GetMapping("classSetting1")
    public String APPLET_WEB_ADMIN(){
        return "在类上使用 ApiPlatform APPLET_WEB_ADMIN";
    }

    @GetMapping("classSetting2")
    public String APPLET_WEB_ADMIN_2(){
        return "2在类上使用 ApiPlatform APPLET_WEB_ADMIN";
    }
}
