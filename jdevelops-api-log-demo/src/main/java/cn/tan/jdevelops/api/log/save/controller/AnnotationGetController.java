package cn.tan.jdevelops.api.log.save.controller;

import cn.jdevelops.aop.api.log.annotation.ApiLog;
import cn.jdevelops.aop.api.log.enums.OperateTypeEnum;
import cn.jdevelops.api.result.response.ResultVO;
import cn.tan.jdevelops.api.log.console.entity.UserEntity;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;


@RequestMapping("annotation/get")
@Slf4j
@RestController
public class AnnotationGetController {


    @GetMapping()
    @ApiLog(type = OperateTypeEnum.GET ,description = "测试",chineseApi = "测试")
    public void test(String param){
        log.info("参数:{}", param);
    }

    /**
     * 测试get常规请求
     */
    @GetMapping("/v1")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{one}",description = "测试v1",chineseApi = "v1")
    public void v1(@RequestParam("one") String one,
                   @RequestParam("two") BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }

    /**
     * 测试get rest  请求
     */
    @GetMapping("/v2/{one}/{two}")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{one}",description = "测试v2",chineseApi = "v2")
    public void v2(@PathVariable("one") String one,
                   @PathVariable("two") BigDecimal number) {
        log.info("参数:{},{}", one, number);
    }

    /**
     * 测试get map  请求
     */
    @GetMapping("/v3")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{map.tan}",description = "测试v3",chineseApi = "v3")
    public void v3(@RequestParam Map<String,String> map) {
        log.info("{}", new Gson().toJson(map));
    }

    /**
     * 测试get bean  请求
     */
    @GetMapping("/v4")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{map.one}",description = "测试v4",chineseApi = "v4")
    public void v4(UserEntity map) {
        log.info("{}", map.toString());
    }

    /**
     * 测试get request  请求
     */
    @GetMapping("/v5")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{param}",description = "测试v5",chineseApi = "v5")
    public void v5(String param, HttpServletRequest request) {
        log.info("{}", param);
    }


    /**
     * 测试get response  请求
     */
    @GetMapping("/v6")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{param}",description = "测试v6",chineseApi = "v6")
    public void v6(String param,  HttpServletResponse response) {
        log.info("{}", param);
    }

    /**
     * 测试get response，request  请求
     */
    @GetMapping("/v7")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{param}",description = "测试v7",chineseApi = "v7")
    public void v7(String param, HttpServletRequest request,  HttpServletResponse response) {
        log.info("{}", param);
    }


    /**
     * 测试get chinese  请求
     */
    @GetMapping("/chinese")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{param}",description = "测试chinese",chineseApi = "chinese")
    public void chinese(String param, HttpServletRequest request,  HttpServletResponse response) {
        log.info("{}", param);
    }

}
