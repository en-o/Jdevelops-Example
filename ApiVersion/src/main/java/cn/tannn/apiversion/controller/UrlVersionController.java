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
public class UrlVersionController {

    /*********         url                     *******/

    @GetMapping("/api/test/")
    public String version0() {
        return "Hello,Welcome to default version url";
    }


    @GetMapping("/api/test/{version}")
    @ApiVersion()
    public String version1() {
        return "Hello,Welcome to version 1.0 url";
    }

    @GetMapping("/api/test/{version}")
    @ApiVersion(2.0)
    public String version2() {
        return "Hello,Welcome to version 2.0 url";
    }

    @GetMapping("/api/test/{version}")
    @ApiVersion(3.0)
    public String version3() {
        return "Hello,Welcome to version 3.0 url";
    }

    /*********         url                     *******/

}
