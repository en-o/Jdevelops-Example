package cn.tannn.jdevelops.demo.jpa.controller.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectIgnoreField;
import cn.tannn.jdevelops.jpa.request.PagingSorteds;
import lombok.Data;


/**
 * @author tan
 */
@Data
public class PageUser {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String address;


    /**
     * 登录名称
     */
    private String loginName;


    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 手机号/联系电话
     */
    private String phone;

    /**
     * 头像
     */
    private String userIcon;


    @JpaSelectIgnoreField
    PagingSorteds page;

    public PagingSorteds getPage() {
        return page==null?new PagingSorteds():page;
    }
}
