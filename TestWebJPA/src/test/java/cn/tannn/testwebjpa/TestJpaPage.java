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

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TestJpaPage {

    @Autowired
    private UserService userService;

    @Test
    void testDeleteByUnique(){
        userService.deleteByUnique("user2","loginName");
        userService.deleteByUnique("user3",User::getLoginName);
    }


    @Test
    void testDeleteByUniques(){

        userService.deleteByUnique(Arrays.asList("user5","user4"),"loginName");
        userService.deleteByUnique(Arrays.asList("user6","user7"),User::getLoginName);
    }
}
