package cn.tannn.apisign.controller;

import cn.jdevelops.apisign.annotation.Signature;
import cn.jdevelops.apisign.enums.SginEnum;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.apisign.entity.UserEntity;
import com.alibaba.fastjson.JSONObject;
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
        return ResultVO.successForData(name+age);
    }


    /**
     * 验证SHA加密bean 拼接
     */
    @GetMapping(value = "/isSignSHABean")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABean(UserEntity userEntity){
        return ResultVO.success(JSONObject.toJSONString(userEntity));
    }

    /**
     *  POST验证SHA加密Bean
     */
    @PostMapping(value = "/isSignSHABeanP")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABeanP(UserEntity userEntity){
        return ResultVO.successForData(userEntity);
    }

    /**
     * POST验证SHA加密BeanJson
     */
    @PostMapping(value = "/isSignSHABeanPJson")
    @Signature(type = SginEnum.SHA)
    public ResultVO<UserEntity> isSignSHABeanPJson(@RequestBody UserEntity userEntity){
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.successForData(userEntity);
    }
}
