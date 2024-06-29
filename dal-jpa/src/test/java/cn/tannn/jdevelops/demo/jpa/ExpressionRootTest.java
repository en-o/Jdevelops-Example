package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.ExpressionRootDemoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 内置JPA上下文 CommonStateExpressionRoot
 * @see cn.tannn.jdevelops.jpa.constant.CommonStateExpressionRoot
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午2:45
 */
@SpringBootTest
public class ExpressionRootTest {

    @Autowired
    private ExpressionRootDemoDao expressionRootDemoDao;

    @Test
    void emptyBean() {
        // 查询不到的 观测sql就好了
        // from test_expression_root expression0_ where expression0_.gender=1
        expressionRootDemoDao.findManGender();
        // from test_expression_root expression0_ where expression0_.gender=0
        expressionRootDemoDao.findUnknownGender();
        //  from test_expression_root expression0_ where expression0_.gender=2
        expressionRootDemoDao.findWomanGender();
    }
}
