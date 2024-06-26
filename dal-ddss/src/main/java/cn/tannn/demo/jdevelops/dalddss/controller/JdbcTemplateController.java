package cn.tannn.demo.jdevelops.dalddss.controller;

import cn.tannn.demo.jdevelops.dalddss.controller.dto.TestVO;
import cn.tannn.jdevelops.annotations.ddss.DbName;
import cn.tannn.jdevelops.annotations.ddss.DbNamed;
import cn.tannn.jdevelops.annotations.ddss.DyDS;
import cn.tannn.jdevelops.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 测试模块
 *
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@Tag(name = "jdbcTemplate测试")
@RestController
@RequestMapping("/jdbcTemplate")
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Operation(summary = "hi")
    @GetMapping("hi")
    public ResultVO<String> hi() {
        return ResultVO.success("hi");
    }

    @Operation(summary = "测试默认库[dy_datasource]")
    @GetMapping("defDB")
    public ResultVO<List<Map<String, Object>>> defTemplate() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from dy_datasource");
        return ResultVO.success(maps);
    }


    /* ===================提交上来的代码只用不同的mysql 和 pgsql 测试，-======================================*/
    @Operation(summary = "根据传入的数据源名查询-单参")
    @GetMapping("testDynamicDataSourceByDatabase")
    @DyDS
    public ResultVO<List<Map<String, Object>>> testDynamicDataSourceByDatabase(@DbName String dbName) {
        return ResultVO.success(jdbcTemplate.queryForList("select * from sys_user"));
    }


    @Operation(summary = "根据传入的数据源名查询-bean参")
    @PostMapping("testDynamicDataSourceByDatabaseBean")
    @DyDS
    public ResultVO<List<Map<String, Object>>> testDynamicDataSourceByDatabaseBean(@RequestBody @DbNamed TestVO dbName) {
        return ResultVO.success(jdbcTemplate.queryForList("select * from sys_user"));
    }



    @Operation(summary = "根据传入的数据源名查询-注解写死")
    @GetMapping("testDyDsSelect_test1")
    @DyDS("test1")
    public ResultVO<List<Map<String, Object>>> testDyDsSelect() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from sys_user");
        return ResultVO.success(maps);
    }



    // 测试 sqlserver dynamic classloader

    @Operation(summary = "测试ClassLoader-sqljdbc4-4.0.jar")
    @GetMapping("sqlServerDynamicClassloader")
    @DyDS
    public ResultVO<List<Map<String, Object>>> sqlServerDynamicClassloader(@DbName String dbName) {
        return ResultVO.success(jdbcTemplate.queryForList("SELECT name FROM sys.databases"));
    }


}

