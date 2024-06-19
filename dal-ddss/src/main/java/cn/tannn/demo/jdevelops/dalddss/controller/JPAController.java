package cn.tannn.demo.jdevelops.dalddss.controller;

import cn.tannn.demo.jdevelops.dalddss.controller.dto.TestVO;
import cn.tannn.demo.jdevelops.dalddss.dao.UserDao;
import cn.tannn.demo.jdevelops.dalddss.entity.nonExistentListener.User;
import cn.tannn.jdevelops.annotations.ddss.DbName;
import cn.tannn.jdevelops.annotations.ddss.DbNamed;
import cn.tannn.jdevelops.annotations.ddss.DyDS;
import cn.tannn.jdevelops.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试模块
 *
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@Tag(name = "jpa测试")
@RestController
@RequestMapping("/jpa")
public class JPAController {

    @Autowired
    private UserDao userDao;




    /* ===================提交上来的代码只用不同的mysql 和 pgsql 测试，-======================================*/
    @Operation(summary = "根据传入的数据源名查询-单参")
    @GetMapping("testDynamicDataSourceByDatabase")
    @DyDS
    public ResultVO<List<User>> testDynamicDataSourceByDatabase(@DbName String dbName) {
        return ResultVO.success(userDao.findAll());
    }


    @Operation(summary = "根据传入的数据源名查询-bean参")
    @PostMapping("testDynamicDataSourceByDatabaseBean")
    @DyDS
    public ResultVO<List<User>> testDynamicDataSourceByDatabaseBean(@RequestBody @DbNamed TestVO dbName) {
        return ResultVO.success(userDao.findAll());
    }


    @Operation(summary = "根据传入的数据源名查询-注解写死")
    @GetMapping("testDyDsSelect_test1")
    @DyDS("test1")
    public ResultVO<List<User>> testDyDsSelect() {
        return ResultVO.success(userDao.findAll());
    }


}

