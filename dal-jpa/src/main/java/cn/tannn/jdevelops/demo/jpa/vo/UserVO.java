package cn.tannn.jdevelops.demo.jpa.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户
 *
 * @author tn
 * @date 2021-06-08 15:26
 */
@Getter
@Setter
@ToString
public class UserVO extends CommonVO<UserVO> {

	/**
	 * 用户编号
	 */
	private String userNo;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 登录名称
	 */
	private String loginName;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 用户类型
	 */
	private Integer userType;

	/**
	 * 用户头像
	 */
	private String userIcon;

	/**
	 * 手机号/联系电话
	 */
	private String phone;
}
