package cn.tannn.swagger.groupapi2;

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
@Api(tags = {"第二接口组"},value = "分组测试")
@RestController
public class Test4DocController {

    @PostMapping("apigroup_2")
    @ApiOperation(value = "接口组中的接口", notes = "分组测试")
    public String testDoc(TestDTO testDTO){
        return "swagger会扫描我吗";
    }
}
