package cn.tannn.demo.jdevelops.frameworksweb.controller;

import cn.tannn.demo.jdevelops.frameworksweb.controller.pojo.EditTest;
import cn.tannn.demo.jdevelops.frameworksweb.controller.pojo.PageTest;
import cn.tannn.demo.jdevelops.frameworksweb.controller.pojo.SaveTest;
import cn.tannn.demo.jdevelops.frameworksweb.entity.TestWeb;
import cn.tannn.demo.jdevelops.frameworksweb.service.TestService;
import cn.tannn.jdevelops.annotations.web.mapping.PathRestController;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.result.JpaPageResult;
import cn.tannn.jdevelops.result.response.ResultPageVO;
import cn.tannn.jdevelops.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:25
 */
@PathRestController("test")
@Tag(name = "test", description = "test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取详情", description = "save")
    public ResultVO<TestWeb> save(@PathVariable("id") Long id) {
        TestWeb bean = testService.findOnly("id", id).orElse(new TestWeb());
        return ResultVO.success(bean);
    }

    @PostMapping("save")
    @Operation(summary = "save", description = "save")
    public ResultVO<String> save(@RequestBody @Valid SaveTest save) {
        testService.saveOne(save.toTestWeb());
        return ResultVO.success();
    }

    @DeleteMapping("/delete/id")
    @Operation(summary = "delete", description = "save")
    public ResultVO<String> delete(Long id) {
        testService.deleteEq("id", id);
        return ResultVO.success();
    }

    @PutMapping("/edit")
    @Operation(summary = "edit", description = "edit")
    public ResultVO<String> update(@RequestBody @Valid EditTest edit) {
        testService.update(edit, SQLOperator.EQ);
        return ResultVO.success();
    }

    @GetMapping("/list")
    @Operation(summary = "list", description = "list")
    public ResultVO<List<TestWeb>> list() {
        List<TestWeb> finds = testService.finds();
        return ResultVO.success(finds);
    }


    @PostMapping("/page")
    @Operation(summary = "page", description = "page")
    public ResultPageVO<TestWeb, JpaPageResult<TestWeb>> page(@RequestBody @Valid PageTest page) {
        Page<TestWeb> webPage = testService.findPage(page, page.getPage());
        JpaPageResult<TestWeb> req = JpaPageResult.toPage(webPage);
        return ResultPageVO.success(req);
    }
}
