package cn.tannn.demo.jdevelops.apissign.controller;

import cn.tannn.demo.jdevelops.apissign.entity.UserEntity;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.sign.annotation.Signature;
import cn.tannn.jdevelops.sign.enums.SginEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 验证签名
 * MD5 sign 跟在 header中
 * （Signature(type = SginEnum.MD5HEADER)）
 * @author tn
 * @version 1
 * @date 2020/12/21 14:39
 */
@RequestMapping("/header/md5")
@RestController
public class SignHeaderMD5Controller {


    /**
     *
     */
    @PostMapping(value = "/isSignMD5BeanPJsonH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<UserEntity> isSignMD5BeanPJsonH(@RequestBody UserEntity userEntity) {
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.success(userEntity);
    }

    /**
     * 验证MD5加密 - get bean 拼接 header
     *
     */
    @GetMapping(value = "/isSignMD5BeanH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<UserEntity> isSignMD5BeanH(UserEntity userEntity) {
        return ResultVO.success(userEntity);
    }

    /**
     * 验证MD5加密 - post  json list header
     *
     */
    @PostMapping(value = "/isSignMD5ListBeanPH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<List<UserEntity>> isSignMD5ListBeanPH(@RequestBody List<UserEntity> list) {
        return ResultVO.success(list);
    }


}
