package com.example.webdemo.controller;


import cn.jdevelops.aop.exception.exception.annotation.DisposeException;
import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.exception.exception.BusinessException;
import cn.jdevelops.api.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "测试异常")
@PathRestController("/ex")
public class ExeceptionController {

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @Operation(summary = "测试全局异常拦截处理1")
    @GetMapping("/testGlobalErrors")
    public ResultVO<Integer> testGlobalErrors(){
        Integer i  = 1/0;
        return ResultVO.success( "测试全局异常拦截处理",i);
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @Operation(summary = "测试全局异常拦截处理2")
    @GetMapping("/testGlobalTry")
    public ResultVO<Integer> testGlobalTry(){
        throw  new BusinessException("测试全局异常拦截处理");
    }


    /**
     * 单个异常
     * @return
     */
    @Operation(summary = "单个异常")
    @GetMapping("/a")
    @DisposeException(messages ="错误的零", codes = 500, exceptions = ArithmeticException.class )
    public Object  test1(){
        int zero = 1 / 0;
        return "ok";
    }


    /**
     * 多个异常处理
     * @param i
     * @return
     */
    @Operation(summary = "多个异常处理")
    @GetMapping("/b")
    @DisposeException(messages ={"错误的零","下标越界"}, codes = {500,501}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    @Parameter(name = "i",description = "1: 错误的零,其他:下标越界", example = "1")
    public Object  test2(Integer i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return "ok";
    }
}
