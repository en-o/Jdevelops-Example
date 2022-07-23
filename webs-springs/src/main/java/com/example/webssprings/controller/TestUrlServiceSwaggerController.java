package com.example.webssprings.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 *  测试 获取所有接口
 * @author tnnn
 * @version V1.0
 * @date 2022-07-17 00:55
 */
//

@Api(tags = "测试获取所有接口", value = "测试获取所有接口")
@RequestMapping("test/urr/swagger")
@RestController
public class TestUrlServiceSwaggerController {


    @PostMapping
    @ApiOperation(value = "获取所有接口0-1", notes = "测试获取所有接口")
    public String hi1(){
        return "获取所有接口0-1";
    }

    @DeleteMapping
    @ApiOperation(value = "获取所有接口0-2", notes = "测试获取所有接口")
    public String hi2(){
        return "获取所有接口0-2";
    }

    @GetMapping("test1")
    @ApiOperation(value = "获取所有接口1", notes = "测试获取所有接口")
    public String test1(){
        return "获取所有接口1";
    }

    @PostMapping("test2")
    @ApiOperation(value = "获取所有接口2", notes = "测试获取所有接口")
    public String test2(){
        return "获取所有接口2";
    }

    @DeleteMapping("test2")
    @ApiOperation(value = "获取所有接口3", notes = "测试获取所有接口")
    public String test3(){
        return "获取所有接口3";
    }

    @PutMapping("test4")
    @ApiOperation(value = "获取所有接口4", notes = "测试获取所有接口")
    public String test4(){
        return "获取所有接口4";
    }

}
