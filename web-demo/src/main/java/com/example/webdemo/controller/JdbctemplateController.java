package com.example.webdemo.controller;

import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.api.result.util.ListTo;
import com.example.webdemo.controller.dto.UserFindDTO;
import com.example.webdemo.controller.vo.UserVO;
import com.example.webdemo.entity.User;
import com.example.webdemo.service.UserService;
import com.example.webdemo.service.pojo.UserBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@Tag(name = "测试Jdbctemplate")
@PathRestController("/jdbctemplate")
public class JdbctemplateController {

    private final UserService userService;

    public JdbctemplateController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("findById")
    public ResultVO<UserVO> findById(){
        User byId = userService.findById(1);
        UserVO userVO = byId.to(UserVO.class);
        return ResultVO.successForData(userVO);
    }

    @Operation(summary = "findByBean")
    @GetMapping("findByBean")
    public ResultVO<UserVO> findByBean(){
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        User byBean = userService.findByBean(userBO);
        UserVO userVO = byBean.to(UserVO.class);
        return ResultVO.successForData(userVO);
    }
}
