package cn.tannn.jdevelops.demo.apis.log.apislog.console.controller;


import cn.tannn.jdevelops.demo.apis.log.apislog.console.entity.UserEntity;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author tan
 */
@RequestMapping("get")
@Slf4j
@RestController
public class GetController {


    @GetMapping()
    public void test(String param){
        log.info("参数:{}", param);
    }

    /**
     * 测试get常规请求
     */
    @GetMapping("/v1")
    public void v1(@RequestParam("one") String one,
                    @RequestParam("two") BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }

    /**
     * 测试get rest  请求
     */
    @GetMapping("/v2/{one}/{two}")
    public void v2(@PathVariable("one") String one,
                    @PathVariable("two") BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }

    /**
     * 测试get map  请求
     */
    @GetMapping("/v3")
    public void v3(@RequestParam Map<String,String> map) {
        log.info("{}", new Gson().toJson(map));
    }

    /**
     * 测试get bean  请求
     */
    @GetMapping("/v4")
    public void v4(UserEntity map) {
        log.info("{}", map.toString());
    }

    /**
     * 测试get request  请求
     */
    @GetMapping("/v5")
    public void v5(String param, HttpServletRequest request) {
        log.info("{}", param);
    }


    /**
     * 测试get response  请求
     */
    @GetMapping("/v6")
    public void v6(String param,  HttpServletResponse response) {
        log.info("{}", param);
    }

    /**
     * 测试get response，request  请求
     */
    @GetMapping("/v7")
    public void v7(String param, HttpServletRequest request,  HttpServletResponse response) {
        log.info("{}", param);
    }


    /**
     * 测试get chinese  请求
     */
    @GetMapping("/chinese")
    public void chinese(String param, HttpServletRequest request,  HttpServletResponse response) {
        log.info("{}", param);
    }

}
