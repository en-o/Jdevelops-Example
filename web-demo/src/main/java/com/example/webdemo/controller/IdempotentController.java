package com.example.webdemo.controller;

import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.idempotent.annotation.ApiIdempotent;
import cn.jdevelops.api.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 幂等接口测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-17 16:30
 */
@Tag(name = "测试接口幂等")
@PathRestController("/idempotent")
public class IdempotentController {

    /**
     * 不幂等的接口
     * @return  String
     */
    @Operation(summary = "不幂等的接口")
    @GetMapping("notIdempotent")
    public ResultVO<String> notIdempotent(){
        return ResultVO.success("不幂等的接口");
    }


    /**
     * 测试get空参数
     * @return  String
     */
    @Operation(summary = "测试get空参数")
    @GetMapping("getNull")
    @ApiIdempotent
    public ResultVO<String> getNull(){
        return ResultVO.success("测试空参数");
    }


    /**
     * 自定义返回错误提示
     * @return  String
     */
    @Operation(summary = "你再点封了你")
    @GetMapping("message")
    @ApiIdempotent(message = "你再点封了你")
    public ResultVO<String> message(){
        return ResultVO.success("测试空参数");
    }

    /**
     * 测试get有参数
     * @return  String
     */
    @Operation(summary = "测试get有参数")
    @GetMapping("getParam")
    @ApiIdempotent
    public ResultVO<String> getParam(String v1, Integer v2){
        return ResultVO.success("测试get有参数");
    }

    /**
     * 测试post普通参数
     * @return  String
     */
    @Operation(summary = "测试post普通参数")
    @PostMapping("postGeneral")
    @ApiIdempotent
    public ResultVO<String> postGeneral(String v1){
        return ResultVO.success("测试post普通参数");
    }



}
