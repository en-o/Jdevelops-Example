package cn.tannn.apisign.controller;

import cn.jdevelops.apisign.annotation.Signature;
import cn.jdevelops.apisign.enums.SginEnum;
import cn.jdevelops.idempotent.annotation.ApiIdempotent;
import cn.jdevelops.result.result.ResultVO;
import cn.tannn.apisign.entity.UserEntity;
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
    @ApiIdempotent
    public ResultVO<UserEntity> isSignMD5BeanPJsonH(@RequestBody UserEntity userEntity) {
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.successForData(userEntity);
    }

    /**
     * 验证MD5加密 - get bean 拼接 header
     *
     */
    @GetMapping(value = "/isSignMD5BeanH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<UserEntity> isSignMD5BeanH(UserEntity userEntity) {
        return ResultVO.successForData(userEntity);
    }

    /**
     * 验证MD5加密 - post  json list header
     *
     */
    @PostMapping(value = "/isSignMD5ListBeanPH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<List<UserEntity>> isSignMD5ListBeanPH(@RequestBody List<UserEntity> list) {
        return ResultVO.successForData(list);
    }


}
