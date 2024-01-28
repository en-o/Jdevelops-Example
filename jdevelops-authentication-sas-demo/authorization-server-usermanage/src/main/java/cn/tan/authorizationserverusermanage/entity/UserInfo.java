package cn.tan.authorizationserverusermanage.entity;

import cn.jdevelops.authentication.sas.server.user.entity.AuthenticationAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Arrays;

/**
 * 用户
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/25 13:25
 */
@Entity
@Table(name = "user_info")
@ToString
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    @Comment("主键，自动生成")
    private Long no;


    @Column(columnDefinition = "varchar(50)")
    @Comment("登录名")
    private String loginName;


    @Column(columnDefinition = "varchar(200)")
    @Comment("密码")
    private String password;


    @Column(columnDefinition = "varchar(100)")
    @Comment("昵称")
    private String nickname;


    @Comment("状态：1[正常],2[锁定[违规]],3[删除[禁用]]")
    private Integer status;


    @Column(columnDefinition = "varchar(15)")
    @Comment("手机号")
    private String phone;


    @Column(columnDefinition = "varchar(100)")
    @Comment("角色[逗号隔开]")
    private String roles;


    public AuthenticationAccount toAuthenticationAccount(){
        AuthenticationAccount account = new AuthenticationAccount();
        account.setNo(this.no.toString());
        account.setLoginName(this.loginName);
        account.setPassword(this.password);
        account.setNickname(this.nickname);
        account.setDescription("");
        account.setStatus(this.status);
        account.setRoles(Arrays.asList(StringUtils.split(this.roles, ",")));
        account.setPhoneNumber(this.phone);
        account.setEmail("");
        account.setProfile("");
        account.setAddress("");
        return account;
    }

}
