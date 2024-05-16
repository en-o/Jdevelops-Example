package cn.tannn.jdevelops.demo.jpa.dto;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectIgnoreField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
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
public class UserFindJpaSelectWrapperOperatorDTO extends SerializableBean<UserFindJpaSelectWrapperOperatorDTO> {
	/**
	 * LIKE 用户编号
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE)
	private String userNo;
	/**
	 * EQ 姓名
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ)
	private String name;

	/**
	 * LIKE 手机号/联系电话
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE)
	private String phone;


	/**
	 * 返回查询
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.BETWEEN)
	private String loginPwd;


	/**
	 * 返回查询
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ, function = SpecBuilderDateFun.DATE_FORMAT)
	private String createTime;

	/**
	 * 测试忽略
	 */
	@JpaSelectIgnoreField
	private Long id;


	/**
	 * 测试默认查询, 为空会不查询 【null,""】
	 */
	private String address;


	/**
	 * 测试重命名
	 */
	@JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ,fieldName = "updateUserName")
	private String reName;

}
