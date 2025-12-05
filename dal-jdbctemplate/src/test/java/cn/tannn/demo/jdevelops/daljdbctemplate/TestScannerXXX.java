package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.jdevelops.annotations.jdbctemplate.proxysql.JdbcTemplate;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/12/13 12:16
 */

@SpringBootTest
public class TestScannerXXX {


//    @Test
    void mainxxx() {
        // 指定要扫描的基础包
        String basePackage = "cn.tannn.demo.jdevelops.daljdbctemplate";

        // 创建 Reflections 实例
        Reflections reflections = new Reflections(basePackage, Scanners.FieldsAnnotated);

        // 查找所有带有 @JdbcTemplate 注解的字段
        Set<Field> annotatedFields = reflections.getFieldsAnnotatedWith(JdbcTemplate.class);
        // 打印结果
        for (Field field : annotatedFields) {
            System.out.println("Found field with @JdbcTemplate: " + field.getDeclaringClass().getName() + "." + field.getName());
        }
    }
}
