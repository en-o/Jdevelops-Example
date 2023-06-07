package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.api.result.request.PageDTO;
import cn.jdevelops.api.result.request.SortPageDTO;
import cn.jdevelops.data.jap.page.JpaPageResult;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import cn.tannn.jdevelopssbootjpademo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 分页测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 14:18
 */
@SpringBootTest
public class PageTest {
    @Autowired
    private UserService userService;

    /**
     * 分页+排序查询 返回新的对象
     */
    @Test
    void findByBeanPSFVO(){
        SortPageDTO sortPage = new SortPageDTO(1, 10, 1,"id" );
        User user = new User();
        user.setLoginPwd("123456");
        JpaPageResult<UserVO> byBean = userService.findByBean(user, sortPage, UserVO.class);
        byBean.getRows().forEach(System.out::println);
    }

    /**
     * 分页+排序查询
     */
    @Test
    void findByBeanPS(){
        SortPageDTO sortPage = new SortPageDTO(1, 10, 1,"id" );
        User user = new User();
        user.setLoginPwd("123456");
        JpaPageResult<User> byBean = userService.findByBean(user, sortPage);
        byBean.getRows().forEach(System.out::println);
    }

    /**
     * 分页查询 返回新的对象
     */
    @Test
    void findByBeanPFVO(){
        PageDTO page = new PageDTO(1, 2);
        User user = new User();
        user.setLoginPwd("123456");
        JpaPageResult<UserVO> byBean = userService.findByBean(user, page, UserVO.class);
        byBean.getRows().forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    void findByBeanPF(){
        PageDTO page = new PageDTO(1, 2);
        User user = new User();
        user.setLoginPwd("123456");
        JpaPageResult<User> byBean = userService.findByBean(user, page);
        byBean.getRows().forEach(System.out::println);
    }
}
