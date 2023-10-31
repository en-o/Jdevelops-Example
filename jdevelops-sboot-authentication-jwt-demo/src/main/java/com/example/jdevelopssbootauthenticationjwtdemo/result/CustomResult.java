package com.example.jdevelopssbootauthenticationjwtdemo.result;

import cn.jdevelops.api.result.custom.ExceptionResult;
import cn.jdevelops.api.result.emums.ExceptionCode;
import org.springframework.stereotype.Component;

/**
 * 自定义返回包装类
 * @author tn
 * @date 2021-01-20 10:50
 */
@Component("exceptionResult")
public class CustomResult implements ExceptionResult<ReplaceResultVO> {


    @Override
    public ReplaceResultVO result(ExceptionCode resultCode) {
        return ReplaceResultVO.result(resultCode.getCode(),resultCode.getMessage(),null);
    }

    @Override
    public ReplaceResultVO result(int code, String message) {
        return ReplaceResultVO.result(code,message,null);
    }

    @Override
    public ReplaceResultVO result(int code, String message, Object data) {
        return ReplaceResultVO.result(code,message,data);
    }

    @Override
    public ReplaceResultVO result(ExceptionCode resultCode, Object data) {
        return ReplaceResultVO.result(resultCode.getCode(),resultCode.getMessage(),data);
    }

    @Override
    public ReplaceResultVO success() {
        return ReplaceResultVO.success(200,"");
    }

    @Override
    public ReplaceResultVO success(Object data) {
        return ReplaceResultVO.result(200,"",data);
    }

    @Override
    public ReplaceResultVO fail(String message) {
        return ReplaceResultVO.fail(500,message);
    }

    @Override
    public ReplaceResultVO fail() {
        return ReplaceResultVO.fail(500,"系统错误");
    }


}
