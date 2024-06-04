package cn.tannn.demo.jdevelops.apissign.controller;

import cn.tannn.demo.jdevelops.apissign.entity.UserEntity;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.sign.annotation.Signature;
import cn.tannn.jdevelops.sign.enums.SginEnum;
import org.springframework.web.bind.annotation.*;

/**
 * @author tn
 * @version 1
 * @date 2020/12/21 17:13
 */
@RequestMapping("/params/sha")
@RestController
public class SignParamsSHAController {


    /**
     * 验证SHA加密 get 拼接
     */
    @GetMapping(value = "/isSignSHA")
    @Signature(type = SginEnum.SHA)
    public ResultVO<String> isSignSHA(String name, String age){
        return ResultVO.success(name+age);
    }


    /**
     * 验证SHA加密bean 拼接
     */
    @GetMapping(value = "/isSignSHABean")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABean(UserEntity userEntity){
        return ResultVO.success(userEntity);
    }

    /**
     *  POST验证SHA加密Bean
     */
    @PostMapping(value = "/isSignSHABeanP")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABeanP(UserEntity userEntity){
        return ResultVO.success(userEntity);
    }

    /**
     * POST验证SHA加密BeanJson
     */
    @PostMapping(value = "/isSignSHABeanPJson")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABeanPJson(@RequestBody UserEntity userEntity){
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.success(userEntity);
    }
}
