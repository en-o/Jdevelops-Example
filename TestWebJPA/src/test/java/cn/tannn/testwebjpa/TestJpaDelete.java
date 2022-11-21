package cn.tannn.testwebjpa;

import cn.jdevelops.jap.page.ResourceJpaPage;
import cn.jdevelops.jap.page.ResultJpaPageVO;
import cn.jdevelops.result.response.RoutinePageDTO;
import cn.tannn.testwebjpa.entity.User;
import cn.tannn.testwebjpa.service.UserService;
import cn.tannn.testwebjpa.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJpaDelete {

    @Autowired
    private UserService userService;

    @Test
    void testPage_ResourceJpaPage(){
        RoutinePageDTO page = new RoutinePageDTO("id", 1, 1, 10);
        User user = new User();
        user.setLoginPwd("123456");
        ResourceJpaPage<UserVO> byBean = userService.findByBean(user, page, UserVO.class);
        System.out.println(byBean.toString());
    }


    @Test
    void testPage_ResultJpaPageVO(){
        RoutinePageDTO page = new RoutinePageDTO("id", 1, 1, 10);
        User user = new User();
        user.setLoginPwd("123456");
        ResultJpaPageVO<UserVO> byBeanForVO = userService.findByBeanForVO(user, page, UserVO.class);
        System.out.println(byBeanForVO.toString());
    }
}
