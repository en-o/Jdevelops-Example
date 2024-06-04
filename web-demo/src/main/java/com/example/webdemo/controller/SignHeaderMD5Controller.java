package com.example.webdemo.controller;

import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.api.sign.annotation.Signature;
import cn.jdevelops.api.sign.enums.SginEnum;
import com.example.webdemo.controller.vo.ApiSignTestEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 用这里的接口文档测试：https://yrzyjs4ns6.apifox.cn/api-8961087
 * 验证签名
 *
 */
@Tag(name = "测试接口签名")
@PathRestController("/header/md5")
public class SignHeaderMD5Controller {


    /**
     * isSignMD5BeanPJsonH
     */
    @PostMapping(value = "/isSignMD5BeanPJsonH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<ApiSignTestEntity> isSignMD5BeanPJsonH(@RequestBody ApiSignTestEntity userEntity) {
        System.out.println("userEntity.toString() = " + userEntity.toString());
        return ResultVO.successForData(userEntity);
    }

    /**
     * 验证MD5加密 - get bean 拼接 header
     *
     */
    @GetMapping(value = "/isSignMD5BeanH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<ApiSignTestEntity> isSignMD5BeanH(ApiSignTestEntity userEntity) {
        return ResultVO.successForData(userEntity);
    }

    /**
     * 验证MD5加密 - post  json list header
     *
     */
    @PostMapping(value = "/isSignMD5ListBeanPH")
    @Signature(type = SginEnum.MD5HEADER)
    public ResultVO<List<ApiSignTestEntity>> isSignMD5ListBeanPH(@RequestBody List<ApiSignTestEntity> list) {
        return ResultVO.successForData(list);
    }


}
