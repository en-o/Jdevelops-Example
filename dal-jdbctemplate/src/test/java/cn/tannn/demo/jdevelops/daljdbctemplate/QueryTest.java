package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.jdectemplate.core.CreateProxy;
import cn.tannn.jdevelops.result.request.Paging;
import cn.tannn.jdevelops.result.response.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class QueryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    QueryUserService queryUserService;

    @BeforeEach
    void x(){
        queryUserService = (QueryUserService)CreateProxy.createQueryProxy(QueryUserService.class, jdbcTemplate, Query.class);
    }

    @Test
    void findAll() {
        queryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void findById() {
        System.out.println(queryUserService.findById());
    }

    @Test
    void findByIdByBo() {
        System.out.println(queryUserService.findByIdByBo());
    }

    @Test
    void findById2() {
        System.out.println(queryUserService.findById(2));
    }

    @Test
    void findById3() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            queryUserService.findById(10);
//        });
            //优化成 null 了
        Assertions.assertNull(queryUserService.findById(10));
    }

    @Test
    void findByBean() {
        UserBO userBO = new UserBO();
        userBO.setName("超级管理员");
        userBO.setLoginName("admin");
        System.out.println(queryUserService.findByBean(userBO));
    }

    @Test
    void findByBean2() {
        UserBO userBO = new UserBO();
        userBO.setName("111");
        userBO.setLoginName("SH-01");
        queryUserService.findByBean2(userBO).forEach(it -> System.out.println(it.toString()));
    }

    @Test
    void findName() {
        queryUserService.findName().forEach(System.out::println);
    }


    @Test
    void findId() {
        System.out.println(queryUserService.findId());
    }


    @Test
    void findIdByName() {
        System.out.println(queryUserService.findIdByName("用户1"));
    }

    @Test
    void findIdByName2() {
        assertEquals("User{id=6, userNo='1469200914007695361', name='111', address='重庆', loginName='XX-01', loginPwd='1231', phone='1312', userIcon='null'}"
                , queryUserService.findIdByNameAndAddress("111", "重庆").toString());
        // null
        Assertions.assertNull(queryUserService.findIdByNameAndAddress("112", "重庆"));
        Assertions.assertThrows(IncorrectResultSizeDataAccessException.class, () -> {
            // IncorrectResultSizeDataAccessException
            queryUserService.findIdByNameAndAddress("test2", "重庆test");
        });
    }

    @Test
    void findAllPage() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));

        PageResult<User> allPage = queryUserService.findAllPage(new Paging(1, 2));
        System.out.println("第一页：" + allPage);
        System.out.println("第二页：" + queryUserService.findAllPage(new Paging(2, 2)));
    }

    @Test
    void findAllPage_2() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserService.findAllPage("111", "重庆", new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findAllPage("111", "重庆", new Paging(2, 2)));
    }

    @Test
    void findAllPageByName() {
//        queryUserServiceImpl.findAllPage(new Paging(1, 2))
//                .getRows().forEach(it -> System.out.printf(it.toString()));
        System.out.println("第一页：" + queryUserService.findAllPageByName(new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findAllPageByName(new Paging(2, 2)));
    }

    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderD() {
        queryUserService.findAllOrderD().forEach(it -> System.out.printf(it.toString()));
    }
    /**
     * 排序 - 写死
     */
    @Test
    void findAllOrderA() {
        queryUserService.findAllOrderA().forEach(it -> System.out.printf(it.toString()));
    }


    /**
     * page - like
     */
    @Test
    void findLikePage() {
        // currentPage=2, pageSize=2, totalPages=2, total=4

        // sql select * from sys_user where name like '%用户%'
        // sql SELECT COUNT(*) from sys_user where name like '%用户%'
        System.out.println("第一页：" + queryUserService.findLikePage("%用户%", new Paging(1, 2)));
        System.out.println("第二页：" + queryUserService.findLikePage("%用户%", new Paging(2, 2)));
    }
}
