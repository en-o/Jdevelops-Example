package com.example.jdevelopsaopexceptiondemo.controller;


import com.example.jdevelopsaopexceptiondemo.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-03 19:37
 */
@RestController
public class ServiceController {

    @Autowired
    private IService iService;



    /**
     * 测试类上使用
     * @return
     */
    @GetMapping("/e")
    public Object test1(){
        iService.test1();
        return "ok";
    }


    /**
     * 测试类上使用
     * @return
     */
    @GetMapping("/f")
    public Object test2(){
        iService.test2();
        return "ok";
    }
}
