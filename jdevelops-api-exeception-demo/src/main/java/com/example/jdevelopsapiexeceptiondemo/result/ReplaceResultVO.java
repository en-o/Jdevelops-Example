package com.example.jdevelopsapiexeceptiondemo.result;


import cn.jdevelops.api.result.emums.ExceptionCode;
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
    private String xiaoXi;

    /**
     * 数据
     */
    private T sJu;



    public static <T> ReplaceResultVO<T> success() {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(200);
        resultVO.setXiaoXi("success");
        return resultVO;
    }



    public static <T> ReplaceResultVO<T> fail() {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(500);
        resultVO.setXiaoXi("fail");
        return resultVO;
    }

    public static <T> ReplaceResultVO<T> fail(String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(500);
        resultVO.setXiaoXi(message);
        return resultVO;
    }

    public static <T> ReplaceResultVO success( T data) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(200);
        resultVO.setSJu(data);
        return resultVO;
    }
    public static <T> ReplaceResultVO result(int code, String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setXiaoXi(message);
        return resultVO;
    }
    public static <T> ReplaceResultVO result(int code, String message,T data) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setXiaoXi(message);
        resultVO.setSJu(data);
        return resultVO;
    }

    public static <T> ReplaceResultVO result(ExceptionCode result,T data) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(result.getCode());
        resultVO.setXiaoXi(result.getMessage());
        resultVO.setSJu(data);
        return resultVO;
    }


    public static <T> ReplaceResultVO result(ExceptionCode result) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(result.getCode());
        resultVO.setXiaoXi(result.getMessage());
        return resultVO;
    }
}
