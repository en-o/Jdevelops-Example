package cn.tan.jdevelops.api.log.controller;


import cn.tan.jdevelops.api.log.entity.UserEntity;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author tan
 */
@RequestMapping("post")
@Slf4j
public class PostController {

    @PostMapping("/v1/post")
    public void v1(@RequestBody UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v2/post")
    public void v2(UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v3/post")
    public void v3(String one,BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }

    @PostMapping("/v4/post")
    public void v4(@RequestParam Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }

    @PostMapping("/v5/post")
    public void v5(@RequestBody Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }


}
