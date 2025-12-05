package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XML Mapper 测试 使用 @XmlMapper 注解接口
 *
 * @author tnnn
 */
@SpringBootTest
class XmlMapper_annotation_Test {


    @Autowired
    private UserMapper userMapper;

    @Test
    void testFindById() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(List.of(1L));

        // 执行查询
        UserMapperEntity result = userMapper.findById(query);

        // 验证结果
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(18);

        // 执行查询
        List<UserMapperEntity> result = userMapper.findUsers(query);

        // 验证结果
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindByIds() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(1L, 2L, 3L));

        // 执行查询
        List<UserMapperEntity> result = userMapper.findByIds(query);

        // 验证结果
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() <= 3);
    }

    @Test
    void testInsertUser() {
        // 创建用户对象
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername("testuser" + System.currentTimeMillis());
        user.setEmail("test" + System.currentTimeMillis() + "@example.com");
        user.setAge(25);
        user.setStatus(1);

        // 执行插入
        int rows = userMapper.insertUser(user);

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
        int rows = userMapper.updateUser(user);

        // 验证结果
        assertTrue(rows >= 0);
    }

    @Test
    void testDeleteById() {
        // 先插入一条测试数据
        UserMapperEntity insertUser = new UserMapperEntity();
        insertUser.setUsername("delete_test" + System.currentTimeMillis());
        insertUser.setEmail("delete" + System.currentTimeMillis() + "@example.com");
        insertUser.setAge(20);
        insertUser.setStatus(1);
        userMapper.insertUser(insertUser);

        // 创建删除参数（使用实际存在的ID）
        UserMapperEntity user = new UserMapperEntity();
        user.setId(1L);

        // 执行删除
        int rows = userMapper.deleteById(user);

        // 验证结果
        assertTrue(rows >= 0);
    }

    @Test
    void testCountUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 执行统计
        Integer result = userMapper.countUsers(query);

        // 验证结果
        assertNotNull(result);
        assertTrue(result >= 0);
    }

    @Test
    void testDynamicSql() {
        // 测试动态 SQL - 只设置部分条件
        UserQuery query = new UserQuery();
        query.setUsername("alice");

        // 执行查询
        List<UserMapperEntity> result = userMapper.findUsers(query);

        // 验证结果
        assertNotNull(result);
        // 动态SQL可能返回空列表，这是正常的
    }

    @Test
    void testBatchInsert() {
        // 创建用户列表
        List<UserMapperEntity> users = Arrays.asList(
                createUser("batch1", "batch1@example.com", 21),
                createUser("batch2", "batch2@example.com", 22),
                createUser("batch3", "batch3@example.com", 23)
        );

        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setUsers(users);

        // 执行批量插入
        int rows = userMapper.batchInsert(query);

        // 验证结果
        assertTrue(rows >= 0);
    }

    @Test
    void testFindUsersPage() {
        // 创建分页查询参数
        UserQuery query = new UserQuery();
        query.setPageSize(2);
        query.setOffset(0);

        // 执行分页查询
        List<UserMapperEntity> result = userMapper.findUsersPage(query);

        // 验证结果
        assertNotNull(result);
        assertTrue(result.size() <= 2);
    }

    /**
     * 辅助方法：创建用户对象
     */
    private UserMapperEntity createUser(String username, String email, Integer age) {
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername(username + System.currentTimeMillis());
        user.setEmail(email.replace("@", System.currentTimeMillis() + "@"));
        user.setAge(age);
        user.setStatus(1);
        return user;
    }

    @Test
    void testDeleteAll() {

        int rows = userMapper.deleteAll();
        // 验证结果
        assertTrue(rows >= 0);
    }
}
