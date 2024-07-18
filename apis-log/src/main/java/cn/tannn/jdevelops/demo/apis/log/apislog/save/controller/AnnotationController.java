package cn.tannn.jdevelops.demo.apis.log.apislog.save.controller;

import cn.tannn.jdevelops.apis.log.annotation.ApiLog;
import cn.tannn.jdevelops.apis.log.constants.OperateType;
import cn.tannn.jdevelops.demo.apis.log.apislog.console.entity.UserEntity;
import cn.tannn.jdevelops.result.response.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@RequestMapping("annotation/test")
@Slf4j
@RestController
public class AnnotationController {


    /**
     * 返回String
     */
    @GetMapping("/v1")
    @ApiLog(type = OperateType.GET, expression = "#{map.one}", description = "测试v1", chineseApi = "v1")
    public ResultVO<String> v4(UserEntity map) {
        log.info("{}", map.toString());
        return ResultVO.success(map.toString());
    }

    /**
     * 返回对象
     */
    @PostMapping("/v2")
    @ApiLog(type = OperateType.GET, expression = "#{user}", description = "测试v2", chineseApi = "v2")
    public ResultVO<UserEntity> v2(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.success(user);
    }


    /**
     * 不记录日志
     */
    @PostMapping("/v3")
    @ApiLog(type = OperateType.GET,
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
    @ApiLog(type = OperateType.GET,
            expression = "#{user}",
            logArgs = false,
            description = "测试v4",
            chineseApi = "v4")
    public ResultVO<UserEntity> v4(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.success(user);
    }

    /**
     * 不记录出参
     */
    @PostMapping("/v5")
    @ApiLog(type = OperateType.GET,
            expression = "#{user}",
            logResultData = false,
            description = "测试v5",
            chineseApi = "v5")
    public ResultVO<UserEntity> v5(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.success(user);
    }

    /**
     * 不记录入参和出参
     */
    @PostMapping("/v6")
    @ApiLog(type = OperateType.GET,
            expression = "#{user}",
            logResultData = false,
            logArgs = false,
            description = "测试v6",
            chineseApi = "v6")
    public ResultVO<UserEntity> v6(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.success(user);
    }


    /**
     * 记录入参和出参 - 返回state : false
     */
    @PostMapping("/v7")
    @ApiLog(type = OperateType.GET,
            expression = "#{user}",
            description = "测试v7",
            chineseApi = "v7")
    public ResultVO<UserEntity> v7(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.fail(user);
    }


    /**
     * 不记录入参和出参 - 返回state : false
     */
    @PostMapping("/v8")
    @ApiLog(type = OperateType.GET,
            logResultData = false,
            logArgs = false,
            expression = "#{user}",
            description = "测试v8",
            chineseApi = "v8")
    public ResultVO<UserEntity> v8(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.fail(user);
    }

    /**
     * 不记录入参和出参 和 不影响 GlobalApiLogPrint
     */
    @PostMapping("/v9")
    @ApiLog(type = OperateType.GET,
            expression = "#{user}",
            logResultData = false,
            logArgs = false,
            description = "测试v9",
            consolEenable = false,
            chineseApi = "v9")
    public ResultVO<UserEntity> v9(@RequestBody UserEntity user, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", user.toString());
        return ResultVO.success(user);
    }
}
