package cn.tannn.springbootparentswagger.controller;

import cn.tannn.springbootparentswagger.controller.dto.TestDTO;
import cn.tannn.springbootparentswagger.controller.vo.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试接口参数显示情况
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Tag(name="测试接口参数显示情况",description = "测试接口参数显示情况")
@RestController
public class TestDocParamsController {

    @GetMapping("getBean")
    @Operation(summary = "getBean", description = "测试实体参数")
    public TestVO testDoc(TestDTO testDTO){
        TestVO testVO = new TestVO();
        testVO.setId(testDTO.getId());
        testVO.setName(testDTO.getName());
        return testVO;
    }

    @GetMapping("getParams")
    @Operation(summary = "getParams", description = "测试参数")
    @Parameters({
            @Parameter(name = "name",description = "名称"),
            @Parameter(name = "code",description = "代码")
    })
    public String getParams(String name, Integer code){
        return name+code;
    }

    @GetMapping("getParam")
    @Operation(summary = "getParam", description = "测试参数")
    public String getParam(@Parameter(name = "name",description = "名称") String name){
        return name;
    }

    @PostMapping("postBean")
    @Operation(summary = "postBean", description = "测试参数")
    public TestDTO postBean(TestDTO testDTO){
        return testDTO;
    }

    @PostMapping("postBeanJson")
    @Operation(summary = "postBeanJson", description = "测试参数")
    public TestDTO postBeanJson(@RequestBody TestDTO testDTO){
        System.out.println("postBeanJson: "+testDTO.toString());
        return testDTO;
    }
}
