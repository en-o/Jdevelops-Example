package com.example.webdemo.controller;

import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.version.annotation.ApiVersion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本
 * swagger 测试时，将文档管理->个性化设置->开启动态请求参数
 *  在请求时，handler 中添加 version 属性
 * @author tn
 * @version 1
 * @date 2021/1/30 21:53
 */
@Tag(name = "测试接口版本")
@PathRestController("version")
public class HeaderVersionController {

    /*********         header                     *******/


    @GetMapping("/api/test_2")
    @ApiVersion()
    public String version1_1() {
        return "Hello,Welcome to version 1.0 header";
    }

    @GetMapping("/api/test_2")
    @ApiVersion(2.0)
    public String version2_2() {
        return "Hello,Welcome to version 2.0 header";
    }

    @GetMapping("/api/test_2")
    @ApiVersion(3.0)
    public String version3_2() {
        return "Hello,Welcome to version 3.0 header";
    }


    @GetMapping("/api/test_2")
    @ApiVersion(21312312312312312313213.123123123)
    public String version4_2() {
        return "Hello,Welcome to version 21312312312312312313213.123123123 header";
    }

    /*********         parameter                     *******/
}
