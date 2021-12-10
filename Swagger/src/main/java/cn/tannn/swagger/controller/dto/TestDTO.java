package cn.tannn.swagger.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 * @author tn
 * @version 1
 * @date 2021-12-10 22:56
 */
@ApiModel("测试dto")
@Getter
@Setter
@ToString
public class TestDTO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * id
     */
    @ApiModelProperty(value = "姓名")
    private Integer name;


}

