package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.StorageUserRole;
import cn.jdevelops.sboot.authentication.jredis.entity.StorageUserState;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import cn.jdevelops.util.jwt.constant.PlatformConstant;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.Login;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

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
     * @param onlyOnline 以前的是否会被挤下线
     * @return ResultVO
     */
    @ApiMapping(value = "/login", checkToken = false, method = RequestMethod.GET)
    public ResultVO<Object> login(Login login) {

        RedisSignEntity<TestBean> redisSignEntity = new RedisSignEntity<>(
                login.getUsername(),
                Collections.singletonList(PlatformConstant.COMMON),
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

        String sign = redisLoginService.login(redisSignEntity);
        Map<String, String> responseData = Collections.singletonMap("token", sign);
        return ResultVO.success(responseData);
    }

}
