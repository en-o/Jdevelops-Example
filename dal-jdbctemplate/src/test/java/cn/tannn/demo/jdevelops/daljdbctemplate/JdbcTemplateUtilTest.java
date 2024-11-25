package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.entity.User;
import cn.tannn.jdevelops.jdectemplate.util.JdbcTemplateUtil;
import cn.tannn.jdevelops.result.request.Paging;
import cn.tannn.jdevelops.result.response.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试JdbcTemplateUtil
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/11/25 下午1:17
 */
@SpringBootTest
public class JdbcTemplateUtilTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testPageFullSql(){
        Paging paging1 = new Paging(1, 2);
        Paging paging2 = new Paging(2, 2);
        PageResult<User> p1 = JdbcTemplateUtil.paging(jdbcTemplate
                , "select * from sys_user where name like '%用户%'"
                , User.class, paging1);

        PageResult<User> p2 = JdbcTemplateUtil.paging(jdbcTemplate
                , "select * from sys_user where name like '%用户%'"
                , User.class, paging2);

        System.out.println("第一页：" + p1);
        System.out.println("第二页：" + p2);
    }

    @Test
    void testPagePlaceholderSql(){
        PageResult<User> paging = JdbcTemplateUtil.paging(jdbcTemplate
                , "select * from sys_user where name like ? "
                , User.class, new Paging(1, 2), "%用户%");
        System.out.println(paging);
    }
}
