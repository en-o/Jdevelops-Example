package com.example.jdevelopsutilvalidationdemo.bean;

import cn.jdevelops.uitl.validation.account.Account;
import cn.jdevelops.uitl.validation.cname.Cname;
import cn.jdevelops.uitl.validation.datetime.DateTime;
import cn.jdevelops.uitl.validation.idcard.IdCard;
import cn.jdevelops.uitl.validation.mobile.Mobile;
import cn.jdevelops.uitl.validation.password.Password;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


/**
 * @author tnnn
 * @version V1.0
 * @date 2022-12-05 14:47
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

    /**
     * 手机号
     */
    @Mobile
    String iphone;

    /**
     * 身份证
     */
    @IdCard
    String idCard;

    /**
     * 中文姓名
     */
    @Cname
    String cname;


    /**
     * 时间yyyy-MM-dd HH:mm:ss
     */
    @DateTime
    String dateTime;

    /**
     * 密码
     */
    @Password
    String password;


    /**
     * 账户名
     */
    @Account
    String account;



}
