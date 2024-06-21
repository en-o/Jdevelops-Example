package cn.tannn.demo.jdevelops.daljdbctemplate.entity;


/**
 * 用户表 sys_user
 * @author : tn
 * @date : 2021-9-10
 */
public class User {

	private Integer id;

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
	 * 用户头像
	 */
	private String userIcon;



	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userNo='" + userNo + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", loginName='" + loginName + '\'' +
				", loginPwd='" + loginPwd + '\'' +
				", phone='" + phone + '\'' +
				", userIcon='" + userIcon + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
}
