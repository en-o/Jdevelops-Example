package cn.tannn.hjpa;

import cn.jdevelops.jap.core.util.CommUtils;
import cn.jdevelops.jap.core.util.JPAUtilExpandCriteria;
import cn.jdevelops.jap.core.util.criteria.Restrictions;
import cn.tannn.hjpa.entity.User;
import cn.tannn.hjpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(rollbackFor = Exception.class)
class HjpaApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        JPAUtilExpandCriteria<User> jpaSelect = new JPAUtilExpandCriteria<>();
        jpaSelect.or(Restrictions.like("userNo", "admin", true));
        jpaSelect.or(Restrictions.like("phone", "123", true));
        jpaSelect.or(Restrictions.like("name", "用户", true));
        jpaSelect.or(Restrictions.like("loginName", "user", true));
        jpaSelect.add(Restrictions.eq("address", "重庆", true));
        userService.getJpaBasicsDao().findAll(jpaSelect);
//        JPAUtilExpandCriteria<User> where1 = new JPAUtilExpandCriteria<>();
//        where1.add(Restrictions.eq("address", "重庆", true));
//        JPAUtilExpandCriteria<User> where2= new JPAUtilExpandCriteria<>();
//        where2.or(Restrictions.like("name", "用户", true));
//        where2.or(Restrictions.eq("loginPwd", "1231", true));
//        jpaSelect.and(where1);
//        userService.getJpaBasicsDao().findAll(jpaSelect);
    }

}
