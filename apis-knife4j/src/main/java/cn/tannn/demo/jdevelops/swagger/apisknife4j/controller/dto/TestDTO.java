package cn.tannn.demo.jdevelops.swagger.apisknife4j.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 * @author tn
 * @version 1
 * @date 2021-12-10 22:56
 */
@Schema(description = "测试dto")
@Getter
@Setter
@ToString
public class TestDTO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id")
    private Integer id;

    /**
     * id
     */
    @Schema(description = "姓名")
    private Integer name;


}

