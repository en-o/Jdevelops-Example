package cn.tannn.redisidempotent.controller;

import cn.jdevelops.idempotent.annotation.ApiIdempotent;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.redisidempotent.entity.UserEntity;
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
public class IdempotentController {

    /**
     * 不幂等的接口
     * @return  String
     */
    @GetMapping("notIdempotent")
    public ResultVO<String> notIdempotent(){
        return ResultVO.success("不幂等的接口");
    }


    /**
     * 测试get空参数
     * @return  String
     */
    @GetMapping("getNull")
    @ApiIdempotent
    public ResultVO<String> getNull(){
        return ResultVO.success("测试空参数");
    }

    /**
     * 测试get有参数
     * @return  String
     */
    @GetMapping("getParam")
    @ApiIdempotent
    public ResultVO<String> getParam(String v1, Integer v2){
        return ResultVO.success("测试get有参数");
    }

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
}
