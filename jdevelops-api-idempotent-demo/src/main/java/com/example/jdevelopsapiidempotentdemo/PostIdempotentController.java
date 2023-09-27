package com.example.jdevelopsapiidempotentdemo;

import cn.jdevelops.api.idempotent.annotation.ApiIdempotent;
import cn.jdevelops.api.result.response.ResultVO;
import com.example.jdevelopsapiidempotentdemo.entity.UserEntity;
import com.example.jdevelopsapiidempotentdemo.entity.UserEntity2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 幂等接口测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-11-17 16:30
 */
@RestController
public class PostIdempotentController {

    /**
     * 测试post普通参数
     * @return  String
     */
    @PostMapping("postGeneral")
    @ApiIdempotent
    public ResultVO<String> postGeneral(String v1){
        return ResultVO.success("测试post普通参数");
    }

    /**
     * 测试post json参数
     * @return  String
     */
    @PostMapping("postJson")
    @ApiIdempotent
    public ResultVO<String> postJson(@RequestBody UserEntity user){
        return ResultVO.success("测试post json参数"+user);
    }


    /**
     * 测试post json参数参数多一点是试试
     * @return  String
     */
    @PostMapping("postJsonMultiparameter")
    @ApiIdempotent
    public ResultVO<String> postJsonMultiparameter(@RequestBody UserEntity2 user){
        return ResultVO.success("测试post json参数参数多一点是试试"+user);
    }
}
