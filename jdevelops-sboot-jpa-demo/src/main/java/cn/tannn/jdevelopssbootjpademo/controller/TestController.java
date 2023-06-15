package cn.tannn.jdevelopssbootjpademo.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.api.result.util.ListTo;
import cn.tannn.jdevelopssbootjpademo.dto.UserFindDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import cn.tannn.jdevelopssbootjpademo.vo.UserVO;
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
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        List<User> complex = userService.findComplex(user);
        return ResultVO.successForData(ListTo.to(UserVO.class, complex));
    }

}
