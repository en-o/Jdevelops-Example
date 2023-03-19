package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.data.jap.page.JpaPageResult;
import cn.jdevelops.result.request.SortPageDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import cn.tannn.jdevelopssbootjpademo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJpaDelete {

    @Autowired
    private UserService userService;

    @Test
    void testPage_ResultJpaPageVO(){
        SortPageDTO page = new SortPageDTO("id", 1, 1, 10);
        User user = new User();
        user.setLoginPwd("123456");
        JpaPageResult<UserVO> byBean = userService.findByBean(user, page, UserVO.class);
        System.out.println(byBean.toString());
    }

}
