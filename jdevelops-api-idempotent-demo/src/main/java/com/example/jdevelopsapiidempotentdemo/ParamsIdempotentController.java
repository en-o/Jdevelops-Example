package com.example.jdevelopsapiidempotentdemo;

import cn.jdevelops.api.idempotent.annotation.ApiIdempotent;
import cn.jdevelops.api.result.response.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/12 10:41
 */
@RestController
public class ParamsIdempotentController {


    /**
     * 自定义返回错误提示
     * @return  String
     */
    @GetMapping("message")
    @ApiIdempotent(message = "你再点封了你")
    public ResultVO<String> message(){
        return ResultVO.success("测试空参数");
    }

    /**
     * 将错误code放到 HttpServletResponse 中
     * @return  String
     */
    @GetMapping("responseStatus")
    @ApiIdempotent(responseStatus = true)
    public ResultVO<String> responseStatus(){
        return ResultVO.success("将错误code放到 HttpServletResponse 中");
    }



    /**
     *  局部：过期时间
     * @return  String
     */
    @GetMapping("expireTime")
    @ApiIdempotent(expireTime = 60000)
    public ResultVO<String> expireTime(){
        return ResultVO.success(" 局部：过期时间60000");
    }
}
