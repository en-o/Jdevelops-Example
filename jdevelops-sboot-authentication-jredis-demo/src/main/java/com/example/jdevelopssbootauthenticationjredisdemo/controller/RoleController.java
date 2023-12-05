package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.sboot.authentication.jwt.annotation.ApiPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限测试
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 13:55
 */
@RestController
public class RoleController {


    @Autowired
    private RedisLoginService redisLoginService;

    /**
     * 退出
     */
    @GetMapping("/roles1")
    @ApiPermission(roles = {"admin"})
    public ResultVO<String> roles1(HttpServletRequest request) {
        return ResultVO.success("admin");
    }

    /**
     * 退出
     */
    @GetMapping("/roles2")
    @ApiPermission(roles = {"tan"})
    public ResultVO<String> roles2(HttpServletRequest request) {
        return ResultVO.success("tan");
    }

    /**
     * 退出
     */
    @GetMapping("/roles3")
    @ApiPermission(roles = {"admin","tan"})
    public ResultVO<String> roles3(HttpServletRequest request) {
        return ResultVO.success("admin tan");
    }


    /**
     * 登录
     */
    @GetMapping("/permissions1")
    @ApiPermission(permissions = {"/permissions1"})
    public ResultVO<Object> permissions1(HttpServletRequest request) {
        return ResultVO.success("permissions1");
    }

}
