package com.example.webdemo.controller;


import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "测试")
@PathRestController("/test")
public class TestController {


    @GetMapping("hi")
    public ResultVO<String> hi(){
        return ResultVO.success("hi");
    }
}
