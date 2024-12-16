package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.DefQueryUserService;
import cn.tannn.jdevelops.annotations.jdbctemplate.Query;
import cn.tannn.jdevelops.jdectemplate.core.CreateProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 适配 interface default
 */
@SpringBootTest
class QueryDefTest {

    private DefQueryUserService defQueryUserService;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;


    @BeforeEach
    void x(){
        defQueryUserService = (DefQueryUserService) CreateProxy.createQueryProxy(DefQueryUserService.class, jdbcTemplate);
    }
    @Test
    void findAll() {
        defQueryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
    }

}
