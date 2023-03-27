package cn.tannn.hjpa;

import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jap.core.util.criteria.Restrictions;
import cn.jdevelops.jpa.server.dao.JpaBasicsDao;
import cn.tannn.hjpa.dao.UserDao;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * CommUtils测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-06-29 15:37
 */
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
public class RestrictionsTest {

    @Autowired
    private UserService userService;

    @Test
    public void  restrictionsNullTest(){
        JPAUtilExpandCriteria<User> selectBean = new JPAUtilExpandCriteria<>();
        selectBean.add(
                Restrictions.and(
                        Restrictions.or(Restrictions.eq(User::getName,"用户1", true)),
//                        Restrictions.or(Restrictions.like(User::getLoginName, "", true))
//                        Restrictions.or(Restrictions.like(User::getLoginName, null, true))
                        Restrictions.or(Restrictions.like(User::getLoginName, "u", true))
                )
        );
        userService.getJpaBasicsDao().findAll(selectBean).forEach(System.out::println);
    }





}
