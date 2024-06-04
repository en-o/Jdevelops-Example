package cn.tannn.demo.jdevelops.apissign.controller;

import cn.tannn.demo.jdevelops.apissign.entity.UserEntity;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.sign.annotation.Signature;
import cn.tannn.jdevelops.sign.enums.SginEnum;
import org.springframework.web.bind.annotation.*;

/**
 * MD5 sign 跟在 参数中
 * 验证签名 （Signature(type = SginEnum.MD5)）
 * @author tn
 * @version 1
 * @date 2020/11/25 12:59
 */
@RequestMapping("/params/md5")
@RestController
public class SignParamsMD5Controller {


    /**
     * 接口不签名
     *
     */
    @GetMapping(value = "/isSign")
    @Signature(type = SginEnum.ANY)
    public ResultVO<String> isSign(String name, String age) {
        return ResultVO.success(name + age);
    }

    /**
     * 验证MD5加密 - get 拼接
     *
     */
    @GetMapping(value = "/isSignMD5")
    @Signature(type = SginEnum.MD5)
    public ResultVO<String> isSignMD5(String name, String age) {
        return ResultVO.success(name + age);
    }


    /**
     * 验证MD5加密 - get bean 拼接
     *
     */
    @GetMapping(value = "/isSignMD5Bean")
    @Signature(type = SginEnum.MD5)
    public ResultVO<UserEntity> isSignMD5Bean(UserEntity UserEntity) {
        return ResultVO.success(UserEntity);
    }

    /**
     * 验证MD5加密 - post bean
     *
     */
    @PostMapping(value = "/isSignMD5BeanP")
    @Signature(type = SginEnum.MD5)
    public ResultVO<UserEntity> isSignMD5BeanP(UserEntity userEntity) {
        return ResultVO.success(userEntity);
    }

    /**
     * 验证MD5加密 - post json
     */
    @PostMapping(value = "/isSignMD5BeanPJson")
    @Signature(type = SginEnum.MD5)
    public ResultVO<UserEntity> isSignMD5BeanPJson(@RequestBody(required = false) UserEntity userEntity) {
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.success(userEntity);
    }

}
