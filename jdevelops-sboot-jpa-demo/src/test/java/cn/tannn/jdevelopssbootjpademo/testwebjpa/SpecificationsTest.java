package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.hutool.core.date.DateTime;
import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.core.specification.SpecificationWrapper;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
import cn.jdevelops.data.jap.util.JpaUtils;
import cn.tannn.jdevelopssbootjpademo.dto.UserFindJpaSelectWrapperOperatorDTO;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
     * 混合
     */
    @Test
    void testSpec() {
        // and ... （里面的e.or 可以自定义组合（or ...）,如果不用则默认全部用and）
        // FROM sys_user user0_ WHERE (user0_.NAME LIKE ?) AND ( user0_.phone =? OR user0_.address LIKE ?)AND (user0_.user_icon IS NULL)
        Specification<User> where = Specifications.<User>where(e -> {
            e.likes(true,"name", "用户");
            e.or(e2 -> {
                        e2.eq(true,"phone", "123");
                        e2.likes(true,"address", "重");
                    }
            );
//            e.getBuilder().equal(e.getRoot(), "");
            e.isNull("userIcon");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.out::println);


        // or ... （里面的 and 可以自定义组合（and ...）,如果不用则默认全部用or ）
        // from sys_user user0_ where user0_.name like ? or user0_.phone=? and (user0_.address like ?) or user0_.user_icon is null
        Specification<User> or = Specifications.<User>where(false,e -> {
            e.likes(true,"name", "用户");
            e.and(e2 -> {
                        e2.eq(true,"phone", "123");
                        e2.likes(true,"address", "重");
                    }
            );
//            e.getBuilder().equal(e.getRoot(), "");
            e.isNull("userIcon");
        });
        userService.getJpaBasicsDao().findAll(or).forEach(System.out::println);
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
            e.isNull("userIcon");
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
            e.isNotNull("userIcon");
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
            e.eq(true,"userNO","admin");
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
            e.ne(true,"userNO","admin");
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
            e.likes(true,"loginName","u");
        });
        userService.getJpaBasicsDao().findAll(where).forEach(System.err::println);


        Specification<User> where2 = Specifications.<User>where(e -> {
            e.rlike(true,"loginName","u");
        });
        userService.getJpaBasicsDao().findAll(where2).forEach(System.err::println);

        Specification<User> where3 = Specifications.<User>where(e -> {
            e.llike(true,"loginName","01");
        });
        userService.getJpaBasicsDao().findAll(where3).forEach(System.err::println);


        Specification<User> where4 = Specifications.<User>where(e -> {
            e.nlike(true,"loginName","02%");
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
            e.ge(true,"phone","1312");
        });
        userService.getJpaBasicsDao().findAll(ge).forEach(System.out::println);


        Specification<User> gt = Specifications.<User>where(e -> {
            e.gt(true,"phone","1312");
        });
        userService.getJpaBasicsDao().findAll(gt).forEach(System.err::println);

        Specification<User> le = Specifications.<User>where(e -> {
            e.le(true,"phone","1312");
        });
        userService.getJpaBasicsDao().findAll(le).forEach(System.out::println);


        Specification<User> lt = Specifications.<User>where(e -> {
            e.lt(true,"phone","1312");
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
            e.in(true,"name","超级管理员","111");
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
            e.notIn(true,"name","超级管理员","111");
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
            e.between(true,"name","1312","15888888888");
        });
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }




    /**
     * 日期
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
    void testSpecDateTime() {
        Date date = getDate("2021-12-03 13:48:14");
        Date endDate = getDate("2021-12-10 15:02:49");
        Specification<User> between = Specifications.<User>where(e -> {
            // date 函数 是日期 ，所以查询只能精确到日期
            e.eq(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            e.lt(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            e.le(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            e.gt(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            e.ge(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date);
//            e.between(e.getBuilder().function("date",Date.class,e.getRoot().get("createTime")),date,endDate);
        });
        userService.getJpaBasicsDao().findAll(between).forEach(System.out::println);
    }

    /**
     * 有时分秒
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
    void testSpecTimestamp() {
        String sqlDateFormat ="%Y-%m-%d %T";
        String date = "2021-12-03 13:48:14";
        String endDate = "2021-12-10 15:02:48";
        Specification<User> between = Specifications.<User>where(e -> {
            // date 函数 是日期 ，所以查询只能精确到日期
//            e.eq(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date);

//            e.lt(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date);

//            e.le(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date);

//            e.gt(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date);

//            e.ge(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date);
//            e.between(e.getBuilder().function("DATE_FORMAT",String.class,e.getRoot().get("createTime"),e.getBuilder().literal(sqlDateFormat))
//                    ,date,endDate);
            e.between(JpaUtils.functionTimeFormat(SpecBuilderDateFun.DATE_FORMAT,
                    e.getRoot(),e.getBuilder(),"createTime"),date,endDate);
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




    /**
     * 使用非内置的方法  builder构造查询
     */
    @Test
    void testSpecBuilder() {
        // 等于  (from sys_user user0_ where user0_.name=? )
        Specification<User> equal = Specifications.<User>where(e -> {
            e.getPredicates().add(e.getBuilder().equal(e.getRoot().get("name"), "111"));
        });
        userService.getJpaBasicsDao().findAll(equal).forEach(System.out::println);


        // 小于  (from sys_user user0_ where user0_.phone<? )
        Specification<User> lessThan = Specifications.<User>where(e -> {
            e.getPredicates().add(e.getBuilder().lessThan(e.getRoot().get("phone"), "1312"));
        });
        userService.getJpaBasicsDao().findAll(lessThan).forEach(System.out::println);



        // 组合  (from sys_user user0_ where user0_.create_user_name=? and date(user0_.create_time)<? and (user0_.name like ? or user0_.user_no like ?) )
        Date date = getDate("2021-12-10 15:02:39");
        Specification<User> andCreateUserNameEqAndCreateTimeLessThanOrNameLikeOrUserNoLike = Specifications
                .where(e -> {
                    e.and( and -> {
                        and.getPredicates().add(and.getBuilder().equal(and.getRoot().get("createUserName"), "admin"));
                        and.getPredicates().add(and.getBuilder().lessThan(and.getBuilder().function("date",Date.class,and.getRoot().get("createTime")),date));
                    });
                    e.or( or -> {
                        or.getPredicates().add(or.getBuilder().like(or.getRoot().get("name"), "%用户% "));
                        or.getPredicates().add(or.getBuilder().like(or.getRoot().get("userNo"), "146%"));
                    });
        });
        userService.getJpaBasicsDao().findAll(andCreateUserNameEqAndCreateTimeLessThanOrNameLikeOrUserNoLike).forEach(System.out::println);
    }


    /**
     * 重命名
     */
    @Test
    void testSpecReName() {
        //  from sys_user user0_ where user0_.update_user_name=?
        UserFindJpaSelectWrapperOperatorDTO reName = new UserFindJpaSelectWrapperOperatorDTO();
        reName.setReName("admin");
        Specification<User>  timeSpecification = Specifications.beanWhere(reName);
        userService.getJpaBasicsDao().findAll(timeSpecification).forEach(System.out::println);
    }

    @Test
    public void SpecificationBean(){
        //  from sys_user user0_ where user0_.name=? and (user0_.phone like ?)
        UserFindJpaSelectWrapperOperatorDTO bean = new UserFindJpaSelectWrapperOperatorDTO();
        bean.setName("111");
        bean.setPhone("1312");
        bean.setAddress("222");
        Specification<User> objectSpecification = Specifications.beanWhere(bean);
        userService.getJpaBasicsDao().findAll(objectSpecification).forEach(System.out::println);

//
//        //当address 没有用注解表示的时候，为空不查，空则用 eq
//        // from sys_user user0_ where user0_.name=? and (user0_.phone like ?) and user0_.address=?
//        bean.setAddress("222");
//        objectSpecification = Specifications.beanWhere(bean);
//        userService.getJpaBasicsDao().findAll(objectSpecification).forEach(System.out::println);
//
//
//
//        //  from sys_user user0_ where user0_.login_pwd between ? and ?
//        UserFindJpaSelectWrapperOperatorDTO bean2 = new UserFindJpaSelectWrapperOperatorDTO();
//        bean2.setLoginPwd("1231,1234");
//        objectSpecification = Specifications.beanWhere(bean2);
//        userService.getJpaBasicsDao().findAll(objectSpecification).forEach(System.out::println);
//
//

        //  from sys_user user0_ where user0_.login_pwd between ? and ?
//        UserFindJpaSelectWrapperOperatorDTO time = new UserFindJpaSelectWrapperOperatorDTO();
//        time.setCreateTime("2021-12-03 14:05:23");
//        Specification<User>  timeSpecification = Specifications.beanWhere(time);
//        userService.getJpaBasicsDao().findAll(timeSpecification).forEach(System.out::println);
    }

}
