package cn.tannn.jdevelopssbootjpademo.dto;

import cn.jdevelops.api.result.bean.SerializableBean;
import cn.jdevelops.data.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.enums.SQLOperatorWrapper;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
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
