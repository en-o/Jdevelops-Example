package cn.tannn.hjpa;

import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import cn.tannn.hjpa.vo.UserVo2;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

/**
 * CommUtils测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-06-29 15:37
 */
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
public class CommUtilsTest {

    @Autowired
    private UserService userService;

    @Test
    public void  getSelectBean(){
        User user = new User();
        user.setName("用户");
        user.setLoginName("user");
        JPAUtilExpandCriteria<User> selectBean = CommUtils.getSelectBean(user);
        userService.getJpaBasicsDao().findAll(selectBean).forEach(System.out::println);
    }


    @Test
    public void  getSelectBean2(){
        UserVo2 user = new UserVo2();
//        user.setName("");
        user.setLoginName("admin");
        user.setAddress("重");
        JPAUtilExpandCriteria<User> selectBean = CommUtils.getSelectBean2(user);
        userService.getJpaBasicsDao().findAll(selectBean).forEach(System.out::println);
    }


}
