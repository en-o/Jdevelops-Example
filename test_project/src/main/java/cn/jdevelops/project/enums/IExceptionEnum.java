package cn.jdevelops.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author tn
 * @date 2021-06-08 16:37
 */
@AllArgsConstructor
@Getter
public enum IExceptionEnum {
    /**
     * 参数不正确
     */
    PARAMETER_ERROR(50001,"参数不正确"),

    /**
     * 用户名或密码有误，请重新输入
     */
    USER_LOGIN_REDO(50005,"用户名或密码有误，请重新输入"),

    /**
     * 密码不一致，请重新输入
     */
    PWD_VERIFY(50006,"密码不一致，请重新输入"),

    /**
     * 敏感词汇
     */
    SENSITIVE_WORD(50006,"敏感词汇，请重新输入"),

    ;

    /**
     * code
     */
    private final Integer code;

    /**
     * 注释
     */
    private final String message;
}
