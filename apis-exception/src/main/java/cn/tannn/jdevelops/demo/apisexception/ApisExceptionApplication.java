package cn.tannn.jdevelops.demo.apisexception;

import cn.tannn.jdevelops.demo.apisexception.reset.ReplaceResultVO;
import cn.tannn.jdevelops.exception.built.BusinessException;
import cn.tannn.jdevelops.exception.built.LoginLimitException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApisExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApisExceptionApplication.class, args);
    }
    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalErrors")
    public ResultVO<Integer> testGlobalErrors(){
        Integer i  = 1/0;
        return ResultVO.success( "测试全局异常拦截处理",i);
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
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalErrors2")
    public ReplaceResultVO<Integer> testGlobalErrors2(){
        Integer i  = 1/0;
        return ReplaceResultVO.success();
    }

    /**
     * 测试全局异常拦截处理
     * @return ResultVO
     */
    @GetMapping("/testGlobalTry2")
    public ReplaceResultVO<Integer> testGlobalTry2(){
        throw  new BusinessException("测试全局异常拦截处理");
    }



    /**
     * 测试覆盖全局的  httpServletResponseStatus
     * @return ResultVO
     */
    @GetMapping("/testGlobalTry3")
    public ReplaceResultVO<Integer> testGlobalTry3(){
//        throw new BusinessException(LOGIN_LIMIT).setHttpServletResponseStatus(true);
        throw new LoginLimitException(LOGIN_LIMIT).setHttpServletResponseStatus(false);
    }
}
