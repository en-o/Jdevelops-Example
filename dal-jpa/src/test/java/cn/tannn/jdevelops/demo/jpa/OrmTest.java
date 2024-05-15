package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.UserDao;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.entityManager.EntityManagerBasicTest;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.service.J2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * 测试 {@link J2Service#getJpaBasicsDao()}  {@link J2Service#getEntityManager()}
 */
@SpringBootTest
class OrmTest {


    @Autowired
    private UserService userService;

    /**
     * 在 service 层 获取 repository 在controller层直接使用 dao层的方法
     */
    @Test
    void genServiceJpaBasicsDao() {
        UserDao jpaBasicsDao = userService.getJpaBasicsDao();
        System.out.println("用UserDao接收后调用： ");
        jpaBasicsDao.customSql().forEach(System.out::println);
        // 如果直接调用的，不能得到 UserDao 里自定义构建的方法，只能使用内置的方法
        // 想要使用在UserDao中自定义的方法就用UserDao接收一下在调用
        System.out.println("直接调用： " + userService.getJpaBasicsDao().findById(1L));
    }


    /**
     * 在 service 层 获取 EntityManager
     * <p> 删改增都要加事务
     * @see EntityManagerBasicTest
     */
    @Test
    void genServiceEntityManager() {
        // getEntityManager 方法说明中有简单的使用教程
        // 或者参考 J2Service#updateBean
        EntityManager entityManager = userService.getEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where id = :id", User.class);
        query.setParameter("id", 1L);
        System.out.println(query.getResultList());
    }


}
