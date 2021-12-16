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
public class ParameterVersionController {


    /*********         parameter                     *******/

    @GetMapping("/api/test_1")
    @ApiVersion(1.0)
    public String version1_1(String version) {
        return "Hello,Welcome to version 1.0 parameter";
    }

    @GetMapping("/api/test_1")
    @ApiVersion(2.0)
    public String version2_1(String version) {
        return "Hello,Welcome to version 2.0 parameter";
    }

    @GetMapping("/api/test_1")
    @ApiVersion(3.0)
    public String version3_1(String version) {
        return "Hello,Welcome to version 3.0 parameter";
    }
    /*********         parameter                     *******/


}