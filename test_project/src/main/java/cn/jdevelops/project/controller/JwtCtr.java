package cn.jdevelops.project.controller;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.entity.basics.vo.SerializableVO;
import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jwt.annotation.ApiMapping;
import cn.jdevelops.project.service.UserService;
import cn.jdevelops.result.result.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@PathRestController("jwt")
public class JwtCtr {



    @GetMapping("stop")
    public String stop(){
        return "拦截我";
    }


    @ApiMapping(value = "/zore",checkToken = false)
    public Integer zore(){
        return 1/0;
    }

    @GetMapping("letGo")
    public String letGo(){
        return "通过配置放行我";
    }

    @GetMapping("letGo2")
    public String letGo2(){
        return "通过配置放行我";
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
