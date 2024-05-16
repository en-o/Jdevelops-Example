package cn.tannn.jdevelops.demo.jpa.entityManager;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 测试 j2service entity manageer CriteriaBuilder 简单示例
 *
 * @author <a href="https://tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/5/15 下午9:58
 */
@SpringBootTest
public class EntityManagerCriteriaBuilderTest {

    @Autowired
    private UserService userService;


    @Test
    void def(){
        EntityManager entityManager = userService.getEntityManager();

        // 创建条件（条件表达式）和查询的工厂类
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 构建查询的接口。它允许你定义查询的SELECT子句（即查询结果），FROM子句（即查询的主体），WHERE子句（即查询条件），以及可选的ORDER BY子句（即结果排序）
        //  这个可以是其他的 vo,不一定是实体类，但是字段要相同
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // 查询中的根, 通常是实体的一个实例
        Root<User> from = query.from(User.class);

        // 构造查询
        Predicate predicate = criteriaBuilder.equal(from.get("name"), "111");
        query.where(predicate);
        entityManager.createQuery(query).getResultList().forEach(System.out::println);
    }


    @Test
    void specification(){
        // 构造 specification
        Specification<User> spec =  (root, cq, cb) -> cb.equal(root.get("name"), "111");

        EntityManager entityManager = userService.getEntityManager();


        // 创建条件（条件表达式）和查询的工厂类
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 构建查询的接口。它允许你定义查询的SELECT子句（即查询结果），FROM子句（即查询的主体），WHERE子句（即查询条件），以及可选的ORDER BY子句（即结果排序）
        //  这个可以是其他的 vo,不一定是实体类，但是字段要相同
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // 查询中的根, 通常是实体的一个实例
        Root<User> from = query.from(User.class);

        // 上面构造出来的东西是传给 spec 用的，用来获取 spec 里的 查询条件 返回给 entityManager 做查询

        query.where(spec.toPredicate(from, query, criteriaBuilder));
        entityManager.createQuery(query).getResultList().forEach(System.out::println);
    }
}
