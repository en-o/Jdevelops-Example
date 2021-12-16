package cn.tannn.apiversion.controller;

import cn.jdevelops.version.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本
 *
 * @author tn
 * @version 1
 * @date 2021/1/30 21:53
 */
@RestController
public class HeaderVersionController {

    /*********         header                     *******/

    @GetMapping("/api/test_2")
    public String version1_0() {
        return "Hello,Welcome to default version header";
    }

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
    /*********         parameter                     *******/
}