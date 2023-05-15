package cn.tannn.jdevelopssbootjpademo.dto;

import cn.jdevelops.api.result.bean.SerializableBean;
import cn.jdevelops.data.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.jdevelops.data.jap.enums.SQLOperator;
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
	@JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.AND)
	private String userNo;
	/**
	 * 姓名
	 */
	@JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.AND,fieldName = "name")
	private String name;
	/**
	 * 地址
	 */
	@JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.AND)
	private String address;

	/**
	 * 用户头像
	 */
	@JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.AND)
	private String userIcon;

	/**
	 * 手机号/联系电话
	 */
	@JpaSelectOperator(operator = SQLOperator.EQ ,connect = SQLConnect.AND)
	@JpaSelectIgnoreField
	private String phone;

}
