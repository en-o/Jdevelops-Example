package cn.tannn.apisign.result;

import cn.jdevelops.enums.result.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 全局结果集
 *
 * @author tn
 * @version 1
 * @date 2020/6/8 17:28
 */
@Getter
@Setter
@ToString
public class ReplaceResultVO<T> implements Serializable {

    private static final long serialVersionUID = -7719394736046024902L;

    /**
     * 返回结果状态码
     */
    private Integer zhuangTaiMa;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 数据
     */
    private T sJu;

    /**
     * 自动转换success的返回值：true,false
     */
    public boolean isSuccess() {
        return this.zhuangTaiMa == ResultCodeEnum.SUCCESS.getCode();
    }



    public static <T> ReplaceResultVO<T> success(int code, String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static <T> ReplaceResultVO<T> fail(int code, String message, T data) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setMessage(message);
        resultVO.setSJu(data);
        return resultVO;
    }

    public static <T> ReplaceResultVO<T> fail(int code, String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setMessage(message);
        return resultVO;
    }


}
