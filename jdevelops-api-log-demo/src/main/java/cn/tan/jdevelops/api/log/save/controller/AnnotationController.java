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


@RequestMapping("annotation/test")
@Slf4j
@RestController
public class AnnotationController {


    /**
     * 返回String
     */
    @GetMapping("/v1")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{map.one}",description = "测试v1",chineseApi = "v1")
    public ResultVO<String> v4(UserEntity map) {
        log.info("{}", map.toString());
        return ResultVO.success();
    }

    /**
     * 返回对象
     */
    @PostMapping("/v2")
    @ApiLog(type = OperateTypeEnum.GET ,expression = "#{user}",description = "测试v2",chineseApi = "v2")
    public ResultVO<UserEntity> v2(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
       return ResultVO.successForData(user);
    }


    /**
     * 不记录日志
     */
    @PostMapping("/v3")
    @ApiLog(type = OperateTypeEnum.GET ,
            enable = false,
            expression = "#{user}",
            description = "测试v3",
            chineseApi = "v3")
    public ResultVO<String> v3() {
        return ResultVO.success();
    }


    /**
     * 不记录入参
     */
    @PostMapping("/v4")
    @ApiLog(type = OperateTypeEnum.GET ,
            expression = "#{user}",
            logArgs = false,
            description = "测试v4",
            chineseApi = "v4")
    public ResultVO<UserEntity> v4(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.successForData(user);
    }

    /**
     * 不记录出参
     */
    @PostMapping("/v5")
    @ApiLog(type = OperateTypeEnum.GET ,
            expression = "#{user}",
            logResultData = false,
            description = "测试v5",
            chineseApi = "v5")
    public ResultVO<UserEntity> v5(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.successForData(user);
    }

    /**
     * 不记录入参和出参
     */
    @PostMapping("/v6")
    @ApiLog(type = OperateTypeEnum.GET ,
            expression = "#{user}",
            logResultData = false,
            logArgs = false,
            description = "测试v6",
            chineseApi = "v6")
    public ResultVO<UserEntity> v6(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.successForData(user);
    }

}
