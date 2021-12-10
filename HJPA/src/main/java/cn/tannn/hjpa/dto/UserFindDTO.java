package cn.tannn.hjpa.dto;

import cn.jdevelops.entity.basics.vo.SerializableVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询用户
 *
 * @author tn
 * @date 2021-06-08 16:08
 */
@ApiModel(value = "查询用户", description = "用户管理")
@ToString
@Getter
@Setter
public class UserFindDTO extends SerializableVO<UserFindDTO> {
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = " 用户编号 ")
	private String userNo;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = " 姓名 ")
	private String name;
	/**
	 * 地址
	 */
	@ApiModelProperty(value = " 地址 ")
	private String address;

	/**
	 * 用户头像
	 */
	@ApiModelProperty(value = " 用户头像 ")
	private String userIcon;

	/**
	 * 手机号/联系电话
	 */
	@ApiModelProperty(value = " 手机号/联系电话 ")
	private String phone;

}
