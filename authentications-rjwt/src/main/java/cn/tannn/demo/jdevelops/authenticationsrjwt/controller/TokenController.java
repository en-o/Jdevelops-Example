package cn.tannn.demo.jdevelops.authenticationsrjwt.controller;

import cn.tannn.jdevelops.annotations.web.authentication.NotRefreshToken;
import cn.tannn.jdevelops.jwt.redis.entity.StorageUserRole;
import cn.tannn.jdevelops.jwt.redis.entity.StorageUserState;
import cn.tannn.jdevelops.jwt.redis.entity.only.StorageToken;
import cn.tannn.jdevelops.jwt.redis.service.RedisToken;
import cn.tannn.jdevelops.jwt.redis.service.RedisUserRole;
import cn.tannn.jdevelops.jwt.redis.service.RedisUserState;
import cn.tannn.jdevelops.jwt.redis.util.RsJwtWebUtil;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.utils.jwt.constant.JwtConstant;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
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

    @Autowired
    private RedisUserRole redisUserRole;



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
    @GetMapping("/tokenRedis")
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
    public SignEntity<String> parseJwt(HttpServletRequest request){
        SignEntity<String> tokenByRedisSignEntity = RsJwtWebUtil.getTokenBySignEntity(request, String.class);
        return tokenByRedisSignEntity;
    }


    /**
     * 获取  loadUserStatus
     * @param request HttpServletRequest
     * @return subject
     */
    @GetMapping("/loadUserStatus")
    public StorageUserState loadUserStatus(HttpServletRequest request){
        return  redisUserState.load(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)));
    }

    /**
     * 获取  loadUserRole
     * @param request HttpServletRequest
     * @return subject
     */
    @GetMapping("/loadUserRole")
    public StorageUserRole loadUserRole(HttpServletRequest request){
        return  redisUserRole.load(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)));
    }

}
