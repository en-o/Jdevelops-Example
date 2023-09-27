package com.example.webdemo.controller;


import cn.jdevelops.api.annotation.mapping.PathRestController;
import com.example.webdemo.controller.dto.ValidationUserBean;
import com.example.webdemo.controller.vo.ValidationResultBean;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "测试入参检验和出参遮掩")
@PathRestController("/validation")
public class ValidationController {

    /**
     * 测试入参验证
     * @param userBean userBean
     * @return UserBean
     */
    @PostMapping("validate")
    public ValidationUserBean validate(@RequestBody @Valid ValidationUserBean userBean){
        return userBean;
    }


    /**
     * 测试返回值遮掩
     * @return UserBean
     */
    @GetMapping("cover")
    public ValidationResultBean cover(){
        return new ValidationResultBean();
    }
}
