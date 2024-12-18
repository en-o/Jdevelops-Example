package cn.tannn.demo.jdevelops.authenticationsjwt.reset;


import cn.tannn.jdevelops.result.exception.ExceptionCode;
import cn.tannn.jdevelops.result.exception.ExceptionResult;

/**
 * 自定义返回包装类
 * @author tn
 * @date 2021-01-20 10:50
 */
//@Component("exceptionResult")
public class CustomResult implements ExceptionResult<ReplaceResultVO> {


    @Override
    public ReplaceResultVO result(ExceptionCode resultCode) {
        return ReplaceResultVO.result(resultCode);
    }

    @Override
    public ReplaceResultVO result(int code, String message) {
        return ReplaceResultVO.result(code, message);
    }

    @Override
    public ReplaceResultVO result(int code, String message, Object data) {
        return ReplaceResultVO.result(code, message , data);
    }

    @Override
    public ReplaceResultVO result(ExceptionCode resultCode, Object data) {
        return ReplaceResultVO.result(resultCode, data);
    }

    @Override
    public ReplaceResultVO success() {
        return ReplaceResultVO.success();
    }

    @Override
    public ReplaceResultVO success(Object data) {
        return ReplaceResultVO.success(data);
    }

    @Override
    public ReplaceResultVO fail(String message) {
        return ReplaceResultVO.fail(message);
    }

    @Override
    public ReplaceResultVO fail() {
        return ReplaceResultVO.fail();
    }
}
