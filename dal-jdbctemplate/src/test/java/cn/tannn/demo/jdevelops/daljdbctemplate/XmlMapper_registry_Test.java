package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.registry.XmlMapperRegistry;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XML Mapper Registry 测试
 * <p>
 * 测试直接使用 XmlMapperRegistry 调用 XML SQL
 * <p>
 * 适用场景：
 * <ul>
 *     <li>不需要定义接口，直接通过 namespace 和 statementId 调用</li>
 *     <li>动态 SQL 场景（运行时决定调用哪个 SQL）</li>
 *     <li>框架内部使用</li>
 * </ul>
 * <p>
 * 涵盖功能：
 * <ul>
 *     <li>executeQuery - 执行查询操作</li>
 *     <li>executeUpdate - 执行更新操作（INSERT/UPDATE/DELETE）</li>
 *     <li>动态 SQL 测试</li>
 *     <li>返回类型适配测试</li>
 * </ul>
 *
 * @author tnnn
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class XmlMapper_registry_Test {

    /**
     * UserMapper 的命名空间
     */
    private static final String NAMESPACE = "cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper";

    @Autowired
    private XmlMapperRegistry registry;


    /**
     * 准备测试数据
     */
    @BeforeAll
    static void prepareTestData(@Autowired UserMapper userMapper) {
        System.out.println("========== Registry 测试 - 准备数据 ==========");

        // 1. 清空所有数据
        int deleted = userMapper.deleteAll();
        System.out.println("清空数据: " + deleted + " 条");

        // 2. 插入测试数据(20条)
        List<UserMapperEntity> testUsers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            UserMapperEntity user = new UserMapperEntity();
            user.setUsername("registry_user_" + i);
            user.setEmail("registry" + i + "@example.com");
            user.setAge(20 + (i % 15));
            user.setStatus(i % 2 == 0 ? 1 : 2);
            testUsers.add(user);
        }

        int inserted = userMapper.batchInsert(testUsers);
        System.out.println("插入测试数据: " + inserted + " 条");
        System.out.println("========== 测试数据准备完成 ==========\n");
    }

    // ==================== 查询测试 ====================

    @Test
    @Order(1)
    @DisplayName("01. Registry查询 - 根据ID查询单条记录")
    void testFindById() {
        // 创建查询参数
//        UserQuery query = new UserQuery();
//        query.setId(1L);
//
//        // 执行查询
//        Object result = registry.executeQuery(NAMESPACE, "findById", query, UserMapperEntity.class);
//
//        // 验证结果
//        assertNotNull(result, "查询结果不应为空");
//        assertInstanceOf(UserMapperEntity.class, result, "结果应该是 UserMapperEntity 类型");
//
//        UserMapperEntity user = (UserMapperEntity) result;
//        assertEquals(1L, user.getId(), "用户ID应该为1");
//        System.out.println("查询到用户: " + user.getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("02. Registry查询 - 查询用户列表")
    void testFindUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);
        query.setMinAge(18);

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;
        System.out.println("查询到 " + users.size() + " 条用户记录");
    }

    @Test
    @Order(3)
    @DisplayName("03. Registry查询 - IN查询")
    void testFindByIds() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(1L, 2L, 3L));

        // 执行查询
        Object result = registry.executeQuery(NAMESPACE, "findByIds", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;
        assertTrue(users.size() <= 3, "结果数量不应超过3");
        System.out.println("批量查询到 " + users.size() + " 条记录");
    }

    @Test
    @Order(4)
    @DisplayName("04. Registry查询 - 统计查询")
    void testCountUsers() {
        // 创建查询参数
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 执行统计
        Object result = registry.executeQuery(NAMESPACE, "countUsers", query, Integer.class);

        // 验证结果
        assertNotNull(result, "统计结果不应为空");
        assertInstanceOf(Integer.class, result, "结果应该是 Integer 类型");

        Integer count = (Integer) result;
        assertTrue(count >= 0, "用户数量应该>=0");
        System.out.println("状态为1的用户数量: " + count);
    }

    @Test
    @Order(5)
    @DisplayName("05. Registry查询 - 分页查询")
    void testFindUsersPage() {
        // 创建分页查询参数
        UserQuery query = new UserQuery();
        query.setPageSize(5);
        query.setOffset(0);

        // 执行分页查询
        Object result = registry.executeQuery(NAMESPACE, "findUsersPage", query, UserMapperEntity.class);

        // 验证结果
        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;
        assertTrue(users.size() <= 5, "分页大小应该<=5");
        System.out.println("分页查询到 " + users.size() + " 条记录");
    }

    // ==================== 插入测试 ====================

    @Test
    @Order(10)
    @DisplayName("10. Registry更新 - 插入记录（返回自增ID）")
    void testInsertUser() {
        // 创建用户对象
        UserMapperEntity user = new UserMapperEntity();
        user.setUsername("registry_test_" + System.currentTimeMillis());
        user.setEmail("registry_" + System.currentTimeMillis() + "@example.com");
        user.setAge(25);
        user.setStatus(1);

        // 执行插入（注意：Registry 返回 Object，需要根据配置判断返回值）
        Object result = registry.executeUpdate(NAMESPACE, "insertUser", user);

        // 验证结果
        assertNotNull(result, "返回结果不应为空");
        System.out.println("插入操作返回: " + result + " (类型: " + result.getClass().getSimpleName() + ")");

        // 如果配置了 useGeneratedKeys=true，返回的是自增ID
        if (result instanceof Number) {
            Number id = (Number) result;
            assertTrue(id.longValue() > 0, "返回的ID应该>0");
            System.out.println("自增ID: " + id);
        }
    }

    @Test
    @Order(11)
    @DisplayName("11. Registry更新 - 批量插入")
    void testBatchInsert() {
        // 创建用户列表
        List<UserMapperEntity> users = Arrays.asList(
                createUser("registry_batch1", "batch1@example.com", 21),
                createUser("registry_batch2", "batch2@example.com", 22),
                createUser("registry_batch3", "batch3@example.com", 23)
        );

        // 执行批量插入
        Object result = registry.executeUpdate(NAMESPACE, "batchInsert", users);

        // 验证结果
        assertNotNull(result, "返回结果不应为空");
        System.out.println("批量插入返回: " + result);
    }

    // ==================== 更新测试 ====================

    @Test
    @Order(20)
    @DisplayName("20. Registry更新 - 动态更新用户信息")
    void testUpdateUser() {
        // 先插入一条测试数据
        UserMapperEntity insertUser = new UserMapperEntity();
        insertUser.setUsername("registry_update_" + System.currentTimeMillis());
        insertUser.setEmail("update_" + System.currentTimeMillis() + "@example.com");
        insertUser.setAge(30);
        insertUser.setStatus(1);
        Object insertResult = registry.executeUpdate(NAMESPACE, "insertUser", insertUser);
        System.out.println("插入成功，返回: " + insertResult);

        // 使用返回的ID或对象中的ID进行更新
        Long userId = insertUser.getId();

        // 创建更新参数
        UserMapperEntity updateUser = new UserMapperEntity();
        updateUser.setId(userId);
        updateUser.setUsername("registry_updated");
        updateUser.setEmail("updated@example.com");

        // 执行更新
        Object updateResult = registry.executeUpdate(NAMESPACE, "updateUser", updateUser);

        // 验证结果
        assertNotNull(updateResult, "更新结果不应为空");
        System.out.println("更新返回: " + updateResult);

        // 查询验证
        UserQuery query = new UserQuery();
        query.setId(userId);
        Object queryResult = registry.executeQuery(NAMESPACE, "findById", query, UserMapperEntity.class);

        assertNotNull(queryResult, "查询结果不应为空");
        List<UserMapperEntity> user = (List<UserMapperEntity>) queryResult;
        assertEquals("registry_updated", user.get(0).getUsername(), "用户名应该已更新");
    }

    // ==================== 删除测试 ====================

    @Test
    @Order(30)
    @DisplayName("30. Registry更新 - 根据ID删除记录")
    void testDeleteById() {
        // 先插入一条测试数据
        UserMapperEntity insertUser = new UserMapperEntity();
        insertUser.setUsername("registry_delete_" + System.currentTimeMillis());
        insertUser.setEmail("delete_" + System.currentTimeMillis() + "@example.com");
        insertUser.setAge(20);
        insertUser.setStatus(1);
        registry.executeUpdate(NAMESPACE, "insertUser", insertUser);

        Long userId = insertUser.getId();

        // 创建删除参数
        UserMapperEntity deleteUser = new UserMapperEntity();
        deleteUser.setId(userId);

        // 执行删除
        Object deleteResult = registry.executeUpdate(NAMESPACE, "deleteById", deleteUser);

        // 验证结果
        assertNotNull(deleteResult, "删除结果不应为空");
        System.out.println("删除返回: " + deleteResult);

        // 验证已删除
        UserQuery query = new UserQuery();
        query.setId(userId);
        Object queryResult = registry.executeQuery(NAMESPACE, "findById", query, UserMapperEntity.class);
        List<Object> resultList = (List<Object>) queryResult;
        assertTrue(resultList.isEmpty(), "删除后查询应该为空");
    }

    @Test
    @Order(31)
    @DisplayName("31. Registry更新 - 批量删除")
    void testDeleteByIds() {
        // 先插入测试数据
        UserMapperEntity user1 = createUser("registry_del1", "del1@example.com", 21);
        UserMapperEntity user2 = createUser("registry_del2", "del2@example.com", 22);
        registry.executeUpdate(NAMESPACE, "insertUser", user1);
        registry.executeUpdate(NAMESPACE, "insertUser", user2);

        Long id1 = user1.getId();
        Long id2 = user2.getId();

        // 创建删除参数
        UserQuery query = new UserQuery();
        query.setIds(Arrays.asList(id1, id2));

        // 执行批量删除
        Object deleteResult = registry.executeUpdate(NAMESPACE, "deleteByIds", query);

        // 验证结果
        assertNotNull(deleteResult, "删除结果不应为空");
        System.out.println("批量删除返回: " + deleteResult);
    }

    // ==================== 动态SQL测试 ====================

    @Test
    @Order(40)
    @DisplayName("40. Registry查询 - 动态SQL测试")
    void testDynamicSql() {
        // 测试1: 只设置用户名条件
        UserQuery query1 = new UserQuery();
        query1.setUsername("%registry%");
        Object result1 = registry.executeQuery(NAMESPACE, "findUsers", query1, UserMapperEntity.class);
        assertNotNull(result1, "查询结果不应为空");
        System.out.println("按用户名查询结果类型: " + result1.getClass().getSimpleName());

        // 测试2: 设置多个条件
        UserQuery query2 = new UserQuery();
        query2.setStatus(1);
        query2.setMinAge(20);
        query2.setMaxAge(30);
        Object result2 = registry.executeQuery(NAMESPACE, "findUsers", query2, UserMapperEntity.class);
        assertNotNull(result2, "查询结果不应为空");

        // 测试3: 不设置任何条件
        UserQuery query3 = new UserQuery();
        Object result3 = registry.executeQuery(NAMESPACE, "findUsers", query3, UserMapperEntity.class);
        assertNotNull(result3, "查询结果不应为空");
    }

    // ==================== 边界测试 ====================

    @Test
    @Order(50)
    @DisplayName("50. Registry查询 - 查询不存在的记录")
    void testFindNonExistent() {
        UserQuery query = new UserQuery();
        query.setId(999999L);

        Object result = registry.executeQuery(NAMESPACE, "findById", query, UserMapperEntity.class);
        List result2 = (List<?>) result;
        assertTrue(result2.isEmpty(), "查询不存在的ID应该返回null");
    }

    @Test
    @Order(51)
    @DisplayName("51. Registry更新 - 删除不存在的记录")
    void testDeleteNonExistent() {
        UserMapperEntity user = new UserMapperEntity();
        user.setId(999999L);

        Object result = registry.executeUpdate(NAMESPACE, "deleteById", user);

        assertNotNull(result, "删除结果不应为空");
        System.out.println("删除不存在记录返回: " + result);
    }

    // ==================== Registry 特性测试 ====================

    @Test
    @Order(55)
    @DisplayName("55. Registry分页 - 分页查询")
    void testPageQueryWithRegistry() {
        // 创建分页参数
        PageRequest pageRequest = new PageRequest(1, 5);
        pageRequest.setOrderBy("created_at");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 执行分页查询
        Object listResult = registry.executeQuery(
                NAMESPACE,
                "findUsersPageWithTotal",
                Arrays.asList(query, pageRequest),  // 多参数需要用List传递
                UserMapperEntity.class
        );

        // 执行总数查询
        Object totalResult = registry.executeQuery(
                NAMESPACE,
                "countUsersByCondition",
                query,
                Long.class
        );

        // 验证结果
        assertNotNull(listResult, "数据列表不应为空");
        assertNotNull(totalResult, "总数不应为空");
        assertInstanceOf(List.class, listResult, "应该返回List类型");
        assertInstanceOf(Long.class, totalResult, "结果应该是 Long 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> list = (List<UserMapperEntity>) listResult;
        Long total = (Long) totalResult;

        // 构建分页结果
        PageResult<UserMapperEntity> pageResult = new PageResult<>(
                pageRequest.getPageNum(),
                pageRequest.getPageSize(),
                total,
                list
        );

        System.out.println("Registry 分页查询: " + pageResult);
        assertTrue(pageResult.getList().size() <= 5, "每页最多5条");
    }

    @Test
    @Order(56)
    @DisplayName("56. 【框架内置分页】Registry 方式一键分页")
    void testPageQueryAutoWithRegistry() {
        // 创建分页参数（使用框架提供的 PageRequest）
        cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest pageRequest =
                new cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest(1, 5);
        pageRequest.setOrderBy("created_at");
        pageRequest.setOrderDir("DESC");

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setStatus(1);

        // 使用 Registry 的 executePageQuery 方法，框架自动处理分页
        cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult<UserMapperEntity> pageResult =
                registry.executePageQuery(
                        NAMESPACE,
                        "findUsersPageWithTotal",      // 数据查询 SQL
                        "countUsersByCondition",       // 统计查询 SQL
                        query,                         // 查询参数
                        pageRequest,                   // 分页参数
                        UserMapperEntity.class         // 结果类型
                );

        // 验证结果
        assertNotNull(pageResult, "分页结果不应为空");
        assertNotNull(pageResult.getList(), "数据列表不应为空");
        assertTrue(pageResult.getList().size() <= 5, "每页最多5条");
        assertNotNull(pageResult.getTotal(), "总数不应为空");
        assertTrue(pageResult.getTotal() > 0, "总数应该>0");

        System.out.println("=========================================");
        System.out.println("【Registry 内置分页】测试结果:");
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
    @Order(60)
    @DisplayName("60. Registry特性 - 获取已注册的Mapper")
    void testGetRegisteredMappers() {
        // 获取所有已注册的 Mapper
        var mappers = registry.getRegisteredMappers();

        // 验证结果
        assertNotNull(mappers, "已注册Mapper列表不应为空");
        assertFalse(mappers.isEmpty(), "应该至少有一个已注册的Mapper");
        assertTrue(mappers.contains(NAMESPACE), "应该包含 UserMapper");

        System.out.println("已注册的 Mapper 数量: " + mappers.size());
        System.out.println("已注册的 Mapper:");
        mappers.forEach(ns -> System.out.println("  - " + ns));
    }

    @Test
    @Order(61)
    @DisplayName("61. Registry特性 - 获取指定Mapper")
    void testGetMapper() {
        // 获取指定的 Mapper
        var mapper = registry.getMapper(NAMESPACE);

        // 验证结果
        assertNotNull(mapper, "Mapper不应为空");
        assertEquals(NAMESPACE, mapper.getNamespace(), "命名空间应该匹配");

        System.out.println("Mapper 命名空间: " + mapper.getNamespace());
        System.out.println("SQL 语句数量: " + mapper.getSqlStatements().size());
    }

    @Test
    @Order(62)
    @DisplayName("62. Registry特性 - 获取SQL语句配置")
    void testGetSqlStatement() {
        // 获取 SQL 语句配置
        var statement = registry.getSqlStatement(NAMESPACE, "findById");

        // 验证结果
        assertNotNull(statement, "SQL语句配置不应为空");
        assertEquals("findById", statement.getId(), "语句ID应该匹配");
        System.out.println("SQL 语句ID: " + statement.getId());
        System.out.println("SQL 命令类型: " + statement.getCommandType());
        System.out.println("返回类型: " + statement.getResultType());
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

    // ==================== 特殊符号处理测试（Registry方式） ====================

    @Test
    @Order(70)
    @DisplayName("70. 【Registry-特殊符号】XML实体转义测试")
    void testSpecialChars_EntityEscape_Registry() {
        UserQuery query = new UserQuery();
        query.setMinAge(25);  // age > 25
        query.setMaxAge(30);  // age < 30

        Object result = registry.executeQuery(NAMESPACE, "findUsersWithLessThan", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-XML实体转义测试】:");
        System.out.println("查询条件: age > 25 AND age < 30");
        System.out.println("查询到 " + users.size() + " 条记录");
        users.forEach(user -> {
            assertTrue(user.getAge() > 25 && user.getAge() < 30, "年龄应在(25,30)区间");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(71)
    @DisplayName("71. 【Registry-特殊符号】CDATA区块测试")
    void testSpecialChars_CDATA_Registry() {
        UserQuery query = new UserQuery();
        query.setMinAge(20);         // age > 20
        query.setMaxAgeEqual(28);    // age <= 28

        Object result = registry.executeQuery(NAMESPACE, "findUsersWithCDATA", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-CDATA区块测试】:");
        System.out.println("查询条件: age > 20 AND age <= 28");
        System.out.println("查询到 " + users.size() + " 条记录");
        users.forEach(user -> {
            assertTrue(user.getAge() > 20 && user.getAge() <= 28, "年龄应在(20,28]区间");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(72)
    @DisplayName("72. 【Registry-BETWEEN】年龄范围查询")
    void testBetween_Age_Registry() {
        UserQuery query = new UserQuery();
        query.setMinAge(22);
        query.setMaxAge(27);
        query.setStatus(1);

        Object result = registry.executeQuery(NAMESPACE, "findUsersBetweenAge", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-BETWEEN测试】:");
        System.out.println("查询条件: age BETWEEN 22 AND 27");
        System.out.println("查询到 " + users.size() + " 条记录");
        users.forEach(user -> {
            assertTrue(user.getAge() >= 22 && user.getAge() <= 27, "年龄应在[22,27]区间");
        });
        System.out.println("========================================");
    }

    @Test
    @Order(73)
    @DisplayName("73. 【Registry-复杂条件】AND + OR + NOT 组合")
    void testComplexConditions_Registry() {
        UserQuery query = new UserQuery();
        query.setMinAge(20);
        query.setMaxAge(30);
        query.setStatus1(1);
        query.setStatus2(2);
        query.setExcludeUsername("test_user_1");
        query.setExcludeIds(Arrays.asList(1L, 2L));

        Object result = registry.executeQuery(NAMESPACE, "findUsersComplexConditions", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-复杂条件测试】:");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(74)
    @DisplayName("74. 【Registry-LIKE】模糊查询测试")
    void testLike_Registry() {
        UserQuery query = new UserQuery();
        query.setUsernamePrefix("registry_user");

        Object result = registry.executeQuery(NAMESPACE, "findUsersWithLike", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-LIKE测试】:");
        System.out.println("查询条件: username LIKE 'registry_user%'");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("========================================");
    }

    // ==================== 枚举和 Record 类型方法调用测试（Registry方式） ====================

    @Test
    @Order(75)
    @DisplayName("75. 【Registry-枚举方法】测试枚举 name() 方法")
    void testEnumNameMethod_Registry() {
        // 测试场景1: platform 为 NONE，条件不满足
        UserQuery query1 = new UserQuery();
        query1.setPlatform(UserQuery.UserPlatform.NONE);
        query1.setStatus(1);

        Object result1 = registry.executeQuery(NAMESPACE, "findUsersByPlatform", query1, UserMapperEntity.class);

        assertNotNull(result1, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users1 = (List<UserMapperEntity>) result1;

        System.out.println("========================================");
        System.out.println("【Registry-枚举 name() 方法测试】场景1:");
        System.out.println("平台: " + query1.getPlatform().name());
        System.out.println("条件: platform.name() != 'NONE' 为 false");
        System.out.println("查询到 " + users1.size() + " 条记录");
        System.out.println("========================================");

        // 测试场景2: platform 为 WEB，条件满足
        UserQuery query2 = new UserQuery();
        query2.setPlatform(UserQuery.UserPlatform.WEB);
        query2.setStatus(1);

        Object result2 = registry.executeQuery(NAMESPACE, "findUsersByPlatform", query2, UserMapperEntity.class);

        assertNotNull(result2, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users2 = (List<UserMapperEntity>) result2;

        System.out.println("========================================");
        System.out.println("【Registry-枚举 name() 方法测试】场景2:");
        System.out.println("平台: " + query2.getPlatform().name());
        System.out.println("条件: platform.name() != 'NONE' 为 true");
        System.out.println("查询到 " + users2.size() + " 条记录");
        System.out.println("说明: 此场景会添加 username LIKE '%test%' 条件");
        System.out.println("========================================");
    }

    @Test
    @Order(76)
    @DisplayName("76. 【Registry-枚举方法】测试枚举 ordinal() 方法")
    void testEnumOrdinalMethod_Registry() {
        // 测试场景1: platform 为 NONE (ordinal=0)
        UserQuery query1 = new UserQuery();
        query1.setPlatform(UserQuery.UserPlatform.NONE);

        Object result1 = registry.executeQuery(NAMESPACE, "findUsersByPlatformOrdinal", query1, UserMapperEntity.class);

        assertNotNull(result1, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users1 = (List<UserMapperEntity>) result1;

        System.out.println("========================================");
        System.out.println("【Registry-枚举 ordinal() 方法测试】场景1:");
        System.out.println("平台: " + query1.getPlatform().name() +
                          " (ordinal=" + query1.getPlatform().ordinal() + ")");
        System.out.println("条件: platform.ordinal() > 0 为 false");
        System.out.println("查询到 " + users1.size() + " 条记录");
        System.out.println("========================================");

        // 测试场景2: platform 为 DESKTOP (ordinal=3)
        UserQuery query2 = new UserQuery();
        query2.setPlatform(UserQuery.UserPlatform.DESKTOP);

        Object result2 = registry.executeQuery(NAMESPACE, "findUsersByPlatformOrdinal", query2, UserMapperEntity.class);

        assertNotNull(result2, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users2 = (List<UserMapperEntity>) result2;

        System.out.println("========================================");
        System.out.println("【Registry-枚举 ordinal() 方法测试】场景2:");
        System.out.println("平台: " + query2.getPlatform().name() +
                          " (ordinal=" + query2.getPlatform().ordinal() + ")");
        System.out.println("条件: platform.ordinal() > 0 为 true");
        System.out.println("查询到 " + users2.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(77)
    @DisplayName("77. 【Registry-枚举方法】测试 arg0.platform.name() 形式")
    void testEnumNameMethodWithArg0_Registry() {
        // 测试使用 arg0 形式访问枚举的 name() 方法
        UserQuery query = new UserQuery();
        query.setPlatform(UserQuery.UserPlatform.MOBILE);
        query.setStatus(1);
        Integer limit = 5;

        Object result = registry.executeQuery(
                NAMESPACE,
                "findUsersByPlatformWithArg0",
                Arrays.asList(query, limit),  // 多参数需要用List传递
                UserMapperEntity.class
        );

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        assertTrue(users.size() <= limit, "结果数量不应超过 limit");
        System.out.println("========================================");
        System.out.println("【Registry-arg0.platform.name() 测试】:");
        System.out.println("平台: " + query.getPlatform().name());
        System.out.println("条件: arg0.platform.name() != 'NONE' 为 true");
        System.out.println("LIMIT: " + limit);
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: 验证了 Registry 方式下多参数场景的枚举方法调用");
        System.out.println("========================================");
    }

    // ==================== 多值枚举测试（Registry方式） ====================

    @Test
    @Order(78)
    @DisplayName("78. 【Registry-多值枚举】测试 getCode() 方法")
    void testMultiValueEnumGetCode_Registry() {
        // 测试场景1: userStatus 为 ACTIVE (code=1)
        UserQuery query1 = new UserQuery();
        query1.setUserStatus(UserQuery.UserStatus.ACTIVE);

        Object result1 = registry.executeQuery(NAMESPACE, "findUsersByUserStatusCode", query1, UserMapperEntity.class);

        assertNotNull(result1, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users1 = (List<UserMapperEntity>) result1;

        System.out.println("========================================");
        System.out.println("【Registry-多值枚举 getCode() 测试】场景1:");
        System.out.println("状态: " + query1.getUserStatus().name() +
                          " (code=" + query1.getUserStatus().getCode() + ")");
        System.out.println("条件: userStatus.getCode() == 1 为 true");
        System.out.println("查询到 " + users1.size() + " 条记录");
        System.out.println("========================================");

        // 测试场景2: userStatus 为 LOCKED (code=2)
        UserQuery query2 = new UserQuery();
        query2.setUserStatus(UserQuery.UserStatus.LOCKED);

        Object result2 = registry.executeQuery(NAMESPACE, "findUsersByUserStatusCode", query2, UserMapperEntity.class);

        assertNotNull(result2, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users2 = (List<UserMapperEntity>) result2;

        System.out.println("========================================");
        System.out.println("【Registry-多值枚举 getCode() 测试】场景2:");
        System.out.println("状态: " + query2.getUserStatus().name() +
                          " (code=" + query2.getUserStatus().getCode() + ")");
        System.out.println("条件: userStatus.getCode() == 1 为 false");
        System.out.println("但 userStatus.getCode() != 0 为 true");
        System.out.println("查询到 " + users2.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(79)
    @DisplayName("79. 【Registry-多值枚举】测试 getName() 方法")
    void testMultiValueEnumGetName_Registry() {
        UserQuery query = new UserQuery();
        query.setUserStatus(UserQuery.UserStatus.ACTIVE);

        Object result = registry.executeQuery(NAMESPACE, "findUsersByUserStatusName", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-多值枚举 getName() 测试】:");
        System.out.println("状态: " + query.getUserStatus().name());
        System.out.println("名称: " + query.getUserStatus().getName());
        System.out.println("条件: userStatus.getName() == '已激活' 为 true");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(80)
    @DisplayName("80. 【Registry-多值枚举】测试 getDescription() 方法")
    void testMultiValueEnumGetDescription_Registry() {
        UserQuery query = new UserQuery();
        query.setUserStatus(UserQuery.UserStatus.ACTIVE);

        Object result = registry.executeQuery(NAMESPACE, "findUsersByUserStatusDescription", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-多值枚举 getDescription() 测试】:");
        System.out.println("状态: " + query.getUserStatus().name());
        System.out.println("描述: " + query.getUserStatus().getDescription());
        System.out.println("条件: userStatus.getDescription() != null 为 true");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(81)
    @DisplayName("81. 【Registry-多值枚举】复杂条件组合测试")
    void testMultiValueEnumComplex_Registry() {
        UserQuery query = new UserQuery();
        query.setUserStatus(UserQuery.UserStatus.ACTIVE);

        Object result = registry.executeQuery(NAMESPACE, "findUsersByUserStatusComplex", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-多值枚举复杂条件测试】:");
        System.out.println("状态: " + query.getUserStatus().name());
        System.out.println("Code: " + query.getUserStatus().getCode());
        System.out.println("Name: " + query.getUserStatus().getName());
        System.out.println("Description: " + query.getUserStatus().getDescription());
        System.out.println("组合条件:");
        System.out.println("  - userStatus.getCode() > 0: true");
        System.out.println("  - userStatus.name() != 'DELETED': true");
        System.out.println("  - userStatus.getName() != null: true");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("========================================");
    }

    @Test
    @Order(82)
    @DisplayName("82. 【Registry-多值枚举】测试 arg0.userStatus.getCode() 形式")
    void testMultiValueEnumWithArg0_Registry() {
        UserQuery query = new UserQuery();
        query.setUserStatus(UserQuery.UserStatus.ACTIVE);
        Integer limit = 5;

        Object result = registry.executeQuery(
                NAMESPACE,
                "findUsersByUserStatusWithArg0",
                Arrays.asList(query, limit),  // 多参数需要用List传递
                UserMapperEntity.class
        );

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        assertTrue(users.size() <= limit, "结果数量不应超过 limit");
        System.out.println("========================================");
        System.out.println("【Registry-arg0.userStatus.getCode() 测试】:");
        System.out.println("状态: " + query.getUserStatus().name());
        System.out.println("Code: " + query.getUserStatus().getCode());
        System.out.println("Name: " + query.getUserStatus().getName());
        System.out.println("条件:");
        System.out.println("  - arg0.userStatus.getCode() == 1: true");
        System.out.println("  - arg0.userStatus.getName() == '已激活': true");
        System.out.println("LIMIT: " + limit);
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: 验证了 Registry 方式下多参数场景的多值枚举方法调用");
        System.out.println("========================================");
    }

    // ==================== 空值判断测试（Registry方式） ====================

    @Test
    @Order(90)
    @DisplayName("90. 【Registry-空值判断】空字符串 \"\" 测试")
    void testEmptyString_Registry() {
        // 测试空字符串
        UserQuery query = new UserQuery();
        query.setUsername("");
        query.setEmail("");

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");
        assertInstanceOf(List.class, result, "结果应该是 List 类型");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-空字符串测试】:");
        System.out.println("username = \"\"");
        System.out.println("email = \"\"");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试空字符串在动态SQL中的处理");
        System.out.println("========================================");
    }

    @Test
    @Order(91)
    @DisplayName("91. 【Registry-空值判断】空格字符串 \" \" 测试")
    void testBlankString_Registry() {
        // 测试只包含空格的字符串
        UserQuery query = new UserQuery();
        query.setUsername("   ");
        query.setEmail("  ");

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-空格字符串测试】:");
        System.out.println("username = \"   \"");
        System.out.println("email = \"  \"");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试空格字符串在动态SQL中的处理");
        System.out.println("========================================");
    }

    @Test
    @Order(92)
    @DisplayName("92. 【Registry-空值判断】空集合 List.size() == 0 测试")
    void testEmptyList_Registry() {
        // 测试空集合
        UserQuery query = new UserQuery();
        query.setIds(new ArrayList<>());  // 空集合
        query.setStatusList(new ArrayList<>());  // 空集合

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-空集合测试】:");
        System.out.println("ids.size() = 0");
        System.out.println("statusList.size() = 0");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试空集合在foreach标签中的处理");
        System.out.println("========================================");
    }

    @Test
    @Order(93)
    @DisplayName("93. 【Registry-空值判断】null 值测试")
    void testNullValue_Registry() {
        // 测试 null 值
        UserQuery query = new UserQuery();
        query.setUsername(null);
        query.setEmail(null);
        query.setIds(null);
        query.setStatusList(null);
        query.setMinAge(null);
        query.setMaxAge(null);

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-null值测试】:");
        System.out.println("username = null");
        System.out.println("email = null");
        System.out.println("ids = null");
        System.out.println("statusList = null");
        System.out.println("minAge = null");
        System.out.println("maxAge = null");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试null值在动态SQL中的处理");
        System.out.println("========================================");
    }

    @Test
    @Order(94)
    @DisplayName("94. 【Registry-空值判断】字符串 \"null\" 测试")
    void testNullString_Registry() {
        // 测试字符串 "null"
        UserQuery query = new UserQuery();
        query.setUsername("null");
        query.setEmail("null");

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-字符串\"null\"测试】:");
        System.out.println("username = \"null\"");
        System.out.println("email = \"null\"");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试字符串\"null\"与实际null值的区别");
        System.out.println("========================================");
    }

    @Test
    @Order(95)
    @DisplayName("95. 【Registry-空值判断】混合空值测试")
    void testMixedEmptyValues_Registry() {
        // 混合多种空值情况
        UserQuery query = new UserQuery();
        query.setUsername("");           // 空字符串
        query.setEmail(null);            // null
        query.setIds(new ArrayList<>());  // 空集合
        query.setStatusList(null);       // null集合
        query.setMinAge(null);           // null数值
        query.setKeyword("   ");         // 空格字符串

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-混合空值测试】:");
        System.out.println("username = \"\" (空字符串)");
        System.out.println("email = null");
        System.out.println("ids = [] (空集合)");
        System.out.println("statusList = null");
        System.out.println("minAge = null");
        System.out.println("keyword = \"   \" (空格)");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试多种空值混合场景");
        System.out.println("========================================");
    }

    @Test
    @Order(96)
    @DisplayName("96. 【Registry-空值判断】数值0测试")
    void testZeroValue_Registry() {
        // 测试数值0（区分null和0）
        UserQuery query = new UserQuery();
        query.setStatus(0);
        query.setMinAge(0);
        query.setMaxAge(0);

        Object result = registry.executeQuery(NAMESPACE, "findUsers", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-数值0测试】:");
        System.out.println("status = 0");
        System.out.println("minAge = 0");
        System.out.println("maxAge = 0");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试数值0与null的区别（0是有效值）");
        System.out.println("========================================");
    }

    @Test
    @Order(97)
    @DisplayName("97. 【Registry-空值判断】单元素空字符串集合测试")
    void testListWithEmptyString_Registry() {
        // 测试包含空字符串的集合
        UserQuery query = new UserQuery();
        List<Long> idsWithZero = new ArrayList<>();
        idsWithZero.add(0L);  // 添加0值
        query.setIds(idsWithZero);

        Object result = registry.executeQuery(NAMESPACE, "findByIds", query, UserMapperEntity.class);

        assertNotNull(result, "查询结果不应为空");

        @SuppressWarnings("unchecked")
        List<UserMapperEntity> users = (List<UserMapperEntity>) result;

        System.out.println("========================================");
        System.out.println("【Registry-单元素集合测试】:");
        System.out.println("ids = [0] (包含0的集合)");
        System.out.println("查询到 " + users.size() + " 条记录");
        System.out.println("说明: Registry方式测试包含特殊值的非空集合");
        System.out.println("========================================");
    }
}
