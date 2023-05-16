package com.example.jdevelopssbootauthenticationjwtdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.sboot.authentication.jwt.server.LoginService;
import cn.jdevelops.util.jwt.core.JwtService;
import cn.jdevelops.util.jwt.entity.SignEntity;
import com.example.jdevelopssbootauthenticationjwtdemo.result.ReplaceResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String stop(){
        return "拦截我";
    }


    @GetMapping("letGo")
    public ResultVO<String> letGo(){
        return ResultVO.success("通过配置放行我");
    }

    @GetMapping("letGo2")
    public ReplaceResultVO<String> letGo2(){
        return ReplaceResultVO.success(200,"通过配置放行我");
    }

    @ApiMapping(value = "/api/letGo",checkToken = false)
    public String apiLetGo(){
        return "利用注解@ApiMapping放行";
    }


    @GetMapping("/user/login")
    public String defaultLetGo(){
        return "默认放行</user/login>";
    }

    @GetMapping("/login")
    public String token(){
        SignEntity signEntity = new SignEntity("tan","tan","tan","tan");
        return loginService.login(signEntity);
    }


    @GetMapping("/isLogin")
    public boolean isLogin(HttpServletRequest request){
        return loginService.isLogin(request);
    }

}
