package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.entity.RedisAccount;
import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiMapping;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.Login;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.TestBean;
import com.example.jdevelopssbootauthenticationjredisdemo.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
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
                login.getUsername(),
                login.getUsername(),
                login.getUsername(),
                false,
                login.isOnlyOnline()
        );
        redisSignEntity.setMap(new TestBean("jwtRedis"));
        // 此处数据最好用查出来的用,我这里没有查返回所以自己写了
        RedisAccount<UserInfo> redisAccount = new RedisAccount<>();
        redisAccount.setDisabledAccount(false);
        redisAccount.setExcessiveAttempts(false);
        redisAccount.setUserCode(login.getUsername());
        redisAccount.setSalt(login.getUsername());
        redisAccount.setPassword(login.getPassword());
        redisAccount.setRoles(login.getRoles());
        redisAccount.setPermissions(login.getPermissions());
        UserInfo userInfo = new UserInfo("tan",10);
        redisAccount.setUserInfo(userInfo);
        String sign = redisLoginService.login(redisSignEntity,
                redisAccount);
        Map<String, String> responseData = Collections.singletonMap("token", sign);
        return ResultVO.success(responseData);
    }

}
