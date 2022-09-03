package cn.jdevelops.project.controller.pojo;
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
public class CommonDTO<V> extends SerializableVO<V> {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Integer id;

}
