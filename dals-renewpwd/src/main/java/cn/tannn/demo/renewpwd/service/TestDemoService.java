package cn.tannn.demo.renewpwd.service;

import cn.tannn.demo.renewpwd.dao.TestDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/8/15 13:40
 */
@Service
public class TestDemoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private TestDemoDao testDemoDao;


    public Object jdbcTemplateQuery(){
        return jdbcTemplate.queryForList("select * from test_demo");
    }

    public Object namedParameterJdbcTemplateQuery(){
        return namedParameterJdbcTemplate.queryForList("select * from test_demo", Map.of());
    }

    public Object jpa(){
        return testDemoDao.findAll();
    }

    public Object jpa2(){
        return testDemoDao.findAll().get(0);
    }


}
