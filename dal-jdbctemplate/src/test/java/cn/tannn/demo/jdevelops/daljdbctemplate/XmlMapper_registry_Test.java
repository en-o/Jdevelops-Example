package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.registry.XmlMapperRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XML Mapper 测试 使用 XmlMapperRegistry
 *
 * @author tnnn
 */
@SpringBootTest
class XmlMapper_registry_Test {
    private static final String NAMESPACE = "cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper";

    @Autowired
    private XmlMapperRegistry registry;

    @Test
    void testFindById() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(List.of(1L));

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findById", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result);
        if (result instanceof UserMapperEntity user) {
            assertEquals(1L, user.getId());
        }
    }

    @Test
    void testFindUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(18);

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result);
        assertInstanceOf(List.class, result);
    }

    @Test
    void testFindByIds() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(1L, 2L, 3L));

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findByIds", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result);
        assertInstanceOf(List.class, result);
    }

    @Test
    void testInsertUser() {
        // 创建用户对象
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setAge(25);
        user.setStatus(1);

        // 执行插入
        int rows = registry.executeUpdate(NAMESPACE, "insertUser", user);

        // 验证结果
        assertEquals(1, rows);
    }

    @Test
    void testUpdateUser() {
        // 创建更新参数
        UserMapperEntity user = new UserMapperEntity();
        user.setId(1L);
        user.setUsername("updated_user");
        user.setEmail("updated@example.com");

        // 执行更新
        int rows = registry.executeUpdate(NAMESPACE, "updateUser", user);

        // 验证结果
        assertTrue(rows >= 0);
    }

    @Test
    void testDeleteById() {
        // 创建删除参数
        UserMapperEntity user = new UserMapperEntity();
        user.setId(1L);

        // 执行删除
        int rows = registry.executeUpdate(NAMESPACE, "deleteById", user);

        // 验证结果
        assertTrue(rows >= 0);
    }

    @Test
    void testCountUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 执行统计
        Object result = registry.executeQuery(NAMESPACE, "countUsers", query, Integer.class);

        // 验证结果
        assertNotNull(result);
        assertInstanceOf(Integer.class, result);
    }

    @Test
    void testDynamicSql() {
        // 测试动态 SQL - 只设置部分条件
        UserQuery query = new UserQuery();
        query.setUsername("test%");

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result);
        assertInstanceOf(List.class, result);
    }
}
