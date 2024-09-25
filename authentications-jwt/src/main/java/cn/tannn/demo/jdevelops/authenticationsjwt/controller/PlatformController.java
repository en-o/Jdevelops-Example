package cn.tannn.demo.jdevelops.authenticationsjwt.controller;

import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.annotations.web.authentication.ApiPlatform;
import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.jwt.standalone.service.LoginService;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
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
    public TokenSign loginPlatform(@RequestBody List<String> platform){
        SignEntity<String> signEntity = SignEntity.initPlatform("tan", platform);
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
