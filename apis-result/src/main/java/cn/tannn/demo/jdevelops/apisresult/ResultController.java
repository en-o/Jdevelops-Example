package cn.tannn.demo.jdevelops.apisresult;

import cn.tannn.demo.jdevelops.apisresult.reset.ReplaceResultVO;
import cn.tannn.jdevelops.result.response.PageResult;
import cn.tannn.jdevelops.result.response.ResultPageVO;
import cn.tannn.jdevelops.result.response.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 注释掉：CustomResult 在测试
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/9 上午11:13
 */
@RestController
@RequestMapping("result")
public class ResultController {

    @GetMapping("/rvo")
    public ResultVO<String> rvo(){
        return ResultVO.successMessage("ResultVO");
    }

    @GetMapping("/rpvo")
    public ResultPageVO<String,PageResult<String>> rpvo(){
        PageResult<String> pageData = new PageResult<>(1, 2, 3, 4L, Arrays.asList("tan", "ning"));
        return ResultPageVO.success(pageData);
    }

    /**
     * 测试 CustomResult+jdevelops.api.result.enabled=true
     */
    @GetMapping("/rvo2")
    public ReplaceResultVO<String> rvo2(){
        return ReplaceResultVO.success("ResultVO");
    }
}
