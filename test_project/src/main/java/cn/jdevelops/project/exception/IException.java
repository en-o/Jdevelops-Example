package cn.jdevelops.project.exception;

import cn.jdevelops.exception.exception.BusinessException;
import cn.jdevelops.project.enums.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author tn
 * @date 2021-01-22 14:15
 */
@AllArgsConstructor
@Getter
public class IException extends BusinessException {

    public IException(int code, String message) {
        super(code, message);
    }

    public IException(String message) {
        super(message);
    }

    public IException(IExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }
}
