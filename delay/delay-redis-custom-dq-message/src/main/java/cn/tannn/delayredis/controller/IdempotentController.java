package cn.tannn.delayredis.controller;

import cn.tannn.jdevelops.idempotent.annotation.ApiIdempotent;
import cn.tannn.jdevelops.result.response.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
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

}
