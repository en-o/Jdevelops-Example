package cn.tannn.jdevelops.demo.jpa.entityManager;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


/**
 * 测试 j2service entity manageer createQuery 简单示例
 *
 * <p> https://developer.aliyun.com/article/885236
 *
 * @author <a href="https://tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/5/15 下午8:24
 */
@SpringBootTest
public class EntityManagerCreateQueryTest {

    @Autowired
    private UserService userService;

    /**
     * hibernate  语法查询
     */
    @Test
    void queryHPQL(){
        EntityManager entityManager = userService.getEntityManager();
        //
        System.out.println("================  查询所有 =========================================");
        entityManager.createQuery("select u from User u", User.class)
                .getResultList()
                .forEach(System.out::println);

        System.out.println("================  参数查询 - 使用标识符 =========================================");
        entityManager.createQuery("select u from User u where id = :id ", User.class)
                .setParameter("id",4L)
                .getResultList()
                .forEach(System.out::println);

        System.out.println("================  参数查询 - 使用索引下标 =========================================");
        entityManager.createQuery("select u from User u where id = ?1 ", User.class)
                .setParameter(1,4L)
                .getResultList()
                .forEach(System.out::println);

    }

    /**
     * 原生sql
     */
    @Test
    void querySQL(){
        EntityManager entityManager = userService.getEntityManager();
        //
        System.out.println("================  查询所有 =========================================");
        entityManager.createNativeQuery("select * from sys_user u", User.class).getResultList()
                .forEach(System.out::println);

        System.out.println("================  参数查询 - 使用标识符 =========================================");
        entityManager.createNativeQuery("select * from sys_user u where id = :id ", User.class)
                .setParameter("id",4L)
                .getResultList()
                .forEach(System.out::println);

        System.out.println("================  参数查询 - 使用索引下标 =========================================");
        entityManager.createNativeQuery("select * from sys_user u where id = ?1 ", User.class)
                .setParameter(1,4L)
                .getResultList()
                .forEach(System.out::println);

    }

    /**
     * hibernate  语法查询 / sql的基本区别
     * <p> 单元测试 Rolled back transaction for test
     */
    @Test
    @Transactional
    void updateHPQL(){
        EntityManager entityManager = userService.getEntityManager();
        // update语句执行createQuery的时候，不能传对象类。
        Query query = entityManager.createQuery("update User as u set u.name =:name  where id = :id");
        query.setParameter("name", "updatename");
        query.setParameter("id", 4L);
        int result = query.executeUpdate();
        System.out.println("影响的记录数:" + result);
    }

    /**
     * hibernate  语法查询 / sql的基本区别
     * <p> 单元测试 Rolled back transaction for test
     */
    @Test
    @Transactional
    void deleteHPQL(){
        EntityManager entityManager = userService.getEntityManager();
        // delete语句执行createQuery的时候，不能传对象类。
        Query query = entityManager.createQuery("delete from User where id = :id");
        query.setParameter("id", 4L);
        int result = query.executeUpdate();
        System.out.println("影响的记录数:" + result);
    }

}
