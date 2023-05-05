package com.example.jdevelopseventredisdemo;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.redis.core.config.RedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class JdevelopsEventRedisDemoApplication {

    @Autowired
    private RedisProxy redisProxy;


    public static void main(String[] args) {
        SpringApplication.run(JdevelopsEventRedisDemoApplication.class, args);
    }
    @GetMapping("/set/{msg}")
    public ResultVO<Boolean> set(@PathVariable String msg){
        return ResultVO.successForData(redisProxy.set("test", msg));
    }

    @GetMapping("/get")
    public ResultVO<Object> get(){
        return ResultVO.successForData(redisProxy.get("test"));
    }
}
