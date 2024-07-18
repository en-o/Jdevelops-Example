package cn.tannn.demo.jdevelops.authenticationsrjwt.controller;


import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.LoginJwtExtendInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
