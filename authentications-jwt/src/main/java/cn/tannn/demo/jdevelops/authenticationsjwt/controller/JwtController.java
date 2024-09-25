package cn.tannn.demo.jdevelops.authenticationsjwt.controller;

import cn.tannn.demo.jdevelops.authenticationsjwt.bean.TestBean;
import cn.tannn.demo.jdevelops.authenticationsjwt.reset.ReplaceResultVO;
import cn.tannn.jdevelops.annotations.web.authentication.ApiMapping;
import cn.tannn.jdevelops.jwt.standalone.pojo.TokenSign;
import cn.tannn.jdevelops.jwt.standalone.service.LoginService;
import cn.tannn.jdevelops.jwt.standalone.util.JwtWebUtil;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.utils.jwt.core.JwtService;
import cn.tannn.jdevelops.utils.jwt.module.LoginJwtExtendInfo;
import cn.tannn.jdevelops.utils.jwt.module.SignEntity;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
public class JwtController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "拦截")
    @ApiOperationSupport(order = 1)
    @GetMapping("stop")
    public String stop(){
        return "拦截我";
    }

    @Operation(summary = "通过配置放行")
    @GetMapping("letGo")
    @ApiOperationSupport(order = 2)
    public ResultVO<String> letGo(){
        return ResultVO.success("通过配置放行我");
    }

    @Operation(summary = "通过配置放行2")
    @ApiOperationSupport(order = 3)
    @GetMapping("letGo2")
    public ReplaceResultVO<String> letGo2(){
        return ReplaceResultVO.success("通过配置放行我");
    }

    @Operation(summary = "利用注解@ApiMapping放行")
    @ApiOperationSupport(order = 4)
    @ApiMapping(value = "/api/letGo",checkToken = false)
    public String apiLetGo(){
        return "利用注解@ApiMapping放行";
    }

    @Operation(summary = "默认放行</user/login>")
    @ApiOperationSupport(order = 5)
    @GetMapping("/user/login")
    public String defaultLetGo(){
        return "默认放行</user/login>";
    }


    @Operation(summary = "login")
    @ApiOperationSupport(order = 6)
    @GetMapping("/login")
    public TokenSign token(){
        SignEntity<String> signEntity = SignEntity.init("tan");
        signEntity.setMap("hi");
        return loginService.login(signEntity);
    }


    @Operation(summary = "login2")
    @ApiOperationSupport(order = 7)
    @ApiMapping(value = "/login2",checkToken = false)
    public TokenSign token2(){
        SignEntity<TestBean> signEntity = SignEntity.initMap("tan",new TestBean("hi"));
        return loginService.login(signEntity);
    }

    @Operation(summary = "login3")
    @ApiOperationSupport(order = 8)
    @ApiMapping(value = "/login3",checkToken = false)
    public TokenSign token3(){
        Map<String, String> info = new HashMap<>();
        info.put("nickname","ning");
        SignEntity<Map<String, String>> signEntity = SignEntity.initMap("tan",info);
        return loginService.login(signEntity);
    }

    @Operation(summary = "login4")
    @ApiOperationSupport(order = 9)
    @ApiMapping(value = "/login4", checkToken = false)
    public TokenSign token4() {
        Map<String, String> info = new HashMap<>();
        info.put("nickname", "ning");
        SignEntity<LoginJwtExtendInfo<Map<String, String>>> signEntity = SignEntity.initMap("tan",
                new LoginJwtExtendInfo("tan", "1", "宁", info));
        return loginService.login(signEntity);
    }

    @Operation(summary = "isLogin")
    @ApiOperationSupport(order = 9)
    @GetMapping("/isLogin")
    public boolean isLogin(HttpServletRequest request){
        return loginService.isLogin(request);
    }


    @Operation(summary = "解析token")
    @ApiOperationSupport(order = 10)
    @GetMapping("/parseJwt")
    @Parameter(description = "0:login4, 1:login2, 2:login3, 其他:login1", name = "type")
    public SignEntity<Object> parseJwt(HttpServletRequest request, int type){
        if(type == 0){
            return JwtWebUtil.getTokenBySignEntity(request, LoginJwtExtendInfo.class);
        }else if (type == 1) {
            return JwtWebUtil.getTokenBySignEntity(request, TestBean.class);
        } else if (type == 2) {
            return JwtWebUtil.getTokenBySignEntity(request, Map.class);
        } else {
            return JwtWebUtil.getTokenBySignEntity(request, String.class);
        }
    }

    @Operation(summary = "subject")
    @ApiOperationSupport(order = 11)
    @GetMapping("/subject")
    public String subject(HttpServletRequest request){
        String token = JwtWebUtil.getToken(request);
        return JwtService.getSubjectExpires(token);
    }

}
