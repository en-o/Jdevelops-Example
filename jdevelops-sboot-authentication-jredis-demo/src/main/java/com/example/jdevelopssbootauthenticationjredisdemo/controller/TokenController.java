package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.RedisAccount;
import cn.jdevelops.sboot.authentication.jredis.entity.only.StorageUserTokenEntity;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.JwtRedisService;
import cn.jdevelops.sboot.authentication.jredis.util.RsJwtWebUtil;
import cn.jdevelops.sboot.authentication.jwt.annotation.NotRefreshToken;
import cn.jdevelops.sboot.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.constant.JwtConstant;
import cn.jdevelops.util.jwt.core.JwtService;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.TestBean;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 13:56
 */
@RestController
public class TokenController {
    @Autowired
    private JwtRedisService jwtRedisService;


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
            return ResultVO.success(loginTokenRedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.fail();
    }

    /**
     * 解析token
     * @param request HttpServletRequest
     * @return  TestBean
     */
    @GetMapping("/parseJwt")
    public RedisSignEntity<TestBean> parseJwt(HttpServletRequest request){
        RedisSignEntity<TestBean> tokenByRedisSignEntity = RsJwtWebUtil.getTokenByRedisSignEntity(request, TestBean.class);
        if(tokenByRedisSignEntity.getMap() != null){
            System.out.println(tokenByRedisSignEntity.getMap().getRemark());
        }
        return tokenByRedisSignEntity;
    }


    /**
     * 获取  subject
     * @param request HttpServletRequest
     * @return subject
     */
    @GetMapping("/loadUserStatus")
    public RedisAccount<?> loadUserStatus(HttpServletRequest request){
        return  jwtRedisService.loadUserStatus(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)), RedisAccount.class);
    }
}
