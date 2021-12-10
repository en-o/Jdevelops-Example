package cn.tannn.globalexception;

import cn.jdevelops.exception.exception.BusinessException;
import cn.jdevelops.result.result.ResultVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
