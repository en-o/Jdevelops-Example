package com.example.jdevelopsaopexceptiondemo.result;

import cn.jdevelops.api.result.custom.ExceptionResult;
import cn.jdevelops.api.result.emums.ResultCodeEnum;
import org.springframework.stereotype.Component;

/**
 * 自定义返回包装类
 * @author tn
 * @date 2021-01-20 10:50
 */
//@Component("exceptionResult")
public class CustomResult implements ExceptionResult<ReplaceResultVO> {
    @Override
    public ReplaceResultVO result(ResultCodeEnum resultCodeEnum) {
        return ReplaceResultVO.fail(resultCodeEnum.getCode(),resultCodeEnum.getMessage());
    }

    @Override
    public ReplaceResultVO result(int code, String message) {
        return ReplaceResultVO.success(code,message);
    }

    @Override
    public ReplaceResultVO result(int code, String message, Object data) {
        return ReplaceResultVO.success(code,message);
    }

    @Override
    public ReplaceResultVO success() {
        return ReplaceResultVO.success(200,"成功");
    }

    @Override
    public ReplaceResultVO error() {
        return ReplaceResultVO.fail(500,"服务器错误");
    }

}
