package cn.tannn.jdevelopssbootjpademo.controller;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.data.jap.core.JPAUtilExpandCriteria;
import cn.jdevelops.result.bean.SerializableBean;
import cn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelopssbootjpademo.dto.UserFindDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import cn.tannn.jdevelopssbootjpademo.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@PathRestController()
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        List<User> complex = userService.findComplex(user);
        return ResultVO.successForData(SerializableBean.to(complex, UserVO.class));
    }
}
