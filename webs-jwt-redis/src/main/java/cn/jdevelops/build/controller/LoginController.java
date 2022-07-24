package cn.jdevelops.build.controller;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.build.account.AccountService;
import cn.jdevelops.build.account.LoginDTO;
import cn.jdevelops.build.account.RegisterDTO;
import cn.jdevelops.jredis.service.RedisService;
import cn.jdevelops.jredis.util.JwtRedisUtil;
import cn.jdevelops.jwt.annotation.ApiMapping;
import cn.jdevelops.jwt.constant.JwtConstant;
import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.result.result.ResultVO;
import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-18 13:42
 */
@PathRestController
@Slf4j
@Api(tags = "登录管理", value = "登录管理")
@RequiredArgsConstructor
@ApiSupport(order = 1)
public class LoginController {

    /**
     * 用户
     */
    private final AccountService accountService;
    private final RedisService redisService;


    /**
     * 登录
     * ps: WithoutAuth放行接口
     *
     * @param login login
     * @return ResultVO
     */
    @ApiOperation(value = "登录", notes = "登录管理")
    @ApiMapping(value = "/login", checkToken = false, method = RequestMethod.POST)
    @ApiOperationSupport(order = 1)
    public ResultVO<Object> login(@RequestBody @Valid LoginDTO login) {
        boolean authenticatedFlag = accountService.authenticateAccount(login);
        if (!authenticatedFlag) {
            if (log.isDebugEnabled()) {
                log.debug("account: {} authenticated fail", login);
            }
            return ResultVO.fail("用户名或密不正确");
        }
        String sign = JwtRedisUtil.sign(login.getUsername(),null);
        Map<String, String> responseData = Collections.singletonMap("token", sign);
        if (log.isDebugEnabled()) {
            log.debug("issue token success, account: {} -- token: {}", login, sign);
        }
        return ResultVO.successForData(responseData);
    }

    @ApiOperation(value = "注册", notes = "登录管理")
    @ApiMapping(value = "/register", checkToken = false, method = RequestMethod.POST)
    @ApiOperationSupport(order = 2)
    public ResultVO<String> accountRegister(@RequestBody @Validated RegisterDTO register, HttpServletRequest request) {
        if (accountService.registerAccount(register, request)) {
            if (log.isDebugEnabled()) {
                log.debug("account: {}, sign up success", register);
            }
            return ResultVO.success("注册成功");
        } else {
            return ResultVO.fail("用户名已经存在");
        }
    }


    @ApiOperation(value = "退出", notes = "登录管理")
    @PostMapping("/logout")
    @ApiOperationSupport(order = 3)
    public ResultVO<String> logout(HttpServletRequest request) {
        try {
            String token = request.getHeader(JwtConstant.TOKEN);
            redisService.removeUserToken(JwtUtil.getSubject(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.success("成功退出");

    }
}
