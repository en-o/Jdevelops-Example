package cn.jdevelops.build.account;

import cn.hutool.crypto.SecureUtil;
import cn.jdevelops.build.entity.CommonBean;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * user entity
 * @author tan
 * @date 2022-07-18 10:54:57
 */
@Entity
@Table(name = "auth_user")
@org.hibernate.annotations.Table(appliesTo = "auth_user", comment = "用户表")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class AuthUser extends CommonBean<AuthUser> {

    /**
     * 用户编码
     */
    @Column(columnDefinition = " varchar(50) not null comment '用户编码' ")
    private String code;

    /**
     * 用户名(登录名
     */
    @Column(columnDefinition = " varchar(50) not null comment '用户名(登录名' ")
    private String username;

    /**
     * 登录密码（MD5(password+userName+salt)
     */
    @Column(columnDefinition = " varchar(50) not null comment '登录密码（MD5(password+salt)' ")
    private String password;

    /**
     * 昵称
     */
    @Column(columnDefinition = " varchar(50) not null comment '昵称' ")
    private String nickName;

    /**
     * 密码加密的盐
     */
    @Column(columnDefinition = " varchar(50) comment '密码加密的盐' ")
    private String salt;

    /**
     * 用户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    @Column(columnDefinition = " smallint  default 1  comment '用户状态(1.正常 2.锁定 3.删除 4.非法)' ")
    private Integer status;

    /**
     * 注册的IP
     */
    @Column(columnDefinition = " varchar(50) comment '注册的IP' ")
    private String createIp;



    public String getMd5Password(){
       return SecureUtil.md5(password + salt);
    }

    /**
     * 是否禁用
     * @return boolean
     */
    public boolean disabledAccount(){
        return 1 != status;
    }


    /**
     * 是否锁定
     * @return boolean
     */
    public boolean excessiveAttempts(){
        return 2 == status;
    }

}
