package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.core.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 14:03
 */
@RestController
public class JwtServiceController {

    /**
     * loginName
     */
    @PostMapping("/loginName")
    public ResultVO<String> loginName(HttpServletRequest request) {
        return ResultVO.success(JwtService.getLoginNameExpires(JwtWebUtil.getToken(request)));
    }

    /**
     * userName
     */
    @PostMapping("/userName")
    public ResultVO<String> userName(HttpServletRequest request) {
        return ResultVO.success(JwtService.getUserNameExpires(JwtWebUtil.getToken(request)));
    }

    /**
     * subject
     */
    @PostMapping("/subject")
    public ResultVO<String> subject(HttpServletRequest request) {
        return ResultVO.success(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)));
    }

    /**
     * userId
     */
    @PostMapping("/userId")
    public ResultVO<String> userId(HttpServletRequest request) {
        return ResultVO.success(JwtService.getUserIdExpires(JwtWebUtil.getToken(request)));
    }

}
