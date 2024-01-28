package com.example.jdevelopssbootauthenticationjredisdemo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.sboot.authentication.jwt.util.JwtWebUtil;
import cn.jdevelops.util.jwt.constant.PlatformConstant;
import cn.jdevelops.util.jwt.core.JwtService;
import cn.jdevelops.util.jwt.entity.LoginJwtExtendInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 14:03
 */
@RestController
public class JwtServiceController {

    // ================== getTokenByBean 自己看单元测试吧 ==================


    /**
     * loginJwtExtendInfo
     * 登录时要用了 LoginJwtExtendInfo 才行噢
     */
    @GetMapping("/loginJwtExtendInfo")
    public ResultVO<LoginJwtExtendInfo> userName(HttpServletRequest request) {
        return ResultVO.success(JwtService.getLoginJwtExtendInfoExpires(JwtWebUtil.getToken(request)));
    }


    /**
     * subject
     */
    @GetMapping("/subject")
    public ResultVO<String> subject(HttpServletRequest request) {
        return ResultVO.success(JwtService.getSubjectExpires(JwtWebUtil.getToken(request)));
    }

    /**
     * platformConstant
     */
    @GetMapping("/platformConstant")
    public ResultVO<List<PlatformConstant>> userId(HttpServletRequest request) {
        return ResultVO.success(JwtService.getPlatformConstantExpires(JwtWebUtil.getToken(request)));
    }

}
