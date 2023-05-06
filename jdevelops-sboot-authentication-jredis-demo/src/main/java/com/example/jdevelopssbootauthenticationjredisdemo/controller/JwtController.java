package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.RedisAccount;
import cn.jdevelops.sboot.authentication.jredis.entity.only.StorageUserTokenEntity;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.JwtRedisService;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.sboot.authentication.jwt.annotation.NotRefreshToken;
import cn.jdevelops.util.jwt.constant.JwtConstant;
import cn.jdevelops.util.jwt.core.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
public class JwtController {

    @Autowired
    private JwtRedisService jwtRedisService;


    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResultVO<String> logout(HttpServletRequest request) {
        try {
            String token = request.getHeader(JwtConstant.TOKEN);
            jwtRedisService.removeUserToken(JwtService.getSubject(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.success("成功退出");
    }


    /**
     * 登录
     * @return ResultVO
     */
    @ApiMapping(value = "/login", checkToken = false, method = RequestMethod.GET)
    public ResultVO<Object> login(HttpServletRequest request, String username,String password) {

        // 2.0.6 可以只传一个参数了 ，同时新增了允许永久在线的参数设置
//        String sign = JwtRedisUtil.sign(login.getUsername());'

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

        String sign = new RedisLoginService().refreshLogin(request,
                false,
                redisSignEntity,
                redisAccount);
        Map<String, String> responseData = Collections.singletonMap("token", sign);
        return ResultVO.successForData(responseData);
    }


    /**
     * 测试查询时不刷新token的有效期
     * @return
     */
    @PostMapping("notRefreshToken")
    @NotRefreshToken
    public ResultVO<String> finAll2() {
        return ResultVO.success("haha");
    }


    /**
     * redis中的token信息
     * @param request
     * @return
     */
    @PostMapping("/tokenRedis")
    public ResultVO<StorageUserTokenEntity> tokenRedis(HttpServletRequest request) {
        try {
            String token = request.getHeader(JwtConstant.TOKEN);
            StorageUserTokenEntity loginTokenRedis = jwtRedisService.loadUserTokenInfoByToken(token);
            return ResultVO.successForData(loginTokenRedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.fail("");
    }


    @GetMapping("stop")
    public String stop(){
        return "拦截我";
    }


    @GetMapping("letGo")
    public ResultVO<String> letGo(){
        return ResultVO.success("通过配置放行我");
    }


    @ApiMapping(value = "/api/letGo",checkToken = false)
    public String apiLetGo(){
        return "利用注解@ApiMapping放行";
    }


    @GetMapping("/user/login")
    public String defaultLetGo(){
        return "默认放行</user/login>";
    }
}
