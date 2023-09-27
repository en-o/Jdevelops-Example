package com.example.webdemo.service.pojo;


/**
 * 用户表 sys_user
 * @author : tn
 * @date : 2021-9-10
 */
public class UserBO {




	/**
	 * 姓名
	 */
	private String name;



	/**
	 * 登录名称
	 */
	private String loginName;


	@Override
	public String toString() {
		return "UserBO{" +
				"name='" + name + '\'' +
				", loginName='" + loginName + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
