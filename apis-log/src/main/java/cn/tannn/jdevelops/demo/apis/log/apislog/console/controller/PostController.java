package cn.tannn.jdevelops.demo.apis.log.apislog.console.controller;


import cn.tannn.jdevelops.demo.apis.log.apislog.console.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author tan
 */
@RequestMapping("post")
@Slf4j
@RestController
public class PostController {

    @PostMapping("/v1")
    public void v1(@RequestBody UserEntity user) {
        log.info("{}", user.toString());
    }


    /**
     * form-data
     */
    @PostMapping("/v2")
    public void v2(@RequestParam UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v21")
    public void v2_1(UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v3")
    public void v3(String one,BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }



    @PostMapping("/v4")
    public void v4(@RequestParam Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }

    /**
     * RequestBody-Map
     */
    @PostMapping("/v5")
    public void v5(@RequestBody Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }


    @PostMapping("/v6")
    public void v6(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }
    @PostMapping("/v7")
    public void v7(@RequestBody UserEntity user, HttpServletRequest request) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v8")
    public void v8(@RequestBody UserEntity user, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v9")
    public void v9(String one,BigDecimal number, HttpServletResponse response) {
        log.info("参数:{},{}", one, number);
    }

    @PostMapping("/v10")
    public void v10(@RequestParam("one")String one) {
        log.info("参数:{}", one);
    }
}
