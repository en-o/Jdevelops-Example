package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.StorageUserState;
import cn.jdevelops.sboot.authentication.jredis.entity.only.StorageToken;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.RedisToken;
import cn.jdevelops.sboot.authentication.jredis.service.RedisUserState;
import cn.jdevelops.sboot.authentication.jredis.util.RsJwtWebUtil;
import cn.jdevelops.sboot.authentication.jwt.annotation.NotRefreshToken;
import cn.jdevelops.sboot.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.constant.JwtConstant;
import cn.jdevelops.util.jwt.core.JwtService;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.TestBean;
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
    private RedisToken redisToken;

    @Autowired
    private RedisUserState redisUserState;



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
    public ResultVO<StorageToken> tokenRedis(HttpServletRequest request) {
        try {
            String token = request.getHeader(JwtConstant.TOKEN);
            StorageToken storageToken = redisToken.loadByToken(token);
            return ResultVO.success(storageToken);
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
    public RedisSignEntity<String> parseJwt(HttpServletRequest request){
        RedisSignEntity<String> tokenByRedisSignEntity = RsJwtWebUtil.getTokenByRedisSignEntity(request, String.class);
        return tokenByRedisSignEntity;
    }


    /**
     * 获取  subject
     * @param request HttpServletRequest
     * @return subject
     */
    @GetMapping("/loadUserStatus")
    public StorageUserState loadUserStatus(HttpServletRequest request){
        return  redisUserState.load(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)));
    }
}
