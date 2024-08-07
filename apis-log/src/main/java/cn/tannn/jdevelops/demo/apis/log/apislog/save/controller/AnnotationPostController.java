package cn.tannn.jdevelops.demo.apis.log.apislog.save.controller;

import cn.tannn.jdevelops.apis.log.annotation.ApiLog;
import cn.tannn.jdevelops.apis.log.constants.OperateType;
import cn.tannn.jdevelops.demo.apis.log.apislog.console.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RequestMapping("annotation/post/")
@Slf4j
@RestController
public class AnnotationPostController {

    @PostMapping("/v1")
    @ApiLog(type = OperateType.GET ,expression = "#{user.one}",description = "测试v1",chineseApi = "v1")
    public void v1(@RequestBody UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v2")
    @ApiLog(type = OperateType.GET ,expression = "#{user.one}",description = "测试v21",chineseApi = "v21")
    public void v2_1(UserEntity user) {
        log.info("{}", user.toString());
    }


    @PostMapping("/v3")
    @ApiLog(type = OperateType.GET ,expression = "#{number}",description = "测试v3",chineseApi = "v3")
    public void v3(String one, BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }



    @PostMapping("/v4")
    @ApiLog(type = OperateType.GET ,expression = "#{test.qq}",description = "测试v4",chineseApi = "v4")
    public void v4(@RequestParam Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }

    /**
     * RequestBody-Map
     */
    @PostMapping("/v5")
    @ApiLog(type = OperateType.GET ,expression = "#{test.qq}",description = "测试v5",chineseApi = "v5")
    public void v5(@RequestBody Map<String,Object> test){
        log.info("参数:{}", test.toString());
    }


    @PostMapping("/v6")
    @ApiLog(type = OperateType.GET ,expression = "#{user.one}",description = "测试v6",chineseApi = "v6")
    public void v6(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }
    @PostMapping("/v7")
    @ApiLog(type = OperateType.GET ,expression = "#{user.one}",description = "测试v7",chineseApi = "v7")
    public void v7(@RequestBody UserEntity user, HttpServletRequest request) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v8")
    @ApiLog(type = OperateType.GET ,expression = "#{user.one}",description = "测试v8",chineseApi = "v8")
    public void v8(@RequestBody UserEntity user, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

    @PostMapping("/v9")
    @ApiLog(type = OperateType.GET ,expression = "#{one}",description = "测试v9",chineseApi = "v9")
    public void v9(String one,BigDecimal number, HttpServletResponse response) {
        log.info("参数:{},{}", one, number);
    }

    @PostMapping("/v10")
    @ApiLog(type = OperateType.GET ,expression = "#{one}",description = "测试v10",chineseApi = "v10")
    public void v10(@RequestParam("one")String one) {
        log.info("参数:{}", one);
    }


    @PostMapping("/v11")
    @ApiLog(type = OperateType.GET ,expression = "#{user}",description = "测试v11",chineseApi = "v11")
    public void v11(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
    }

}
