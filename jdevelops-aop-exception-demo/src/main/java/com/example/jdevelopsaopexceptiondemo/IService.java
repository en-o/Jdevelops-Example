package com.example.jdevelopsaopexceptiondemo;


import cn.jdevelops.aop.exception.exception.annotation.DisposeException;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-03 19:36
 */
@Service
@DisposeException(messages ={"错误的零","下标越界"}, codes = {500,501}, exceptions = {
        ArithmeticException.class,
        ArrayIndexOutOfBoundsException.class} )
public class IService {

    /**
     * 单个异常
     * @return
     */
    public Object test1(){
        int zero = 1 / 0;
        return "ok";
    }
    /**
     * 多个异常处理
     * @return
     */

    public Object  test2(){
        int[] indexs = {1,2,3};
        System.out.println(indexs[4]);
        return "ok";
    }
}
