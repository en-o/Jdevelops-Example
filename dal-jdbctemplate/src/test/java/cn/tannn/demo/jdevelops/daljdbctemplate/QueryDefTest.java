package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.UserBO;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.DefQueryUserService;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserServiceImpl;
import cn.tannn.jdevelops.result.request.Paging;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;


@SpringBootTest
class QueryDefTest {

    @Autowired
    private DefQueryUserService defQueryUserService;

    @Test
    void findAll() {
        defQueryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
    }

}
