package cn.tannn.hjpa;

import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jap.core.util.JpaUtils;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import cn.tannn.hjpa.vo.UserVo2;
import cn.tannn.hjpa.vo.UserVo2_JpaSelectIgnoreField;
import cn.tannn.hjpa.vo.UserVo2_NoJpaSelectIgnoreField;
import lombok.Data;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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


    /**
     * 测试 @JpaSelectIgnoreField 不加
     */
    @Test
    public void  getSelectBean2JpaSelectIgnoreField_1(){
        UserVo2_NoJpaSelectIgnoreField user = new UserVo2_NoJpaSelectIgnoreField();
        user.setLoginName("admin");
        user.setAddress("重");
        user.setIgnoreMe("123");
        JPAUtilExpandCriteria<User> selectBean = JpaUtils.getSelectBean2(user);
        Assert.assertThrows( InvalidDataAccessApiUsageException.class, () -> userService.getJpaBasicsDao().findAll(selectBean));
    }


    /**
     * 测试 @JpaSelectIgnoreField
     */
    @Test
    public void  getSelectBean2JpaSelectIgnoreField_2(){
        UserVo2_JpaSelectIgnoreField user = new UserVo2_JpaSelectIgnoreField();
//        user.setName("");
        user.setIgnoreMe("123");
        user.setLoginName("admin");
        user.setAddress("重");
        JPAUtilExpandCriteria<User> selectBean = JpaUtils.getSelectBean2(user);
        userService.getJpaBasicsDao().findAll(selectBean).forEach(System.out::println);
    }


}
