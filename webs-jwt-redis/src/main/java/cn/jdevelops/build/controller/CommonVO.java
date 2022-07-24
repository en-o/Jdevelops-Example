package cn.jdevelops.build.controller;

import cn.jdevelops.entity.basics.vo.SerializableVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 公共的实体类
 *
 * @author lxw
 * @date 2021-01-21 14:20
 */
@Getter
@Setter
@ToString
public class CommonVO<V> extends SerializableVO<V> {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Integer id;

	/**
	 * 表示该字段为创建时间字段，
	 */
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	/**
	 * 表示该字段为创建人，
	 */
	@ApiModelProperty(value = "创建人")
	private String createUserName;


}
