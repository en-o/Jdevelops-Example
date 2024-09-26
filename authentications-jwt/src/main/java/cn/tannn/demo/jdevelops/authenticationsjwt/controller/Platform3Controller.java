package cn.tannn.demo.jdevelops.authenticationsjwt.controller;

import cn.tannn.jdevelops.annotations.web.authentication.ApiPlatform;
import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 apiPlatform注解
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/10/31 13:46
 */
@RestController
@ApiPlatform(platform = {PlatformConstant.WEB_ADMIN}, filter = {"/classSetting5"})
public class Platform3Controller {

    @GetMapping("classSetting4")
    public String APPLET_WEB_ADMIN_4(){
        return "4在类上使用 ApiPlatform WEB_ADMIN";
    }

    @GetMapping("classSetting3")
    public String APPLET_WEB_ADMIN_3(){
        return "3在类上使用 ApiPlatform WEB_ADMIN";
    }


    @GetMapping("classSetting5")
    public String APPLET_WEB_ADMIN_5(){
        return "放行我的 platform 拦截";
    }
}
