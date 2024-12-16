package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.ExistInterface;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.NoInterfaceQueryImpl;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.QueryUserService;
import cn.tannn.jdevelops.annotations.jdbctemplate.JdbcTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/12/13 14:40
 */
@Component
public class TestScanner implements ApplicationRunner {

    @JdbcTemplate
    QueryUserService queryUserService;


    @JdbcTemplate
    NoInterfaceQueryImpl noInterfaceQuery;

    @JdbcTemplate
    ExistInterface existInterface;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        queryUserService.findAll().forEach(it -> System.out.printf(it.toString()));
        System.out.println("noInterfaceQuery"+noInterfaceQuery.findById());
        System.out.println("existInterface:"+existInterface.findById());
        System.out.println("existInterface#findByIdByBo:"+existInterface.findByIdByBo());
    }
}
