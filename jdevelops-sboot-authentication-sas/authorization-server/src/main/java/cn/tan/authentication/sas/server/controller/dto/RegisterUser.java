package cn.tan.authentication.sas.server.controller.dto;

import cn.tan.authentication.sas.server.entity.SysUser;
import cn.tan.authentication.sas.server.util.UUIDUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    /**
     * 用户角色
     */
    private List<String> role;



    public SysUser ofSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(this.username);
        // 记得用PasswordEncoder passwordEncoder这个加密账户噢
        sysUser.setPassword(this.password);
        sysUser.setNickname(this.nickname);
        sysUser.setDescription(this.description);
        sysUser.setId(UUIDUtils.getInstance().generateShortUuidLong());
        sysUser.setRoles(StringUtils.join(this.role,","));
        return sysUser;
    }
}

