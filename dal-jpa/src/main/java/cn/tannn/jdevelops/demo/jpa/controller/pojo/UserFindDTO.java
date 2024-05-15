package cn.tannn.jdevelops.demo.jpa.controller.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectIgnoreField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import cn.tannn.jdevelops.jpa.request.Sorteds;
import cn.tannn.jdevelops.result.bean.SerializableBean;
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
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR)
	private String userNo;
	/**
	 * 姓名
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR,fieldName = "name")
	private String name;
	/**
	 * 地址
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR)
	private String address;

	/**
	 * 用户头像
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR)
	private String userIcon;

	/**
	 * 手机号/联系电话
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ ,connect = SQLConnect.AND)
	private String phone;

	@JpaSelectIgnoreField
	private Integer id;

	/**
	 * 手机号/联系电话
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ ,connect = SQLConnect.AND, function = SpecBuilderDateFun.DATE_FORMAT)
	private String createTime;

	@JpaSelectIgnoreField
	Sorteds sort;

	public Sorteds getSort() {
		return sort==null?new Sorteds():sort;
	}
}
