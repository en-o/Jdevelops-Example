package cn.tannn.testwebjpa;

import cn.jdevelops.jap.core.util.SortUtil;
import cn.jdevelops.jap.page.ResourceJpaPage;
import cn.jdevelops.jap.page.ResultJpaPageVO;
import cn.jdevelops.result.response.RoutinePageDTO;
import cn.tannn.testwebjpa.dao.UserDao;
import cn.tannn.testwebjpa.entity.User;
import cn.tannn.testwebjpa.service.UserService;
import cn.tannn.testwebjpa.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class TestJpaPage {

    @Autowired
    private UserService userService;

    @Test
    void testPage_ResourceJpaPage(){
        RoutinePageDTO page = new RoutinePageDTO("id", 1, 1, 10);
        User user = new User();
        user.setLoginPwd("123456");
        ResourceJpaPage<List<UserVO>> byBean = userService.findByBean(user, page, UserVO.class);
        System.out.println(byBean.toString());
    }


    @Test
    void testPage_ResultJpaPageVO(){
        RoutinePageDTO page = new RoutinePageDTO("id", 1, 1, 10);
        User user = new User();
        user.setLoginPwd("123456");
        ResultJpaPageVO<ResourceJpaPage<List<UserVO>>> byBeanForVO = userService.findByBeanForVO(user, page, UserVO.class);
        System.out.println(byBeanForVO.toString());
    }
}
