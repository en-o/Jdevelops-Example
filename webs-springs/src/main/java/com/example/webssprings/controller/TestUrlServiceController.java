package com.example.webssprings.controller;


import org.springframework.web.bind.annotation.*;

/**
 * 测试 获取所有接口
 * @author tnnn
 * @version V1.0
 * @date 2022-07-17 00:55
 */
@RequestMapping("test/url")
@RestController
public class TestUrlServiceController {


    @PostMapping
    public String hi1(){
        return "获取所有接口0-1";
    }

    @DeleteMapping
    public String hi2(){
        return "获取所有接口0-2";
    }

    @GetMapping("test1")
    public String test1(){
        return "获取所有接口1";
    }

    @PostMapping("test2")
    public String test2(){
        return "获取所有接口2";
    }

    @DeleteMapping("test2")
    public String test3(){
        return "获取所有接口3";
    }

    @PutMapping("test4")
    public String test4(){
        return "获取所有接口4";
    }


}
