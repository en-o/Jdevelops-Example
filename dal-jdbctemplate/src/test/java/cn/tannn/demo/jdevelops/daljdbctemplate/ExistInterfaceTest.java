package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.service.ExistInterface;
import cn.tannn.demo.jdevelops.daljdbctemplate.service.ExistInterfaceImpl;
import cn.tannn.jdevelops.jdectemplate.core.CreateProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 适配 interface + impl class + default
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/12/16 09:59
 */
@SpringBootTest
class ExistInterfaceTest {

    private ExistInterface existInterface;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @BeforeEach
    void x(){
        existInterface = (ExistInterface) CreateProxy.createQueryProxy(ExistInterface.class, jdbcTemplate);
    }


    @Test
    void findAll() {
        existInterface.findAll().forEach(it -> System.out.printf(it.toString()));
    }

    @Test
    void findById() {
        System.out.println(existInterface.findById());
    }

    @Test
    void findByIdByBo() {
        System.out.println(existInterface.findByIdByBo());
    }
}
