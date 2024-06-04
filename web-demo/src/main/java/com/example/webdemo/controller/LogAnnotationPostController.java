package com.example.webdemo.controller;

import cn.jdevelops.aop.api.log.annotation.ApiLog;
import cn.jdevelops.aop.api.log.enums.OperateTypeEnum;
import cn.jdevelops.api.annotation.mapping.PathRestController;
import com.example.webdemo.controller.dto.LogUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;


@Tag(name = "测试aopLog")
@PathRestController("/log/annotation/post/")
@Slf4j
public class LogAnnotationPostController {


    @Operation(summary = "v1")
    @PostMapping("/v1")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user.one}",description = "测试v1",chineseApi = "v1")
    public void v1(@RequestBody LogUserDTO user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v2")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user.one}",description = "测试v21",chineseApi = "v21")
    public void v2_1(LogUserDTO user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v3")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{number}",description = "测试v3",chineseApi = "v3")
    public void v3(String one, BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }



    @PostMapping("/v4")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{test.qq}",description = "测试v4",chineseApi = "v4")
    public void v4(@RequestParam Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }

    /**
     * RequestBody-Map
     */
    @PostMapping("/v5")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{test.qq}",description = "测试v5",chineseApi = "v5")
    public void v5(@RequestBody Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }


    @PostMapping("/v6")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user.one}",description = "测试v6",chineseApi = "v6")
    public void v6(@RequestBody LogUserDTO user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }
    @PostMapping("/v7")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user.one}",description = "测试v7",chineseApi = "v7")
    public void v7(@RequestBody LogUserDTO user, HttpServletRequest request) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v8")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user.one}",description = "测试v8",chineseApi = "v8")
    public void v8(@RequestBody LogUserDTO user, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v9")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{one}",description = "测试v9",chineseApi = "v9")
    public void v9(String one,BigDecimal number, HttpServletResponse response) {
        log.info("参数:{},{}", one, number);
    }

    @PostMapping("/v10")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{one}",description = "测试v10",chineseApi = "v10")
    public void v10(@RequestParam("one")String one) {
        log.info("参数:{}", one);
    }


    @PostMapping("/v11")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user}",description = "测试v11",chineseApi = "v11")
    public void v11(@RequestBody LogUserDTO user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

}
