package cn.tannn.swagger.api;

import cn.tannn.swagger.controller.dto.TestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Api(tags = {"一个组聚合多个_controller_1"},value = "多controller聚合分组")
@RestController
public class Test2DocController {

    @PostMapping("aggregate")
    @ApiOperation(value = "一个组聚合多个_controller_api", notes = "多controller聚合分组")
    public String testDoc(TestDTO testDTO){
        return "swagger会扫描我吗";
    }
}
