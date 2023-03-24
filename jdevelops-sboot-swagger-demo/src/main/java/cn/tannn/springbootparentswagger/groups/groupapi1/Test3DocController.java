package cn.tannn.springbootparentswagger.groups.groupapi1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *     @Bean public GroupedOpenApi userApi(){}
 * </pre>
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Tag(name="测试接口分组_组一",description = "测试接口分组")
@RestController
public class Test3DocController {

    @PostMapping("apigroup_1")
    @Operation(summary = "接口组中的接口", description = "测试接口分组")
    public String testDoc(){
        return "swagger会扫描我吗";
    }
}
