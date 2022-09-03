package cn.tannn.hjpa.vo;

import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

/**
 * 用户
 *
 * @author tn
 * @date 2021-06-08 15:26
 */
@Getter
@Setter
@ToString
public class UserPO extends CommonVO<UserPO> {
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

	public UserPO(String userNo, String name, String address) {
		this.userNo = userNo;
		this.name = name;
		this.address = address;
	}


	public String getUserNo() {
		return userNo;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}
