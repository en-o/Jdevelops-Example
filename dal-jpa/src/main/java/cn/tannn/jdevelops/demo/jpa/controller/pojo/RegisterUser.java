package cn.tannn.jdevelops.demo.jpa.controller.pojo;

import cn.hutool.core.date.DateTime;
import cn.tannn.jdevelops.annotations.jpa.JpaUpdate;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author tan
 */
@Data
public class RegisterUser {

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


}
