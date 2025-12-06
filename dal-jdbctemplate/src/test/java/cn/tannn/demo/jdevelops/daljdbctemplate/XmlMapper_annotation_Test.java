package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    /**
     * 准备测试数据
     */
    @BeforeAll
    static void prepareTestData(@Autowired UserMapper userMapper) {
        System.out.println("========== 准备测试数据 ==========");

        // 1. 清空所有数据
        int deleted = userMapper.deleteAll();
        System.out.println("清空数据: " + deleted + " 条");

        // 2. 插入测试数据(20条)
        List<UserMapperEntity> testUsers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            UserMapperEntity user = new UserMapperEntity();
            user.setUsername("test_user_" + i);
            user.setEmail("test" + i + "@example.com");
            user.setAge(20 + (i % 15));  // 年龄 20-34
            user.setStatus(i % 2 == 0 ? 1 : 2);  // 状态交替1和2
            testUsers.add(user);
        }

        // 批量插入
        int inserted = userMapper.batchInsert(testUsers);
        System.out.println("插入测试数据: " + inserted + " 条");
        System.out.println("========== 测试数据准备完成 ==========\n");
    }

    // ==================== 查询测试 ====================

    @Test
    @Order(1)
    @DisplayName("01. 根据ID查询单条记录")
    void testFindById() {
        // 先查询一个用户获取ID
        UserQuery listQuery = new UserQuery();
        listQuery.setPageSize(1);
        listQuery.setOffset(0);
        List<UserMapperEntity> users = userMapper.findUsersPage(listQuery);
        assertFalse(users.isEmpty(), "应该至少有一条数据");

        Long userId = users.get(0).getId();

        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setId(userId);

        // 执行查询
        UserMapperEntity result = userMapper.findById(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertEquals(userId, result.getId(), "用户ID应该匹配");
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

    @Test
    @Order(7)
    @DisplayName("07. 分页查询 - 第一页")
    void testPageQuery_FirstPage() {
        // 创建分页参数
        PageRequest pageRequest = new PageRequest(1, 5);
        pageRequest.setOrderBy("created_at");
        pageRequest.setOrderDir("DESC");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);  // 只查状态为1的用户

        // 查询第一页数据
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);

        // 查询总数
        Long total = userMapper.countUsersByCondition(query);

        // 构建分页结果
        PageResult<UserMapperEntity> pageResult = new PageResult<>(
                pageRequest.getPageNum(),
                pageRequest.getPageSize(),
                total,
                list
        );

        // 验证结果
        assertNotNull(pageResult.getList(), "数据列表不应为空");
        assertTrue(pageResult.getList().size() <= 5, "每页最多5条");
        assertEquals(1, pageResult.getPageNum(), "应该是第1页");
        assertNotNull(pageResult.getTotal(), "总数不应为空");

        System.out.println("分页查询结果: " + pageResult);
        System.out.println("第1页数据: " + pageResult.getList().size() + " 条");
        System.out.println("总记录数: " + pageResult.getTotal());
        System.out.println("总页数: " + pageResult.getPages());
    }

    @Test
    @Order(8)
    @DisplayName("08. 分页查询 - 第二页")
    void testPageQuery_SecondPage() {
        // 创建分页参数(第2页)
        PageRequest pageRequest = new PageRequest(2, 5);

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 查询第二页数据
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);

        // 查询总数
        Long total = userMapper.countUsersByCondition(query);

        // 构建分页结果
        PageResult<UserMapperEntity> pageResult = new PageResult<>(
                pageRequest.getPageNum(),
                pageRequest.getPageSize(),
                total,
                list
        );

        // 验证结果
        assertNotNull(pageResult.getList(), "数据列表不应为空");
        assertEquals(2, pageResult.getPageNum(), "应该是第2页");
        assertTrue(pageResult.getHasPrevious(), "应该有上一页");

        System.out.println("第2页数据: " + pageResult.getList().size() + " 条");
    }

    @Test
    @Order(9)
    @DisplayName("09. 分页查询 - 空条件查询所有")
    void testPageQuery_AllData() {
        // 创建分页参数
        PageRequest pageRequest = new PageRequest(1, 10);

        // 不设置任何查询条件
        UserQuery query = new UserQuery();

        // 查询数据
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
        Long total = userMapper.countUsersByCondition(query);

        // 构建分页结果
        PageResult<UserMapperEntity> pageResult = new PageResult<>(
                pageRequest.getPageNum(),
                pageRequest.getPageSize(),
                total,
                list
        );

        // 验证结果
        assertEquals(20L, pageResult.getTotal(), "总共应该有20条测试数据");
        assertEquals(10, pageResult.getList().size(), "第一页应该有10条数据");
        assertEquals(2, pageResult.getPages(), "应该有2页");
        assertTrue(pageResult.getHasNext(), "应该有下一页");
        assertFalse(pageResult.getHasPrevious(), "不应该有上一页");

        System.out.println("查询所有数据: " + pageResult);
    }

    @Test
    @Order(10)
    @DisplayName("10. 分页查询 - 多条件组合")
    void testPageQuery_WithConditions() {
        // 创建分页参数
        PageRequest pageRequest = new PageRequest(1, 5);
        pageRequest.setOrderBy("age");
        pageRequest.setOrderDir("ASC");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(25);
        query.setMaxAge(30);

        // 查询数据
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
        Long total = userMapper.countUsersByCondition(query);

        // 构建分页结果
        PageResult<UserMapperEntity> pageResult = new PageResult<>(
                pageRequest.getPageNum(),
                pageRequest.getPageSize(),
                total,
                list
        );

        // 验证结果
        assertNotNull(pageResult.getList(), "数据列表不应为空");
        System.out.println("多条件分页查询: 总数=" + pageResult.getTotal() + ", 当前页=" + pageResult.getList().size());

        // 验证年龄范围
        pageResult.getList().forEach(user -> {
            assertTrue(user.getAge() >= 25 && user.getAge() <= 30, "年龄应该在25-30之间");
            assertEquals(1, user.getStatus(), "状态应该为1");
        });
    }

    @Test
    @Order(11)
    @DisplayName("11. 【框架内置分页】一键分页查询")
    void testPageQueryAuto() {
        // 创建框架提供的分页参数
        PageRequest pageRequest = new PageRequest(1, 5);
        pageRequest.setOrderBy("created_at");
        pageRequest.setOrderDir("DESC");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 直接调用框架内置分页方法，一行代码完成分页查询
        cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult<UserMapperEntity> pageResult =
                userMapper.findUsersPageAuto(query, pageRequest);

        // 验证结果
        assertNotNull(pageResult, "分页结果不应为空");
        assertNotNull(pageResult.getList(), "数据列表不应为空");
        assertTrue(pageResult.getList().size() <= 5, "每页最多5条");
        assertEquals(1, pageResult.getPageNum(), "应该是第1页");
        assertNotNull(pageResult.getTotal(), "总数不应为空");
        assertTrue(pageResult.getTotal() > 0, "总数应该>0");

        System.out.println("=========================================");
        System.out.println("【框架内置分页】测试结果:");
        System.out.println("当前页码: " + pageResult.getPageNum());
        System.out.println("每页大小: " + pageRequest.getPageSize());
        System.out.println("总记录数: " + pageResult.getTotal());
        System.out.println("总页数: " + pageResult.getPages());
        System.out.println("当前页数据量: " + pageResult.getList().size());
        System.out.println("是否有下一页: " + pageResult.getHasNext());
        System.out.println("是否有上一页: " + pageResult.getHasPrevious());
        System.out.println("=========================================");
    }

    @Test
    @Order(12)
    @DisplayName("12. 【框架内置分页】多条件分页")
    void testPageQueryAutoWithConditions() {
        // 创建分页参数
        cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest pageRequest =
                new cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest(1, 3);
        pageRequest.setOrderBy("age");
        pageRequest.setOrderDir("ASC");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(20);
        query.setMaxAge(28);

        // 框架自动处理分页
        cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult<UserMapperEntity> pageResult =
                userMapper.findUsersPageAuto(query, pageRequest);

        // 验证结果
        assertNotNull(pageResult, "分页结果不应为空");
        assertTrue(pageResult.getList().size() <= 3, "每页最多3条");

        System.out.println("【框架内置分页】多条件查询: 总数=" + pageResult.getTotal() +
                ", 当前页=" + pageResult.getList().size());

        // 验证数据
        pageResult.getList().forEach(user -> {
            assertTrue(user.getAge() >= 20 && user.getAge() <= 28, "年龄应该在20-28之间");
            assertEquals(1, user.getStatus(), "状态应该为1");
        });
    }

    // ==================== 插入测试 ====================

    @Test
    @Order(20)
    @DisplayName("20. 插入单条记录（返回自增ID）")
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

        // 打印调试信息
        System.out.println("插入的ID: id1=" + id1 + ", id2=" + id2);
        System.out.println("对象的ID: user1.id=" + user1.getId() + ", user2.id=" + user2.getId());

        // 创建删除参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(id1, id2));

        // 先验证数据是否插入成功
        UserQuery checkQuery1 = new UserQuery();
        checkQuery1.setId(id1);
        UserMapperEntity check1 = userMapper.findById(checkQuery1);
        System.out.println("删除前查询 id1: " + (check1 != null ? check1.getUsername() : "未找到"));

        UserQuery checkQuery2 = new UserQuery();
        checkQuery2.setId(id2);
        UserMapperEntity check2 = userMapper.findById(checkQuery2);
        System.out.println("删除前查询 id2: " + (check2 != null ? check2.getUsername() : "未找到"));

        // 执行批量删除
        int rows = userMapper.deleteByIds(query);

        // 验证结果
        System.out.println("批量删除影响行数: " + rows);
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
