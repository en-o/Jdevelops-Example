package com.example.jdevelopsaopexceptiondemo.result;

import java.io.Serializable;

/**
 * 全局结果集
 *
 * @author tn
 * @version 1
 * @date 2020/6/8 17:28
 */

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



    public static <T> ReplaceResultVO<T> success(int code, String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setXiaoXi(message);
        return resultVO;
    }

    public static <T> ReplaceResultVO<T> fail(int code, String message, T data) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setXiaoXi(message);
        resultVO.setsJu(data);
        return resultVO;
    }

    public static <T> ReplaceResultVO<T> fail(int code, String message) {
        ReplaceResultVO<T> resultVO = new ReplaceResultVO<>();
        resultVO.setZhuangTaiMa(code);
        resultVO.setXiaoXi(message);
        return resultVO;
    }

    @Override
    public String toString() {
        return "ReplaceResultVO{" +
                "zhuangTaiMa=" + zhuangTaiMa +
                ", xiaoXi='" + xiaoXi + '\'' +
                ", sJu=" + sJu +
                '}';
    }

    public Integer getZhuangTaiMa() {
        return zhuangTaiMa;
    }

    public void setZhuangTaiMa(Integer zhuangTaiMa) {
        this.zhuangTaiMa = zhuangTaiMa;
    }

    public String getXiaoXi() {
        return xiaoXi;
    }

    public void setXiaoXi(String xiaoXi) {
        this.xiaoXi = xiaoXi;
    }

    public T getsJu() {
        return sJu;
    }

    public void setsJu(T sJu) {
        this.sJu = sJu;
    }
}
