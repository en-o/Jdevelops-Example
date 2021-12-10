package cn.tannn.swagger.controller;

import cn.tannn.swagger.controller.dto.TestDTO;
import cn.tannn.swagger.controller.vo.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tn
 * @version 1
 * @date 2021-12-10 23:06
 */
@Api(tags = {"测试文档"},value = "测试文档")
@RestController
public class TestDocController {

    @PostMapping("doc")
    @ApiOperation(value = "测试", notes = "测试文档")
    public TestVO testDoc(TestDTO testDTO){
        TestVO testVO = new TestVO();
        testVO.setId(testDTO.getId());
        testVO.setName(testDTO.getName());
        return testVO;
    }
}
