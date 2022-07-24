package cn.tannn.jwt.controller;

import cn.jdevelops.jwt.annotation.ApiMapping;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.jwt.result.ReplaceResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
public class JwtController {

    @GetMapping("stop")
    public String stop(){
        return "拦截我";
    }


    @GetMapping("letGo")
    public ResultVO<String> letGo(){
        return ResultVO.success("通过配置放行我");
    }

    @GetMapping("letGo2")
    public ReplaceResultVO<String> letGo2(){
        return ReplaceResultVO.success(200,"通过配置放行我");
    }

    @ApiMapping(value = "/api/letGo",checkToken = false)
    public String apiLetGo(){
        return "利用注解@ApiMapping放行";
    }


    @GetMapping("/user/login")
    public String defaultLetGo(){
        return "默认放行</user/login>";
    }
}
