package cn.tannn.apilog.controller;

import cn.jdevelops.apilog.annotation.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tn
 * @version 1
 * @date 2021-12-16 11:40
 */
@RestController
public class TestController {

    @GetMapping("/")
    public String test(String param){
        return param;
    }

    @GetMapping("/2")
    @ApiLog
    public String test2(String param){
        return param;
    }
}
