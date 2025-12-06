package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XML Mapper 注解接口测试
 * <p>
 * 测试使用 @XmlMapper 注解的接口方式调用 XML SQL
 * <p>
 * 涵盖功能：
 * <ul>
 *     <li>单条查询、列表查询、IN 查询</li>
 *     <li>插入操作（返回自增ID）</li>
 *     <li>批量插入</li>
 *     <li>动态更新</li>
 *     <li>删除操作</li>
 *     <li>统计查询</li>
 *     <li>分页查询</li>
 *     <li>高级查询（多条件、排序）</li>
 *     <li>动态 SQL（if、where、foreach、set 等标签）</li>
 * </ul>
 *
 * @author tnnn
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class XmlMapper_annotation_Test {

    @Autowired
    private UserMapper userMapper;

    // ==================== 查询测试 ====================

    @Test
    @Order(1)
    @DisplayName("01. 根据ID查询单条记录")
    void testFindById() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setId(1L);

        // 执行查询
        UserMapperEntity result = userMapper.findById(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertEquals(1L, result.getId(), "用户ID应该为1");
        System.out.println("查询到用户: " + result.getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("02. 动态条件查询用户列表")
    void testFindUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(18);

        // 执行查询
        List<UserMapperEntity> result = userMapper.findUsers(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("查询到 " + result.size() + " 条用户记录");
        result.forEach(user -> {
            assertTrue(user.getAge() >= 18, "年龄应该>=18");
            assertEquals(1, user.getStatus(), "状态应该为1");
        });
    }

    @Test
    @Order(3)
    @DisplayName("03. 根据ID列表批量查询（IN查询）")
    void testFindByIds() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(1L, 2L, 3L));

        // 执行查询
        List<UserMapperEntity> result = userMapper.findByIds(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertTrue(result.size() <= 3, "结果数量不应超过3");
        System.out.println("批量查询到 " + result.size() + " 条记录");
    }

    @Test
    @Order(4)
    @DisplayName("04. 统计用户数量")
    void testCountUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 执行统计
        Integer result = userMapper.countUsers(query);

        // 验证结果
        assertNotNull(result, "统计结果不应为空");
        assertTrue(result >= 0, "用户数量应该>=0");
        System.out.println("状态为1的用户数量: " + result);
    }

    @Test
    @Order(5)
    @DisplayName("05. 分页查询用户列表")
    void testFindUsersPage() {
        // 创建分页查询参数
        UserQuery query = new UserQuery();
        query.setPageSize(5);
        query.setOffset(0);

        // 执行分页查询
        List<UserMapperEntity> result = userMapper.findUsersPage(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertTrue(result.size() <= 5, "分页大小应该<=5");
        System.out.println("分页查询到 " + result.size() + " 条记录");
    }

    @Test
    @Order(6)
    @DisplayName("06. 高级查询（多条件+排序）")
    void testFindUsersAdvanced() {
        // 创建高级查询参数
        UserQuery query = new UserQuery();
        query.setKeyword("test");
        query.setStatusList(Arrays.asList(1, 2));
        query.setOrderBy("created_at DESC");

        // 执行高级查询
        List<UserMapperEntity> result = userMapper.findUsersAdvanced(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("高级查询到 " + result.size() + " 条记录");
    }

    // ==================== 插入测试 ====================

    @Test
    @Order(10)
    @DisplayName("10. 插入单条记录（返回自增ID）")
    void testInsertUser() {
        // 创建用户对象
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername("testuser_" + System.currentTimeMillis());
        user.setEmail("test_" + System.currentTimeMillis() + "@example.com");
        user.setAge(25);
        user.setStatus(1);

        // 执行插入
        Long userId = userMapper.insertUser(user);

        // 验证结果
        assertNotNull(userId, "返回的ID不应为空");
        assertTrue(userId > 0, "返回的ID应该>0");
        assertNotNull(user.getId(), "对象的ID属性应该被自动设置");
        assertEquals(userId, user.getId(), "返回的ID应该与对象的ID一致");

        System.out.println("插入成功，自增ID: " + userId);
        System.out.println("对象ID已自动回填: " + user.getId());
    }

    @Test
    @Order(11)
    @DisplayName("11. 批量插入用户")
    void testBatchInsert() {
        // 创建用户列表
        List<UserMapperEntity> users = Arrays.asList(
                createUser("batch1", "batch1@example.com", 21),
                createUser("batch2", "batch2@example.com", 22),
                createUser("batch3", "batch3@example.com", 23)
        );

        // 执行批量插入
        int rows = userMapper.batchInsert(users);

        // 验证结果
        assertTrue(rows >= 0, "批量插入影响行数应该>=0");
        System.out.println("批量插入 " + rows + " 条记录");
    }

    // ==================== 更新测试 ====================

    @Test
    @Order(20)
    @DisplayName("20. 动态更新用户信息（只更新非null字段）")
    void testUpdateUser() {
        // 先插入一条测试数据
        UserMapperEntity insertUser = new UserMapperEntity();
        insertUser.setUsername("update_test_" + System.currentTimeMillis());
        insertUser.setEmail("update_" + System.currentTimeMillis() + "@example.com");
        insertUser.setAge(30);
        insertUser.setStatus(1);
        Long userId = userMapper.insertUser(insertUser);

        // 创建更新参数（只设置部分字段）
        UserMapperEntity updateUser = new UserMapperEntity();
        updateUser.setId(userId);
        updateUser.setUsername("updated_user");
        updateUser.setEmail("updated@example.com");
        // 注意：age 和 status 没有设置，不会被更新

        // 执行更新
        int rows = userMapper.updateUser(updateUser);

        // 验证结果
        assertTrue(rows > 0, "更新影响行数应该>0");
        System.out.println("动态更新成功，影响行数: " + rows);

        // 查询验证
        UserQuery query = new UserQuery();
        query.setId(userId);
        UserMapperEntity result = userMapper.findById(query);
        assertEquals("updated_user", result.getUsername(), "用户名应该已更新");
        assertEquals(30, result.getAge(), "年龄不应该被更新");
    }

    // ==================== 删除测试 ====================

    @Test
    @Order(30)
    @DisplayName("30. 根据ID删除单条记录")
    void testDeleteById() {
        // 先插入一条测试数据
        UserMapperEntity insertUser = new UserMapperEntity();
        insertUser.setUsername("delete_test_" + System.currentTimeMillis());
        insertUser.setEmail("delete_" + System.currentTimeMillis() + "@example.com");
        insertUser.setAge(20);
        insertUser.setStatus(1);
        Long userId = userMapper.insertUser(insertUser);

        // 创建删除参数
        UserMapperEntity deleteUser = new UserMapperEntity();
        deleteUser.setId(userId);

        // 执行删除
        int rows = userMapper.deleteById(deleteUser);

        // 验证结果
        assertTrue(rows > 0, "删除影响行数应该>0");
        System.out.println("删除成功，影响行数: " + rows);

        // 验证已删除
        UserQuery query = new UserQuery();
        query.setId(userId);
        UserMapperEntity result = userMapper.findById(query);
        assertNull(result, "删除后查询应该为空");
    }

    @Test
    @Order(31)
    @DisplayName("31. 根据ID列表批量删除")
    void testDeleteByIds() {
        // 先插入测试数据
        UserMapperEntity user1 = createUser("delete1", "delete1@example.com", 21);
        UserMapperEntity user2 = createUser("delete2", "delete2@example.com", 22);
        Long id1 = userMapper.insertUser(user1);
        Long id2 = userMapper.insertUser(user2);

        // 创建删除参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(id1, id2));

        // 执行批量删除
        int rows = userMapper.deleteByIds(query);

        // 验证结果
        assertTrue(rows >= 2, "批量删除影响行数应该>=2");
        System.out.println("批量删除 " + rows + " 条记录");
    }

    // ==================== 动态SQL测试 ====================

    @Test
    @Order(40)
    @DisplayName("40. 动态SQL - 部分条件查询")
    void testDynamicSql() {
        // 测试1: 只设置用户名条件
        UserQuery query1 = new UserQuery();
        query1.setUsername("%test%");
        List<UserMapperEntity> result1 = userMapper.findUsers(query1);
        assertNotNull(result1, "查询结果不应为空");
        System.out.println("按用户名查询到 " + result1.size() + " 条记录");

        // 测试2: 只设置邮箱条件
        UserQuery query2 = new UserQuery();
        query2.setEmail("test@example.com");
        List<UserMapperEntity> result2 = userMapper.findUsers(query2);
        assertNotNull(result2, "查询结果不应为空");

        // 测试3: 设置多个条件
        UserQuery query3 = new UserQuery();
        query3.setStatus(1);
        query3.setMinAge(20);
        query3.setMaxAge(30);
        List<UserMapperEntity> result3 = userMapper.findUsers(query3);
        assertNotNull(result3, "查询结果不应为空");
        System.out.println("多条件查询到 " + result3.size() + " 条记录");
    }

    @Test
    @Order(41)
    @DisplayName("41. 动态SQL - where标签测试")
    void testDynamicWhere() {
        // 不设置任何条件，测试 where 标签自动去除 AND
        UserQuery query = new UserQuery();
        List<UserMapperEntity> result = userMapper.findUsers(query);
        assertNotNull(result, "查询结果不应为空");
        System.out.println("无条件查询到 " + result.size() + " 条记录");
    }

    // ==================== 边界测试 ====================

    @Test
    @Order(50)
    @DisplayName("50. 查询不存在的ID")
    void testFindNonExistentId() {
        UserQuery query = new UserQuery();
        query.setId(999999L);

        UserMapperEntity result = userMapper.findById(query);

        assertNull(result, "查询不存在的ID应该返回null");
    }

    @Test
    @Order(51)
    @DisplayName("51. 删除不存在的记录")
    void testDeleteNonExistent() {
        UserMapperEntity user = new UserMapperEntity();
        user.setId(999999L);

        int rows = userMapper.deleteById(user);

        assertEquals(0, rows, "删除不存在的记录应该返回0");
    }

    // ==================== 辅助方法 ====================

    /**
     * 辅助方法：创建用户对象
     */
    private UserMapperEntity createUser(String username, String email, Integer age) {
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername(username + "_" + System.currentTimeMillis());
        user.setEmail(email.replace("@", "_" + System.currentTimeMillis() + "@"));
        user.setAge(age);
        user.setStatus(1);
        return user;
    }
}
