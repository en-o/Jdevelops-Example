package cn.tannn.springbootparentswagger.controller.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 * @author tn
 * @version 1
 * @date 2021-12-10 22:54
 */
@Schema(name = "测试VO")
@Getter
@Setter
@ToString
public class TestVO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id")
    private Integer id;


    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private Integer name;


}
