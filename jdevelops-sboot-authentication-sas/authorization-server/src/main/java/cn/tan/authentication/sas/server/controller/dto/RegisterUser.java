package cn.tan.authentication.sas.server.controller.dto;

import cn.tan.authentication.sas.server.entity.SysUser;
import cn.tan.authentication.sas.server.util.UUIDUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * 用户注册
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 10:50
 */
@Getter
@Setter
@ToString
public class RegisterUser {

    /**
     * 登录名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个人简介
     */
    private String description;



    public SysUser ofSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(this.username);
        sysUser.setPassword(new BCryptPasswordEncoder().encode(this.password));
        sysUser.setNickname(this.nickname);
        sysUser.setDescription(this.description);
        sysUser.setId(UUIDUtils.getInstance().generateShortUuidLong());
        return sysUser;
    }
}

