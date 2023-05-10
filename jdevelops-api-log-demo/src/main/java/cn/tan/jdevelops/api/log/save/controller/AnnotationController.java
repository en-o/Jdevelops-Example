package cn.tan.jdevelops.api.log.save.controller;

import cn.jdevelops.aop.api.log.annotation.ApiLog;
import cn.jdevelops.api.result.response.ResultVO;
import cn.tan.jdevelops.api.log.console.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


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




    @GetMapping("/2")
    @ApiLog
    public String test2(String param){
        return param;
    }


    @PostMapping("/3")
    @ApiLog
    public String test3(@RequestBody Map<String,Object> test){
        return test.toString();
    }


    @PostMapping("/4")
    @ApiLog(expression = "#{test.param}")
    public String test34(@RequestBody Map<String,Object> test){
        return test.toString();
    }


    @PostMapping("/5")
    @ApiLog(expression = "#{test.param}")
    public String test34(@RequestBody TestBean test){
        return test.toString();
    }


    /**
     * 测试返回 ResultVO对象
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/result/vo")
    @ApiLog(expression = "resultVo")
    public ResultVO<String> resultVo(String param){
        if(param.equals("0")){
            return ResultVO.fail(param);
        } else if (param.equals("1")) {
            throw new RuntimeException("RE");
        } else{
            return ResultVO.success(param);
        }

    }


    /**
     * 测试返回 多参数
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/mp")
    @ApiLog(expression = "mp")
    public ResultVO<String> mp(String param,String param2){
        return ResultVO.fail(param+param2);
    }


    /**
     * 测试返回 多参数2(request)
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/mp2")
    @ApiLog(expression = "mp2")
    public ResultVO<String> mp2(String param,HttpServletRequest request){
        return ResultVO.success(param);
    }

    /**
     * 测试返回 多参数3(response)
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/mp3")
    @ApiLog(expression = "mp3")
    public ResultVO<String> mp3(String param, HttpServletResponse response){
        return ResultVO.success(param);
    }

    /**
     * 测试返回 多参数4(request,response)
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/mp4")
    @ApiLog(expression = "mp4")
    public ResultVO<String> mp4(String param, HttpServletRequest request, HttpServletResponse response){
        return ResultVO.success(param);
    }


    /**
     * 测试chineseApi参数
     * @param param param
     * @return String of ResultVO
     */
    @GetMapping("/chinese")
    @ApiLog(expression = "chinese",chineseApi = "测试chineseApi参数")
    public ResultVO<String> chineseApi(String param, HttpServletRequest request, HttpServletResponse response){
        return ResultVO.success(param);
    }

}
