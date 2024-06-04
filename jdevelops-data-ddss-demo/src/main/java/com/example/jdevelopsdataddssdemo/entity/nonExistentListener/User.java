package com.example.jdevelopsdataddssdemo.entity.nonExistentListener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户表
 * @author : tn
 * @date : 2021-9-10
 */
@Entity
@Table(name = "sys_user",
		indexes = {
				@Index(name = "user_no_index", columnList = "userNo", unique = true),
		})
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "用户表")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int(11)")
	@Comment("主键，自动生成")
	private Integer id;

	/**
	 * 用户编号
	 */
	@Column(columnDefinition = " varchar(50)  not null")
	@Comment("用户编号")
	private String userNo;

	/**
	 * 姓名
	 */
	@Column(columnDefinition = " varchar(100)  not null ")
	@Comment("姓名")
	private String name;

	/**
	 * 地址
	 */
	@Column(columnDefinition = " varchar(100)")
	@Comment("地址")
	private String address;

	/**
	 * 登录名称
	 */
	@Column(columnDefinition = " varchar(100)  not null")
	@Comment("登录名称")
	private String loginName;

	/**
	 * 登录密码
	 */
	@Column(columnDefinition = " varchar(100)  not null")
	@Comment("登录密码")
	private String loginPwd;


	/**
	 * 手机号/联系电话
	 */
	@Column(columnDefinition = " varchar(15) ")
	@Comment("手机号/联系电话")
	private String phone;

	/**
	 * 用户头像
	 */
	@Column(columnDefinition = " varchar(60)")
	@Comment("用户头像")
	private String userIcon;




}
