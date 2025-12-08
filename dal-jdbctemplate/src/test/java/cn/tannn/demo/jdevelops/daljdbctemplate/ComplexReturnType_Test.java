package cn.tannn.demo.jdevelops.daljdbctemplate;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.ComplexUserMapper;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.ComplexUserInterface;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.ComplexUserRecord;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 复杂类型返回测试
 * 测试 Interface 和 Record 返回类型以及复杂属性映射
 *
 * @author tnnn
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplexReturnType_Test {

    @Autowired
    private ComplexUserMapper complexUserMapper;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 固定使用的测试ID
    private static final Long USER_ID_1 = 100L;  // 完整数据用户
    private static final Long USER_ID_2 = 101L;  // NULL值测试用户
    private static final Long USER_ID_3 = 102L;  // 空集合测试用户

    // ==================== 数据准备 ====================

    @Test
    @Order(1)
    @DisplayName("01. 【准备】清空表数据")
    void prepareCleanData() {
        int count = complexUserMapper.deleteAll();
        System.out.println("清空了 " + count + " 条数据");
    }

    @Test
    @Order(2)
    @DisplayName("02. 【准备】插入测试数据")
    void prepareInsertData() throws Exception {
        List<Map<String, Object>> users = new ArrayList<>();

        // 用户1: 完整数据 (ID=100)
        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", USER_ID_1);
        user1.put("username", "complex_user_1");
        user1.put("email", "complex1@test.com");
        user1.put("age", 25);
        user1.put("status", 1); // ACTIVE
        user1.put("platform", "WEB");
        user1.put("tags", objectMapper.writeValueAsString(Arrays.asList("developer", "java", "spring")));
        user1.put("roleIds", objectMapper.writeValueAsString(Arrays.asList(1, 2, 3)));
        user1.put("extras", objectMapper.writeValueAsString(Map.of("level", 5, "vip", true, "score", 99.5)));
        user1.put("detail", objectMapper.writeValueAsString(Map.of("address", "北京市朝阳区", "phone", "13800138001",
                "avatar", "http://example.com/avatar1.jpg", "hobbies", Arrays.asList("reading", "coding", "gaming"))));
        users.add(user1);

        // 用户2: 部分NULL (ID=101)
        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", USER_ID_2);
        user2.put("username", "complex_user_2");
        user2.put("email", "complex2@test.com");
        user2.put("age", 30);
        user2.put("status", 1);
        user2.put("platform", "MOBILE");
        user2.put("tags", objectMapper.writeValueAsString(Arrays.asList("manager", "team-lead")));
        user2.put("roleIds", objectMapper.writeValueAsString(Arrays.asList(2, 4)));
        user2.put("extras", null);
        user2.put("detail", objectMapper.writeValueAsString(Map.of("address", "上海市浦东新区", "phone", "13900139002",
                "avatar", "http://example.com/avatar2.jpg", "hobbies", Arrays.asList("traveling", "photography"))));
        users.add(user2);

        // 用户3: 空集合 (ID=102)
        Map<String, Object> user3 = new HashMap<>();
        user3.put("id", USER_ID_3);
        user3.put("username", "complex_user_3");
        user3.put("email", "complex3@test.com");
        user3.put("age", 28);
        user3.put("status", 2); // LOCKED
        user3.put("platform", "DESKTOP");
        user3.put("tags", objectMapper.writeValueAsString(Collections.emptyList()));
        user3.put("roleIds", objectMapper.writeValueAsString(Collections.emptyList()));
        user3.put("extras", objectMapper.writeValueAsString(Map.of("level", 3)));

        // detail 包含 null 值，不能使用 Map.of()
        Map<String, Object> detail3 = new HashMap<>();
        detail3.put("address", "广州市天河区");
        detail3.put("phone", "13700137003");
        detail3.put("avatar", null);
        detail3.put("hobbies", Collections.emptyList());
        user3.put("detail", objectMapper.writeValueAsString(detail3));
        users.add(user3);

        int count = complexUserMapper.batchInsert(users);
        System.out.println("========================================");
        System.out.println("插入了 " + count + " 条测试数据");
        System.out.println("用户ID: " + USER_ID_1 + ", " + USER_ID_2 + ", " + USER_ID_3);
        System.out.println("========================================");
    }


    // ==================== Record 返回类型测试 ====================

    @Test
    @Order(20)
    @DisplayName("20. 【Record】查询单个用户 - 完整数据")
    void testRecordFullData() {
        ComplexUserRecord user = complexUserMapper.findByIdRecord(USER_ID_1);

        assertNotNull(user, "用户不应为null");
        System.out.println("========================================");
        System.out.println("【Record-完整数据测试】:");

        // 基本类型
        assertEquals(USER_ID_1, user.id());
        assertEquals("complex_user_1", user.username());
        assertEquals("complex1@test.com", user.email());
        assertEquals(25, user.age());
        assertEquals(1, user.status());
        System.out.println("✓ 基本类型映射正常");

        // 简单枚举
        assertEquals(UserQuery.UserPlatform.WEB, user.platform());
        System.out.println("✓ 简单枚举映射正常: " + user.platform());

        // 多值枚举
        assertEquals(UserQuery.UserStatus.ACTIVE, user.userStatus());
        System.out.println("✓ 多值枚举映射正常: " + user.userStatus());

        // List<String>
        assertNotNull(user.tags());
        assertEquals(3, user.tags().size());
        assertTrue(user.tags().contains("developer"));
        System.out.println("✓ List<String>映射正常: " + user.tags());

        // List<Integer>
        assertNotNull(user.roleIds());
        assertEquals(3, user.roleIds().size());
        assertTrue(user.roleIds().contains(1));
        System.out.println("✓ List<Integer>映射正常: " + user.roleIds());

        // Map<String, Object>
        assertNotNull(user.extras());
        assertEquals(5, user.extras().get("level"));
        assertEquals(true, user.extras().get("vip"));
        System.out.println("✓ Map<String,Object>映射正常: " + user.extras());

        // 嵌套Record
        assertNotNull(user.detail());
        assertEquals("北京市朝阳区", user.detail().address());
        assertEquals("13800138001", user.detail().phone());
        assertNotNull(user.detail().hobbies());
        assertEquals(3, user.detail().hobbies().size());
        System.out.println("✓ 嵌套Record映射正常: " + user.detail().address());

        // JSON字符串
        assertNotNull(user.tagsJson());
        assertTrue(user.tagsJson().contains("developer"));
        System.out.println("✓ JSON字符串映射正常: " + user.tagsJson());

        // 时间类型
        assertNotNull(user.createdAt());
        System.out.println("✓ LocalDateTime映射正常: " + user.createdAt());

        System.out.println("========================================");
    }

    @Test
    @Order(21)
    @DisplayName("21. 【Record】查询列表 - 多条数据")
    void testRecordList() {
        List<ComplexUserRecord> users = complexUserMapper.findAllRecord();

        assertNotNull(users);
        assertEquals(3, users.size());

        System.out.println("========================================");
        System.out.println("【Record-列表查询测试】:");
        System.out.println("查询到 " + users.size() + " 条记录");

        for (ComplexUserRecord user : users) {
            System.out.println("- ID: " + user.id() + ", 用户名: " + user.username() +
                    ", 平台: " + user.platform() + ", 状态: " + user.userStatus());
        }
        System.out.println("========================================");
    }

    @Test
    @Order(22)
    @DisplayName("22. 【Record】处理NULL值")
    void testRecordNullValues() {
        // 用户2的extras是null
        ComplexUserRecord user = complexUserMapper.findByIdRecord(USER_ID_2);

        assertNotNull(user);
        System.out.println("========================================");
        System.out.println("【Record-NULL值处理测试】:");

        assertNull(user.extras(), "extras应该为null");
        System.out.println("✓ NULL值处理正常: extras = null");

        assertNotNull(user.tags());
        assertNotNull(user.roleIds());
        assertNotNull(user.detail());
        System.out.println("✓ 非NULL字段正常: tags, roleIds, detail");

        System.out.println("========================================");
    }

    @Test
    @Order(23)
    @DisplayName("23. 【Record】处理空集合")
    void testRecordEmptyCollections() {
        // 用户3的tags和roleIds是空集合
        ComplexUserRecord user = complexUserMapper.findByIdRecord(USER_ID_3);

        assertNotNull(user);
        System.out.println("========================================");
        System.out.println("【Record-空集合处理测试】:");

        assertNotNull(user.tags());
        assertEquals(0, user.tags().size());
        System.out.println("✓ 空List<String>处理正常: tags.size() = 0");

        assertNotNull(user.roleIds());
        assertEquals(0, user.roleIds().size());
        System.out.println("✓ 空List<Integer>处理正常: roleIds.size() = 0");

        assertNotNull(user.detail());
        assertNotNull(user.detail().hobbies());
        assertEquals(0, user.detail().hobbies().size());
        System.out.println("✓ 嵌套Record空集合处理正常: hobbies.size() = 0");

        System.out.println("========================================");
    }
}
