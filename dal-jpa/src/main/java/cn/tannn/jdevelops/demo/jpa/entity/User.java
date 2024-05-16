package cn.tannn.jdevelops.demo.jpa.entity;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.JpaUpdate;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

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
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User extends CommonBean<User> {

	/**
	 * 用户编号
	 */
	@Column(columnDefinition = " varchar(50)  not null ")
	@Comment("用户编号")
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR)
	private String userNo;

	/**
	 * 姓名
	 */
	@Column(columnDefinition = " varchar(100)  not null ")
	@Comment("姓名")
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR)
	private String name;

	/**
	 * 地址
	 */
	@Column(columnDefinition = " varchar(100)  ")
	@Comment("地址")
	private String address;

	/**
	 * 登录名称
	 */
	@Column(columnDefinition = " varchar(100)  not null ")
	@Comment("登录名称")
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ, connect = SQLConnect.AND)
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
	@Column(columnDefinition = " varchar(15)  ")
	@Comment("手机号/联系电话")
	private String phone;

	/**
	 * 用户头像
	 */
	@Column(columnDefinition = " varchar(60)   ")
	@Comment("用户头像")
	@JpaUpdate(ignore = true)
	private String userIcon;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		User user = (User) o;
		return getId() != null && Objects.equals(getId(), user.getId());
	}

	@Override
	public String toString() {
		return "User{" +
				"userNo='" + userNo + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", loginName='" + loginName + '\'' +
				", loginPwd='" + loginPwd + '\'' +
				", phone='" + phone + '\'' +
				", userIcon='" + userIcon + '\'' +
				'}';
	}



	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
