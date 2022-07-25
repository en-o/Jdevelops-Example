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
@Api(tags = {"测试文档2"},value = "测试文档2")
@RestController
public class Test2DocController {

    @PostMapping("doc2")
    @ApiOperation(value = "测试2", notes = "测试文档2")
    public String testDoc(TestDTO testDTO){
        return "swagger会扫描我吗";
    }
}
