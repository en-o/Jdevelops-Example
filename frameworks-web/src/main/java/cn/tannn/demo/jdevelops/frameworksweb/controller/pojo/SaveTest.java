package cn.tannn.demo.jdevelops.frameworksweb.controller.pojo;
import java.time.LocalDateTime;

import cn.tannn.demo.jdevelops.frameworksweb.entity.TestWeb;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:37
 */
@Data
@Schema(description = "save")
public class SaveTest {

    @Schema(description = "名字",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    String name;

    @Schema(description = "地址",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    String address;


    public TestWeb toTestWeb(){
        TestWeb testWeb = new TestWeb();
        testWeb.setName(name);
        testWeb.setAddress(address);
        return testWeb;
    }
}
