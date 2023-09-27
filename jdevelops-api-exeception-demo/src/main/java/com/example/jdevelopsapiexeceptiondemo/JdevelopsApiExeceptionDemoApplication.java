package com.example.jdevelopsapiexeceptiondemo;

import cn.jdevelops.api.exception.exception.BusinessException;
import cn.jdevelops.api.result.response.ResultVO;
import com.example.jdevelopsapiexeceptiondemo.result.ReplaceResultVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class JdevelopsApiExeceptionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevelopsApiExeceptionDemoApplication.class, args);
    }
    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalErrors")
    public ResultVO<Integer> testGlobalErrors(){
        Integer i  = 1/0;
        return ResultVO.success( "测试全局异常拦截处理",i);
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalTry")
    public ResultVO<Integer> testGlobalTry(){
        throw  new BusinessException("测试全局异常拦截处理");
    }


    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalErrors2")
    public ReplaceResultVO<Integer> testGlobalErrors2(){
        Integer i  = 1/0;
        return ReplaceResultVO.success();
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalTry2")
    public ReplaceResultVO<Integer> testGlobalTry2(){
        throw  new BusinessException("测试全局异常拦截处理");
    }



    /**
     * 测试隐式添加包裹类
     * @return ResultVO or ReplaceResultVO
     */
    @GetMapping("/resultHandlerMethodReturnValueHandler")
    public String resultHandlerMethodReturnValueHandler(){
        return "测试隐式添加包裹类";
    }



}
