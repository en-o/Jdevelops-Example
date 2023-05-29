package com.example.webdemo.controller;


import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.RedisAccount;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.JwtRedisService;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.sboot.authentication.jwt.server.LoginService;
import cn.jdevelops.util.jwt.entity.SignEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * 由于集体测试愿意不方便开启 authentication 所以，将文档管理->个性化设置->开启动态请求参数
 *  *  在请求时，handler 中添加 token 属性
 */
@Tag(name = "测试redis登录")
@PathRestController("/jwt/redis/login")
public class RedisLoginController {


    @Autowired
    private RedisLoginService redisLoginService;


    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResultVO<String> logout(HttpServletRequest request) {
        redisLoginService.loginOut(request);
        return ResultVO.success("成功退出");
    }


    /**
     * 登录
     * @param refresh true token刷新, false token重复使用 (用户存在登录时 token时更新还是依旧)
     * @return ResultVO
     */
    @Parameter(name = "refresh",description = " true token刷新, false token重复使用 (用户存在登录时 token时更新还是依旧)", example = "true")
    @Parameter(name = "username",description = "username", example = "tan")
    @Parameter(name = "password",description = "password", example = "tan")
    @ApiMapping(value = "/", checkToken = false, method = RequestMethod.GET)
    public ResultVO<Object> login(HttpServletRequest request, String username,String password,
                                  Boolean refresh) {

        RedisSignEntity redisSignEntity = new RedisSignEntity(username,
                username,username,username,false);

        // 此处数据最好用查出来的用,我这里没有查返回所以自己写了
        RedisAccount redisAccount = new RedisAccount();
        redisAccount.setDisabledAccount(false);
        redisAccount.setExcessiveAttempts(false);
        redisAccount.setUserCode(username);
        redisAccount.setSalt("");
        redisAccount.setPassword(password);
        redisAccount.setUserInfo(null);

        String sign = redisLoginService.refreshLogin(request,
                refresh,
                redisSignEntity,
                redisAccount);
        Map<String, String> responseData = Collections.singletonMap("token", sign);
        return ResultVO.successForData(responseData);
    }



    @GetMapping("stop")
    public String stop(){
        return "拦截还是放行！";
    }

}
