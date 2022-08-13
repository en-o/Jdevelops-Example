package org.example.result;

import cn.jdevelops.result.custom.ExceptionResult;
import org.springframework.stereotype.Component;

/**
 * 自定义返回包装类
 * @author tn
 * @date 2021-01-20 10:50
 */
@Component("exceptionResult")
public class CustomResult implements ExceptionResult<ReplaceResultVO> {
    @Override
    public ReplaceResultVO success(int code, String message, Object object) {
        return ReplaceResultVO.success(code,message);
    }

    @Override
    public ReplaceResultVO error(int code, String message, Object object) {
        return ReplaceResultVO.fail(code, message, object);
    }

    @Override
    public ReplaceResultVO error(int code, String message) {
        return ReplaceResultVO.fail(code, message);
    }
}
