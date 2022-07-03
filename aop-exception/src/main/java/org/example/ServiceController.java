package org.example;

import cn.jdevelops.exception.annotation.DisposeException;
import cn.jdevelops.result.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-03 19:37
 */
@RestController
public class ServiceController {

    @Autowired
    private IService iService;



    /**
     * 测试类上使用
     * @return
     */
    @GetMapping("/e")
    public ResultVO<String> test1(){
        iService.test1();
        return ResultVO.success();
    }


    /**
     * 测试类上使用
     * @return
     */
    @GetMapping("/f")
    public ResultVO<String> test2(){
        iService.test2();
        return ResultVO.success();
    }
}
