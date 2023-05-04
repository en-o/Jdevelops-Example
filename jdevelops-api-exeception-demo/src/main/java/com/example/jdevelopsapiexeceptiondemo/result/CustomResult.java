package com.example.jdevelopsapiexeceptiondemo.result;

import cn.jdevelops.api.result.custom.ExceptionResult;
import cn.jdevelops.api.result.emums.ResultCodeEnum;

/**
 * 自定义返回包装类
 * @author tn
 * @date 2021-01-20 10:50
 */
//@Component("exceptionResult")
public class CustomResult implements ExceptionResult<ReplaceResultVO> {


    @Override
    public ReplaceResultVO result(ResultCodeEnum resultCodeEnum) {
        return ReplaceResultVO.result(resultCodeEnum);
    }

    @Override
    public ReplaceResultVO result(int code, String message) {
        return ReplaceResultVO.result(code,message);
    }

    @Override
    public ReplaceResultVO success() {
        return ReplaceResultVO.success();
    }

    @Override
    public ReplaceResultVO error() {
        return ReplaceResultVO.fail();
    }
}
