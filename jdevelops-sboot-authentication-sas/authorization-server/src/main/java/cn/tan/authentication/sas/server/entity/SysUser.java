package cn.tan.authentication.sas.server.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 8:45
 */
@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "登录用户")
@Getter
@Setter
@ToString
public class SysUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="bigint COMMENT '主键，自动生成'")
    private Long id;

    /**
     * 登录名
     */
    @Comment("登录名")
    @Column(columnDefinition = "varchar(100) not null")
    private String username;

    /**
     * 密码
     */
    @Comment("密码")
    @Column(columnDefinition = "varchar(200) not null")
    private String password;

    /**
     * 昵称
     */
    @Comment("昵称")
    @Column(columnDefinition = "varchar(60)")
    private String nickname;


    /**
     * 个人简介
     */
    @Comment("个人简介")
    @Column(columnDefinition = "varchar(200)")
    private String description;

    /**
     * 状态:0[停用],1[正常],2[封禁]
     */
    @Comment("状态:0[停用],1[正常],2[封禁]")
    @Column(columnDefinition = "int default 1")
    private Integer status;


    /**
     * 用户角色[逗号隔开]
     */
    @Comment("用户角色[逗号隔开]")
    @Column(columnDefinition = "varchar(1000) default 'user'")
    private String roles;

}
