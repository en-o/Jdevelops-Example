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
@Api(tags = {"一个组聚合多个_controller_2"},value = "多controller聚合分组")
@RestController
public class TestDocController {

    @PostMapping("aggregate_1")
    @ApiOperation(value = "一个组聚合多个_controller_api", notes = "多controller聚合分组")
    public TestVO testDoc(TestDTO testDTO){
        TestVO testVO = new TestVO();
        testVO.setId(testDTO.getId());
        testVO.setName(testDTO.getName());
        return testVO;
    }
}
