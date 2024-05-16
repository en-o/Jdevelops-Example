package cn.tannn.jdevelops.demo.jpa.entityManager;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.result.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

/**
 * 测试 j2service entity manageer 简单示例
 *
 * <p> https://developer.aliyun.com/article/885236
 *
 * @author <a href="https://tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/5/15 下午8:24
 */
@SpringBootTest
public class EntityManagerBasicTest {

    @Autowired
    private UserService userService;

    @Test
    void queryFind(){
        EntityManager em = userService.getEntityManager();
        // 为空返回null, find: null
        User user = em.find(User.class, 4L);
        System.out.println("find: "+user);
    }

    /**
     *  必须加事务 要不然会：could not initialize proxy [cn.tannn.jdevelops.demo.jpa.entity.User#4] - no Session
     */
    @Test
    @Transactional
    void queryReference(){
        EntityManager em = userService.getEntityManager();
        // 为空抛出javax.persistence.EntityNotFoundException
        User user = em.getReference(User.class, 4L);
        System.out.println("reference: "+user);
    }

    /**
     * 我这里sql执行了但是没有持久化成功不知道是不是因为单元测试的问题
     * <p> o.s.t.c.transaction.TransactionContext   : Rolled back transaction for test:
     */
    @Test
    @Transactional
    void insertPersist(){
        EntityManager em = userService.getEntityManager();
        User user = new User();
        user.setUserNo(UUIDUtils.getInstance().generateShortUuid());
        user.setName("tan");
        user.setAddress("cq");
        user.setLoginName("tan");
        user.setLoginPwd("123");
        user.setPhone("123");
        user.setUserIcon("");
        user.setCreateTime(LocalDateTime.now());
        user.setCreateUserName("tan");
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUserName("tan");
        em.persist(user);
        em.flush();
    }


    /**
     * 我这里sql执行了但是没有持久化成功不知道是不是因为单元测试的问题
     * <p> o.s.t.c.transaction.TransactionContext   : Rolled back transaction for test:
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    void updateMerge(){
        EntityManager em = userService.getEntityManager();
        User user = em.find(User.class, 4L);
        System.out.println("find: "+user);
        user.setName("tan");
        // 执行merge时，如果实体ID为空，则进行insert操作。 如果有ID则进行update操作
        em.merge(user);
        em.flush();
        System.out.println("find2 :"+em.find(User.class, 4L));
    }


    /**
     * 我这里sql执行了但是没有持久化成功不知道是不是因为单元测试的问题
     * <p> o.s.t.c.transaction.TransactionContext   : Rolled back transaction for test:
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    void deleteRemove(){
        EntityManager em = userService.getEntityManager();
        User user = em.find(User.class, 4L);
        System.out.println("find: "+user);
        user.setName("tan");
        em.remove(user);
        em.flush();
        System.out.println("find2 :"+em.find(User.class, 4L));
    }

}
