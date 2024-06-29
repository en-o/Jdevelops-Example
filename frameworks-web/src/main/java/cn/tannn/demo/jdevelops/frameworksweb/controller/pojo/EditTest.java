package cn.tannn.demo.jdevelops.frameworksweb.controller.pojo;

import cn.tannn.demo.jdevelops.frameworksweb.entity.TestWeb;
import cn.tannn.jdevelops.annotations.jpa.JpaUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:37
 */
@Data
@Schema(description = "edit")
public class EditTest {

    @Schema(description = "id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @JpaUpdate(unique = true)
    private Long id;

    @Schema(description = "名字")
    String name;

    @Schema(description = "地址")
    String address;

}
