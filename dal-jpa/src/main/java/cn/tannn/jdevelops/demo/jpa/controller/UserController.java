package cn.tannn.jdevelops.demo.jpa.controller;

import cn.tannn.jdevelops.demo.jpa.dto.UserFindDTO;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.demo.jpa.vo.UserVO;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.result.utils.ListTo;
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
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        List<User> complex = userService.findComplex(user);
        return ResultVO.success(ListTo.to(UserVO.class, complex));
    }

}
