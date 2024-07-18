package cn.tannn.demo.jdevelops.swagger.apisknife4j.controller;

import cn.tannn.demo.jdevelops.swagger.apisknife4j.controller.vo.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试接口单包扫描， jdevelops.swagger.basePackage=cn.tannn.springbootparentswagger.controller
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Tag(name="测试接口单包扫描",description = "测试接口单包扫描")
@RestController
public class TestDocController {

    @PostMapping("api1")
    @Operation(summary = "默认接口", description = "测试接口单包扫描")
    public TestVO testDoc(HttpServletRequest request){
        TestVO testVO = new TestVO();
        testVO.setId(1);
        testVO.setName(2);
        System.out.println(request.getHeader("token"));
        return testVO;
    }
}
