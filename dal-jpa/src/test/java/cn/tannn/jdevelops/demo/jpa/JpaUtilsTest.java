package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.exception.JpaException;
import cn.tannn.jdevelops.jpa.request.Sorteds;
import cn.tannn.jdevelops.jpa.utils.JpaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

/**
 * 测试 jpaUtils中的一些方法
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 下午4:18
 */
@SpringBootTest
public class JpaUtilsTest {
    @Autowired
    private UserService userService;

    @Test
    void predicate() {
        Specification<User> specification = (root, criteriaQuery, builder) -> {
            Predicate where = JpaUtils.getPredicate(SQLOperator.EQ, builder, root.get("address"), "重庆");
            if (where == null) {
                throw new JpaException("占不支持的表达式: " + SQLOperator.EQ);
            }
            return where;
        };
        userService.getJpaBasicsDao().findAll(specification).forEach(System.out::println);
    }

    @Test
    void functionTimeFormat() {
        Specification<User> eq = (r, q, b) ->
                b.equal(JpaUtils.functionTimeFormat(
                        SpecBuilderDateFun.DATE_FORMAT
                        , r
                        , b, "createTime"),
                        "2024-05-15 11:03:31");
        // from sys_user user0_ where date_format(user0_.create_time, ?)=?
        userService.getJpaBasicsDao().findAll(eq).forEach(System.out::println);


        Specification<User> between = (r, q, b) ->
                b.between(JpaUtils.functionTimeFormat(
                                SpecBuilderDateFun.DATE_FORMAT
                                , r
                                , b, "createTime"),
                        "2019-05-15 11:03:31","2090-05-15 11:03:31");
        // from sys_user user0_ where date_format(user0_.create_time, ?) between ? and ?
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }

    @Test
    void toSql() {
        EntityManager entityManager = userService.getEntityManager();
        // ===> sql: select generatedAlias0 from User as generatedAlias0 where generatedAlias0.name=:param0
        System.out.println("===> sql: "+JpaUtils.toSql(entityManager, User.class, ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), "tan")
        )));
    }
}
