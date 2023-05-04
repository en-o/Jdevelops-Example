package com.example.jdevelopsaopexceptiondemo;

import cn.jdevelops.aop.exception.exception.annotation.DisposeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-06-24 17:23
 */
@RestController
public class Controller {


    /**
     * 单个异常
     * @return
     */
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
    @GetMapping("/b")
    @DisposeException(messages ={"错误的零","下标越界"}, codes = {500,501}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    public Object  test2(int i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return "ok";
    }


    /**
     * 全部公用默认code
     * @param i
     * @return
     */
    @GetMapping("/c")
    @DisposeException(messages ={"错误的零","下标越界"}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    public Object  test3(int i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return "ok";
    }


    /**
     * 全部公用默认code,和原本的异常消息
     * @param i
     * @return
     */
    @GetMapping("/d")
    @DisposeException(messages ={"你错了"}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    public Object  test4(int i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return "ok";
    }
}
