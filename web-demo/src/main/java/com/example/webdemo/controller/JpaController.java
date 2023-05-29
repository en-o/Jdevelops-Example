package com.example.webdemo.controller;

import cn.jdevelops.api.annotation.mapping.PathRestController;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.api.result.util.ListTo;
import com.example.webdemo.controller.dto.UserFindDTO;
import com.example.webdemo.controller.vo.UserVO;
import com.example.webdemo.entity.User;
import com.example.webdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@Tag(name = "测试JPA")
@PathRestController("/jpa")
public class JpaController {

    private final UserService userService;

    public JpaController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "动态查询")
    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        List<User> complex = userService.findComplex(user);
        return ResultVO.successForData(ListTo.to(UserVO.class, complex));
    }
}
