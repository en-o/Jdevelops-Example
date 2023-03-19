package cn.tannn.jdevelopssbootjpademo.dto;

import cn.jdevelops.result.bean.SerializableBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询用户
 *
 * @author tn
 * @date 2021-06-08 16:08
 */
@ToString
@Getter
@Setter
public class UserFindDTO extends SerializableBean<UserFindDTO> {
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
	 * 用户头像
	 */
	private String userIcon;

	/**
	 * 手机号/联系电话
	 */
	private String phone;

}
