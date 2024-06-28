package cn.tannn.demo.jdevelops.authenticationsrjwt.controller;


import cn.tannn.demo.jdevelops.authenticationsrjwt.bean.Login;
import cn.tannn.demo.jdevelops.authenticationsrjwt.bean.TestBean;
import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import cn.tannn.jdevelops.jwt.redis.entity.StorageUserRole;
import cn.tannn.jdevelops.jwt.redis.entity.StorageUserState;
import cn.tannn.jdevelops.jwt.redis.entity.sign.RedisSignEntity;
import cn.tannn.jdevelops.jwt.redis.service.RedisLoginService;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.redis.limit.LoginLimitService;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.utils.jwt.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 登录突出
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 13:55
 */
@RestController
public class LoginController {


    @Autowired
    private RedisLoginService redisLoginService;

    @Autowired
    private LoginLimitService loginLimitService;


    /**
     * 退出
     *
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
     *
     * @param login Login
     * @return ResultVO
     */
    @ApiMapping(value = "/login", checkToken = false, method = RequestMethod.GET)
    public ResultVO<Object> login(Login login) {
        // 第一步 先验证
        loginLimitService.verify(login.getUsername(), true);
        try {
            if(Objects.equals(login.getPassword(), "error")){
                throw new LoginException("滚");
            }
            RedisSignEntity<TestBean> redisSignEntity = new RedisSignEntity<>(
                    login.getUsername(),
                    login.getPlatform(),
                    false,
                    login.isOnlyOnline(),
                    new StorageUserRole(
                            login.getUsername(),
                            login.getRoles(),
                            login.getPermissions()),
                    new StorageUserState(
                            login.getUsername(),
                            login.isDisabledAccount(),
                            login.isExcessiveAttempts())
            );
            TokenSign sign = redisLoginService.login(redisSignEntity);
            Map<String, String> responseData = Collections.singletonMap("token", sign.getSign());
            return ResultVO.success(responseData);
        } catch (Exception e) {
            // 第二步：记录次数
            loginLimitService.limit(login.getUsername());
            throw e;
        }
    }

}
