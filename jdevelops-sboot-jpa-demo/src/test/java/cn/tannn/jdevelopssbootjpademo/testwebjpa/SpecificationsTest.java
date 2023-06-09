package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.hutool.core.date.DateTime;
import cn.jdevelops.data.jap.core.Specifications;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 复杂查询
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 18:06
 */
@SpringBootTest
public class SpecificationsTest {
    @Autowired
    private UserService userService;


    /**

     <sql>

     FROM
        sys_user user0_
     WHERE
         (
         user0_.NAME LIKE ?)
         AND (
         user0_.phone =?
         OR user0_.address LIKE ?)
         AND (
         user0_.user_icon IS NULL)

     </sql>

     */
    @Test
    void testSpec() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.likes(User::getName, "用户");
            e.or(e2 -> {
                        e2.eq(User::getPhone, "123");
                        e2.likes(User::getAddress, "重");
                    }
            );
//            e.getBuilder().equal(e.getRoot(), "");
            e.isNull(User::getUserIcon);
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }


    /**
      <sql>
       SELECT
      	    user0_.*
       FROM
       	sys_user user0_
       WHERE
      	    user0_.user_icon IS NULL
      </sql>
     */
    @Test
    void testSpecIsNull() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.isNull(User::getUserIcon);
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }

    /**
     <sql>
          SELECT
              user0_.*
           FROM
      	        sys_user user0_
          WHERE
     	        user0_.user_icon IS NOT NULL
     </sql>
     */
    @Test
    void testSpecIsNotNull() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.isNotNull(User::getUserIcon);
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }



    /**
     <sql>
     SELECT
        user0_.*
     FROM
        sys_user user0_
     WHERE
        user0_.user_no =?
     </sql>
     */
    @Test
    void testSpecEq() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.eq(User::getUserNo,"admin");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }


    /**
     <sql>
     SELECT
        user0_.*
     FROM
        sys_user user0_
     WHERE
         user0_.user_no <> ?
     </sql>
     */
    @Test
    void testSpecNe() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.ne(User::getUserNo,"admin");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }

    /**
     * ps:  我内部处理了 所以传入的时候不要在加 %%  (不要用like方法这个我没处理%需要自己加)
     * lisks %x%
     * rlike x%
     * llike %x
     * nlike  这个不怎么用所以就没做处理 需要自己加 %
     <sql>
     SELECT
     user0_.*
     FROM
     sys_user user0_
     WHERE
     user0_.user_no LIKE ?
     -- user0_.user_no not like ?
     </sql>
     */
    @Test
    void testSpecLike() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.likes(User::getLoginName,"u");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.err::println);


        Specification<User> where2 = Specifications.<User>where(e -> {
            e.rlike(User::getLoginName,"u");
        });
        userService.getJpaBasicsDao().findAll(where2).forEach(System.err::println);

        Specification<User> where3 = Specifications.<User>where(e -> {
            e.llike(User::getLoginName,"01");
        });
        userService.getJpaBasicsDao().findAll(where3).forEach(System.err::println);


        Specification<User> where4 = Specifications.<User>where(e -> {
            e.nlike(User::getLoginName,"02%");
        });
        userService.getJpaBasicsDao().findAll(where4).forEach(System.err::println);
    }



    /**
     <sql>
     SELECT
        user0_.*
     FROM
        sys_user user0_
     WHERE
        user0_.phone >=?
        user0_.phone > ?
        user0_.phone <= ?
        user0_.phone < ?
     </sql>
     */
    @Test
    void testSpecGe() {
        Specification<User> ge = Specifications.<User>where(e -> {
            e.ge(User::getPhone,"1312");
        });
        userService.getJpaBasicsDao().findAll(ge).forEach(System.out::println);


        Specification<User> gt = Specifications.<User>where(e -> {
            e.gt(User::getPhone,"1312");
        });
        userService.getJpaBasicsDao().findAll(gt).forEach(System.err::println);

        Specification<User> le = Specifications.<User>where(e -> {
            e.le(User::getPhone,"1312");
        });
        userService.getJpaBasicsDao().findAll(le).forEach(System.out::println);


        Specification<User> lt = Specifications.<User>where(e -> {
            e.lt(User::getPhone,"1312");
        });
        userService.getJpaBasicsDao().findAll(lt).forEach(System.err::println);

    }




    /**
     <sql>
     SELECT
        user0_.*
     FROM
        sys_user user0_
     WHERE
        user0_.NAME IN (?,?)
     </sql>
     */
    @Test
    void testSpecIn() {
        Specification<User> where = Specifications.<User>where(e -> {
            e.in(User::getName,"超级管理员","111");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }


    /**
     <sql>
         SELECT
             user0_.*
         FROM
            sys_user user0_
         WHERE
            user0_.NAME not in (?,?)
     </sql>
     */
    @Test
    void testSpecNotIn() {
        Specification<User> notIn = Specifications.<User>where(e -> {
            e.notIn(User::getName,"超级管理员","111");
        });
        userService.getJpaBasicsDao().findAll(notIn).forEach(System.out::println);
    }


    /**
     <sql>
         SELECT
            user0_.*
         FROM
             sys_user user0_
         WHERE
            user0_.phone BETWEEN ? AND ?
     </sql>
     */
    @Test
    void testSpecBetween() {
        Specification<User> between = Specifications.<User>where(e -> {
            e.between(User::getPhone,"1312","15888888888");
        });
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }

    /**
     <sql>
         SELECT
            user0_.*
         FROM
            sys_user user0_
         WHERE
            DATE ( user0_.create_time ) =?
     </sql>
     */
    @Test
    void testSpecTime() {
        Date date = getDate("2021-12-03 13:48:14");
        Date endDate = getDate("2021-12-10 15:02:49");
        Specification<User> between = Specifications.<User>where(e -> {
            // 精确到了秒
//            e.eq(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
            // 精确到了分
//            e.lt(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            // 精确到了秒
//            e.le(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
              // 精确到了秒
//            e.gt(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
            // 精确到了分
//            e.ge(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
            // 精确到了秒
            e.between(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date,endDate);
        });
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }





    private  Date getDate(String timeStr){
        // 创建 SimpleDateFormat 对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 将字符串解析为 Date 对象
           return sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
