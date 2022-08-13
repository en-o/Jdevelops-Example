package cn.tannn.apilog.controller;

import cn.jdevelops.apilog.annotation.ApiLog;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/lm")
    public String lm(String param,String test){
        return param+test;
    }

    @PostMapping("/lm1")
    public String lm1(String param,String test){
        return param+test;
    }

    @PostMapping("/lm2")
    public String lm2(@RequestParam Map<String,Object> test){
        return test.toString();
    }

    @PostMapping("/lm3")
    public String lm3(@RequestBody Map<String,Object> test){
        return test.toString();
    }

    @GetMapping("/2")
    @ApiLog
    public String test2(String param){
        return param;
    }


    @PostMapping("/3")
    @ApiLog
    public String test3(@RequestBody Map<String,Object> test){
        return test.toString();
    }


    @PostMapping("/4")
    @ApiLog(apiKey = "#{test.param}")
    public String test34(@RequestBody Map<String,Object> test){
        return test.toString();
    }


    @PostMapping("/5")
    @ApiLog(apiKey = "#{test.param}")
    public String test34(@RequestBody TestBean test){
        return test.toString();
    }
}
