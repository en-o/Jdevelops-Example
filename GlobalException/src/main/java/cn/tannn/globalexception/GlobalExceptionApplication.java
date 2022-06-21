package cn.tannn.globalexception;

import cn.jdevelops.exception.exception.BusinessException;
import cn.jdevelops.result.result.ResultVO;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tnnn
 */
@SpringBootApplication
@RestController
public class GlobalExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalExceptionApplication.class, args);
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalErrors")
    public ResultVO<Integer> testGlobalErrors(){
        Integer i  = 1/0;
        return ResultVO.success(i, "测试全局异常拦截处理");
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalTry")
    public ResultVO<Integer> testGlobalTry(){
        throw  new BusinessException("测试全局异常拦截处理");
    }



    /**
     * 测试 ResultVO
     * @return ResultVO
     */
    @GetMapping("/testResult0")
    public ResultVO<Integer> testResult0(){
        return ResultVO.successForData(1);
    }



    /**
     * 测试 ResultVO
     * @return ResultVO
     */
    @GetMapping("/testResult1")
    public ResultVO<Integer> testResult1(){
        return ResultVO.<Integer>builder().code(100).message("测试").data(1).build();
    }


    /**
     * 测试 ResultVO
     * @return ResultVO
     */
    @GetMapping("/testResult2")
    public ResultVO<Map<String,String>> testResult2(){
        return ResultVO.<Map<String,String>>builder().code(100).message("测试")
                .data(ImmutableMap.of("key","value")).build();
    }



}
