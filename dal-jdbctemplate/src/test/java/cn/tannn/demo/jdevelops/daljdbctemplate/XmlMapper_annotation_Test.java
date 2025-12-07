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
       PageResult<UserMapperEntity> pageResult =
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
        updateUser.setAge(31);

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
//        assertEquals(30, result.getAge(), "年龄不应该被更新");
        assertEquals(1, result.getStatus(), "状态不应该被更新");
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

        // 创建删除参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(id1, id2));
        int rows = userMapper.deleteByIds(query);

        // 验证结果
        System.out.println("批量删除影响行数: " + rows);
        assertTrue(rows >= 2, "批量删除影响行数应该>=2");
        System.out.println("批量删除 " + rows + " 条记录");
    }

    @Test
    @Order(32)
    @DisplayName("32. 批量插入 - 对象属性是 List<Bean>")
    void testBatchInsertFromQueryWithListOfBeans() {
        // 测试场景：参数是对象，对象中包含 List<Bean> 属性
        // UserQuery.users 是 List<UserMapperEntity> 类型
        // XML 中使用 collection="users" 访问该属性

        // 创建用户列表
        List<UserMapperEntity> users = Arrays.asList(
                createUser("listbean1", "listbean1@example.com", 21),
                createUser("listbean2", "listbean2@example.com", 22),
                createUser("listbean3", "listbean3@example.com", 23)
        );

        // 创建 UserQuery 对象，设置 users 属性
        UserQuery query = new UserQuery();
        query.setUsers(users);

        // 执行批量插入
        int rows = userMapper.batchInsertFromQuery(query);

        // 验证结果
        assertEquals(3, rows, "批量插入应该影响3行");
        System.out.println("========================================");
        System.out.println("【List<Bean> 测试】批量插入成功");
        System.out.println("影响行数: " + rows);
        System.out.println("测试说明:");
        System.out.println("  - 方法参数: UserQuery query");
        System.out.println("  - query.users 类型: List<UserMapperEntity>");
        System.out.println("  - XML 中访问: collection=\"users\"");
        System.out.println("  - 遍历元素: item=\"user\"");
        System.out.println("  - 访问属性: #{user.username}, #{user.email}");
        System.out.println("========================================");

        // 验证数据是否插入成功
        UserQuery checkQuery = new UserQuery();
        checkQuery.setUsername("%listbean%");
        List<UserMapperEntity> result = userMapper.findUsers(checkQuery);
        assertTrue(result.size() >= 3, "应该查询到至少3条记录");
        System.out.println("验证查询结果: 查询到 " + result.size() + " 条记录");
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

    // ==================== 特殊符号处理测试 ====================

    @Test
    @Order(70)
    @DisplayName("70. 【特殊符号】XML实体转义 - 测试 <、>、<=、>= 符号")
    void testSpecialChars_EntityEscape() {
        // 测试条件：查找年龄 > 25 且 < 30 的用户
        UserQuery query = new UserQuery();
        query.setMinAge(25);  // age > 25
        query.setMaxAge(30);  // age < 30

        List<UserMapperEntity> result = userMapper.findUsersWithLessThan(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【XML实体转义测试】查询结果:");
        System.out.println("查询条件: age > 25 AND age < 30");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            System.out.println("  - " + user.getUsername() + ", age=" + user.getAge());
            assertTrue(user.getAge() > 25 && user.getAge() < 30,
                    "年龄应该在(25, 30)区间内，实际:" + user.getAge());
        });
        System.out.println("========================================");
    }

    @Test
    @Order(71)
    @DisplayName("71. 【特殊符号】CDATA区块 - 测试 <、>、<=、>= 符号")
    void testSpecialChars_CDATA() {
        // 使用 CDATA 方式，测试相同的查询条件
        UserQuery query = new UserQuery();
        query.setMinAge(20);         // age > 20
        query.setMaxAgeEqual(28);    // age <= 28

        List<UserMapperEntity> result = userMapper.findUsersWithCDATA(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【CDATA区块测试】查询结果:");
        System.out.println("查询条件: age > 20 AND age <= 28");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            System.out.println("  - " + user.getUsername() + ", age=" + user.getAge());
            assertTrue(user.getAge() > 20 && user.getAge() <= 28,
                    "年龄应该在(20, 28]区间内，实际:" + user.getAge());
        });
        System.out.println("========================================");
    }

    @Test
    @Order(72)
    @DisplayName("72. 【BETWEEN】年龄范围查询")
    void testBetween_Age() {
        // 测试 BETWEEN 语法：查找年龄在 22-27 之间的用户
        UserQuery query = new UserQuery();
        query.setMinAge(22);
        query.setMaxAge(27);
        query.setStatus(1);

        List<UserMapperEntity> result = userMapper.findUsersBetweenAge(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【BETWEEN测试】年龄范围查询:");
        System.out.println("查询条件: age BETWEEN 22 AND 27, status=1");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            System.out.println("  - " + user.getUsername() + ", age=" + user.getAge() + ", status=" + user.getStatus());
            assertTrue(user.getAge() >= 22 && user.getAge() <= 27,
                    "年龄应该在[22, 27]区间内，实际:" + user.getAge());
            assertEquals(1, user.getStatus(), "状态应该为1");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(73)
    @DisplayName("73. 【BETWEEN】日期范围查询（使用CDATA）")
    void testBetween_Date() {
        // 测试日期范围查询（使用 CDATA）
        UserQuery query = new UserQuery();
        query.setStartDate("2020-01-01");
        query.setEndDate("2030-12-31");

        List<UserMapperEntity> result = userMapper.findUsersBetweenDate(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【BETWEEN日期测试】日期范围查询:");
        System.out.println("查询条件: created_at BETWEEN 2020-01-01 AND 2030-12-31");
        System.out.println("查询到 " + result.size() + " 条记录");
        System.out.println("说明：此测试验证 CDATA 在日期比较中的使用");
        System.out.println("========================================");
    }

    @Test
    @Order(74)
    @DisplayName("74. 【复杂条件】AND + OR + NOT 组合")
    void testComplexConditions() {
        // 测试复杂条件组合
        UserQuery query = new UserQuery();
        query.setMinAge(20);
        query.setMaxAge(30);
        query.setStatus1(1);
        query.setStatus2(2);
        query.setExcludeUsername("test_user_1");
        query.setExcludeIds(Arrays.asList(1L, 2L));

        List<UserMapperEntity> result = userMapper.findUsersComplexConditions(query);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【复杂条件测试】AND + OR + NOT 组合:");
        System.out.println("查询条件:");
        System.out.println("  - age >= 20 AND age <= 30");
        System.out.println("  - status IN (1, 2)");
        System.out.println("  - username != 'test_user_1'");
        System.out.println("  - id NOT IN (1, 2)");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            assertTrue(user.getAge() >= 20 && user.getAge() <= 30, "年龄应在[20,30]区间");
            assertTrue(user.getStatus() == 1 || user.getStatus() == 2, "状态应为1或2");
            assertNotEquals("test_user_1", user.getUsername(), "应排除该用户名");
            assertFalse(Arrays.asList(1L, 2L).contains(user.getId()), "应排除ID 1和2");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(75)
    @DisplayName("75. 【LIKE】模糊查询 - 前缀、全模糊、后缀")
    void testLike() {
        // 测试 LIKE 的多种使用方式
        UserQuery query = new UserQuery();
        query.setUsernamePrefix("test_user");  // 前缀匹配：test_user%

        List<UserMapperEntity> result1 = userMapper.findUsersWithLike(query);
        assertNotNull(result1, "前缀匹配结果不应为空");

        System.out.println("========================================");
        System.out.println("【LIKE测试】模糊查询:");
        System.out.println("1. 前缀匹配: username LIKE 'test_user%'");
        System.out.println("   查询到 " + result1.size() + " 条记录");

        // 测试全模糊匹配
        UserQuery query2 = new UserQuery();
        query2.setUsernameLike("user");  // 全模糊匹配：%user%

        List<UserMapperEntity> result2 = userMapper.findUsersWithLike(query2);
        System.out.println("2. 全模糊匹配: username LIKE '%user%'");
        System.out.println("   查询到 " + result2.size() + " 条记录");

        // 测试后缀匹配
        UserQuery query3 = new UserQuery();
        query3.setEmailSuffix("@example.com");  // 后缀匹配：%@example.com

        List<UserMapperEntity> result3 = userMapper.findUsersWithLike(query3);
        System.out.println("3. 后缀匹配: email LIKE '%@example.com'");
        System.out.println("   查询到 " + result3.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(76)
    @DisplayName("76. 【IN】多种数据类型的 IN 查询")
    void testInConditions() {
        // 先查询一些用户获取真实数据
        UserQuery listQuery = new UserQuery();
        listQuery.setPageSize(5);
        listQuery.setOffset(0);
        List<UserMapperEntity> users = userMapper.findUsersPage(listQuery);

        if (users.size() >= 2) {
            // 测试 ID 的 IN 查询
            List<Long> ids = Arrays.asList(users.get(0).getId(), users.get(1).getId());

            UserQuery query = new UserQuery();
            query.setIds(ids);
            query.setStatusList(Arrays.asList(1, 2));

            List<UserMapperEntity> result = userMapper.findUsersInConditions(query);

            assertNotNull(result, "IN查询结果不应为空");
            System.out.println("========================================");
            System.out.println("【IN查询测试】多种数据类型:");
            System.out.println("查询条件:");
            System.out.println("  - id IN (" + ids + ")");
            System.out.println("  - status IN (1, 2)");
            System.out.println("查询到 " + result.size() + " 条记录");
            result.forEach(user -> {
                assertTrue(ids.contains(user.getId()), "ID应在列表中");
                assertTrue(user.getStatus() == 1 || user.getStatus() == 2, "状态应为1或2");
            });
            System.out.println("========================================");
        }
    }

    @Test
    @Order(77)
    @DisplayName("77. 【NULL处理】IS NULL / IS NOT NULL / COALESCE")
    void testNullHandling() {
        // 测试 IS NOT NULL
        UserQuery query = new UserQuery();
        query.setEmailIsNotNull(true);
        query.setDefaultAge(18);

        List<UserMapperEntity> result = userMapper.findUsersWithNull(query);

        assertNotNull(result, "NULL处理查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【NULL处理测试】IS NULL / IS NOT NULL / COALESCE:");
        System.out.println("查询条件:");
        System.out.println("  - email IS NOT NULL");
        System.out.println("  - COALESCE(age, 18) >= 18");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            assertNotNull(user.getEmail(), "邮箱不应为NULL");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(78)
    @DisplayName("78. 【混合使用】实体转义 + CDATA 混合")
    void testMixedSpecialChars() {
        // 测试在同一查询中混合使用两种特殊符号处理方式
        UserQuery query = new UserQuery();
        query.setStatus(0);       // status <> 0  (使用实体转义)
        query.setMinAge(22);      // age > 22     (使用 CDATA)
        query.setMaxAge(28);      // age < 28     (使用 CDATA)
        query.setKeyword("test"); // 关键字搜索

        List<UserMapperEntity> result = userMapper.findUsersMixedSpecialChars(query);

        assertNotNull(result, "混合查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【混合使用测试】实体转义 + CDATA:");
        System.out.println("查询条件:");
        System.out.println("  - status <> 0  (使用 &lt;&gt;)");
        System.out.println("  - age > 22 AND age < 28  (使用 CDATA)");
        System.out.println("  - keyword LIKE '%test%'");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            assertNotEquals(0, user.getStatus(), "状态不应为0");
            assertTrue(user.getAge() > 22 && user.getAge() < 28, "年龄应在(22,28)区间");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(79)
    @DisplayName("79. 【数学运算】SQL 中的数学表达式")
    void testMathExpressions() {
        // 测试 SQL 中的数学运算
        UserQuery query = new UserQuery();
        query.setAgeMultiplier(10);  // age > (10 * 2) = 20
        query.setAgeBase(20);        // age >= (20 * 100 / 100) = 20
        query.setAgePercent(100);

        List<UserMapperEntity> result = userMapper.findUsersWithMath(query);

        assertNotNull(result, "数学运算查询结果不应为空");
        System.out.println("========================================");
        System.out.println("【数学运算测试】SQL 数学表达式:");
        System.out.println("查询条件:");
        System.out.println("  - age > (10 * 2)         = age > 20");
        System.out.println("  - age >= (20 * 100 / 100) = age >= 20");
        System.out.println("查询到 " + result.size() + " 条记录");
        result.forEach(user -> {
            assertTrue(user.getAge() > 20 && user.getAge() >= 20, "年龄应>20");
        });
        System.out.println("========================================");
    }
}
