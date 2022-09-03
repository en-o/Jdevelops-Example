package cn.jdevelops.project.controller;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.entity.basics.util.UUIDUtils;
import cn.jdevelops.entity.basics.vo.SerializableVO;
import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.project.controller.pojo.UserFindDTO;
import cn.jdevelops.project.controller.pojo.UserVO;
import cn.jdevelops.project.entity.User;
import cn.jdevelops.project.service.UserService;
import cn.jdevelops.result.result.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-09-03 20:50
 */
@PathRestController("user")
public class UserCtr {

    private final UserService userService;

    public UserCtr(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("add")
    public ResultVO<User> add() {
        User user = new User();
        user.setUserNo(UUIDUtils.getInstance().generateShortUuid());
        user.setName("tann");
        user.setLoginName("tann");
        user.setLoginPwd("123");
        User user1 = userService.saveByBean(user);
        return ResultVO.successForData(user1);
    }


    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        User to = user.to(User.class);
        JPAUtilExpandCriteria<User> selectBean = CommUtils.getSelectBean(to);
        List<User> all = userService.getJpaBasicsDao().findAll(selectBean);
        return ResultVO.successForData(SerializableVO.to(all, UserVO.class));
    }
}
