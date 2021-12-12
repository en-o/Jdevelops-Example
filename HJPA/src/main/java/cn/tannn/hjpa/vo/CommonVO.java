package cn.tannn.hjpa.vo;

import cn.jdevelops.entity.basics.vo.SerializableVO;
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
	private Integer id;

	/**
	 * 表示该字段为创建时间字段，
	 */
	private String createTime;

	/**
	 * 表示该字段为创建人，
	 */
	private String createUserName;


}
