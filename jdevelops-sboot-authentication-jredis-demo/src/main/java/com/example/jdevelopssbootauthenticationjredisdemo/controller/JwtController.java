package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.RedisAccount;
import cn.jdevelops.sboot.authentication.jredis.entity.only.StorageUserTokenEntity;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.JwtRedisService;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jredis.util.RsJwtWebUtil;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.sboot.authentication.jwt.annotation.NotRefreshToken;
import cn.jdevelops.sboot.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.constant.JwtConstant;
import cn.jdevelops.util.jwt.core.JwtService;
import cn.jdevelops.util.jwt.entity.SignEntity;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @ApiMapping(value = "/login", checkToken = false, method = RequestMethod.GET)
    public ResultVO<Object> login(HttpServletRequest request, String username,String password,
                                 boolean refresh) {

        RedisSignEntity<TestBean> redisSignEntity = new RedisSignEntity<>(username,
                username,username,username,false);
        redisSignEntity.setMap(new TestBean("jwtRedis"));
        // 此处数据最好用查出来的用,我这里没有查返回所以自己写了
        RedisAccount<String> redisAccount = new RedisAccount<>();
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

    @GetMapping("/parseJwt")
    public RedisSignEntity<TestBean> parseJwt(HttpServletRequest request){
        RedisSignEntity<TestBean> tokenByRedisSignEntity = RsJwtWebUtil.getTokenByRedisSignEntity(request, TestBean.class);
        if(tokenByRedisSignEntity.getMap() != null){
            System.out.println(tokenByRedisSignEntity.getMap().getRemark());
        }
        return tokenByRedisSignEntity;
    }


    @GetMapping("/subject")
    public String subject(HttpServletRequest request){
        String token = JwtWebUtil.getToken(request);
        return JwtService.getSubjectExpires(token);
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
