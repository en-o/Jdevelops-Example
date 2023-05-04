package cn.tan.jdevelops.api.log.controller;

import cn.jdevelops.aop.api.log.annotation.ApiLog;
import cn.tan.jdevelops.api.log.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("annotation")
@Slf4j
@RestController
public class AnnotationController {

    /**
     * 测试get请求
     */
    @GetMapping("/get/v1")
    @ApiLog
    public void gv1(String param, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", param);
    }

    /**
     * 测试post
     */
    @PostMapping("/post/v1")
    @ApiLog
    public void pv1(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }


    /**
     * 测试post  apilog 取值
     */
    @PostMapping("/post/v2")
    @ApiLog(description = "#{user.one}")
    public void pv2(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

}
