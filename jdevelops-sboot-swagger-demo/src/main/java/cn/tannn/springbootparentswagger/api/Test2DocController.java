package cn.tannn.springbootparentswagger.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试多包扫描 jdevelops.swagger.basePackage=cn.tannn.springbootparentswagger.controller,cn.tannn.springbootparentswagger.api
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Tag(name="测试多包扫描",description = "测试多包扫描")
@RestController
public class Test2DocController {

    @PostMapping("api2")
    @Operation(summary = "多包扫描的接口", description = "测试多包扫描")
    public String testDoc(){
        return "swagger会扫描我吗";
    }
}
