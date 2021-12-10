package cn.tannn.hjpa.entity;

import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

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
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
public class User extends CommonBean<User> {

	/**
	 * 用户编号
	 */
	@Column(columnDefinition = " varchar(50)  not null   comment ' 用户编号' ")
	@JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR)
	private String userNo;

	/**
	 * 姓名
	 */
	@Column(columnDefinition = " varchar(100)  not null   comment ' 姓名' ")
	@JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR)
	private String name;

	/**
	 * 地址
	 */
	@Column(columnDefinition = " varchar(100)   comment ' 地址' ")
	private String address;

	/**
	 * 登录名称
	 */
	@Column(columnDefinition = " varchar(100)  not null comment '登录名称' ")
	@JpaSelectOperator(operator = SQLOperator.EQ, connect = SQLConnect.AND)
	private String loginName;

	/**
	 * 登录密码
	 */
	@Column(columnDefinition = " varchar(100)  not null comment '登录密码' ")
	private String loginPwd;


	/**
	 * 手机号/联系电话
	 */
	@Column(columnDefinition = " varchar(15)  comment ' 手机号/联系电话 ' ")
	private String phone;

	/**
	 * 用户头像
	 */
	@Column(columnDefinition = " varchar(60)   comment ' 用户头像' ")
	private String userIcon;

}
