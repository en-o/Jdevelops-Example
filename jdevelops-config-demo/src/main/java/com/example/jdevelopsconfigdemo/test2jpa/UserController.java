package com.example.jdevelopsconfigdemo.test2jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:41
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public String add(){
        User user = new User();
        user.setName("tan");
        userService.saveByBean(user);
        return "OK";
    }

    @GetMapping("/")
    public List<User> find(){
        return userService.findAllBean();
    }

}
