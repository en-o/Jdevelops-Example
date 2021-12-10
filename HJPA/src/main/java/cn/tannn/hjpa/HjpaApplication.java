package cn.tannn.hjpa;

import cn.jdevelops.entity.basics.vo.SerializableVO;
import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.result.result.ResultVO;
import cn.jdevelops.spring.scan.EnableAutoSchema;
import cn.tannn.hjpa.dto.UserFindDTO;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import cn.tannn.hjpa.vo.UserVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tnnn
 */
@SpringBootApplication
@EnableAutoSchema
@RestController
public class HjpaApplication {

    private final UserService userService;

    public HjpaApplication(UserService userService) {
        this.userService = userService;
    }


    public static void main(String[] args) {
        SpringApplication.run(HjpaApplication.class, args);
    }

    @PostMapping("dyFind")
    public ResultVO<List<UserVO>> dyFind(@RequestBody UserFindDTO user){
        User to = user.to(User.class);
        JPAUtilExpandCriteria<User> selectBean = CommUtils.getSelectBean(to);
        List<User> all = userService.getJpaBasicsDao().findAll(selectBean);
        return ResultVO.successForData(SerializableVO.to(all, UserVO.class));
    }
}
