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
        user3.put("detail", objectMapper.writeValueAsString(Map.of("address", "广州市天河区", "phone", "13700137003",
                "avatar", (String) null, "hobbies", Collections.emptyList())));
        users.add(user3);

        int count = complexUserMapper.batchInsert(users);
        System.out.println("========================================");
        System.out.println("插入了 " + count + " 条测试数据");
        System.out.println("用户ID: " + USER_ID_1 + ", " + USER_ID_2 + ", " + USER_ID_3);
        System.out.println("========================================");
    }


    // ==================== Interface 返回类型测试 ====================

    @Test
    @Order(10)
    @DisplayName("10. 【Interface】查询单个用户 - 完整数据")
    void testInterfaceFullData() {
        ComplexUserInterface user = complexUserMapper.findByIdInterface(USER_ID_1);

        assertNotNull(user, "用户不应为null");
        System.out.println("========================================");
        System.out.println("【Interface-完整数据测试】:");

        // 基本类型
        assertEquals(USER_ID_1, user.getId());
        assertEquals("complex_user_1", user.getUsername());
        assertEquals("complex1@test.com", user.getEmail());
        assertEquals(25, user.getAge());
        assertEquals(1, user.getStatus());
        System.out.println("✓ 基本类型映射正常");

        // 简单枚举
        assertEquals(UserQuery.UserPlatform.WEB, user.getPlatform());
        System.out.println("✓ 简单枚举映射正常: " + user.getPlatform());

        // 多值枚举
        assertEquals(UserQuery.UserStatus.ACTIVE, user.getUserStatus());
        System.out.println("✓ 多值枚举映射正常: " + user.getUserStatus());

        // List<String>
        assertNotNull(user.getTags());
        assertEquals(3, user.getTags().size());
        assertTrue(user.getTags().contains("developer"));
        System.out.println("✓ List<String>映射正常: " + user.getTags());

        // List<Integer>
        assertNotNull(user.getRoleIds());
        assertEquals(3, user.getRoleIds().size());
        assertTrue(user.getRoleIds().contains(1));
        System.out.println("✓ List<Integer>映射正常: " + user.getRoleIds());

        // Map<String, Object>
        assertNotNull(user.getExtras());
        assertEquals(5, user.getExtras().get("level"));
        assertEquals(true, user.getExtras().get("vip"));
        System.out.println("✓ Map<String,Object>映射正常: " + user.getExtras());

        // 嵌套对象
        assertNotNull(user.getDetail());
        assertEquals("北京市朝阳区", user.getDetail().getAddress());
        assertEquals("13800138001", user.getDetail().getPhone());
        assertNotNull(user.getDetail().getHobbies());
        assertEquals(3, user.getDetail().getHobbies().size());
        System.out.println("✓ 嵌套Interface映射正常: " + user.getDetail().getAddress());

        // JSON字符串
        assertNotNull(user.getTagsJson());
        assertTrue(user.getTagsJson().contains("developer"));
        System.out.println("✓ JSON字符串映射正常: " + user.getTagsJson());

        // 时间类型
        assertNotNull(user.getCreatedAt());
        System.out.println("✓ LocalDateTime映射正常: " + user.getCreatedAt());

        System.out.println("========================================");
    }

    @Test
    @Order(11)
    @DisplayName("11. 【Interface】查询列表 - 多条数据")
    void testInterfaceList() {
        List<ComplexUserInterface> users = complexUserMapper.findAllInterface();

        assertNotNull(users);
        assertEquals(3, users.size());

        System.out.println("========================================");
        System.out.println("【Interface-列表查询测试】:");
        System.out.println("查询到 " + users.size() + " 条记录");

        for (ComplexUserInterface user : users) {
            System.out.println("- ID: " + user.getId() + ", 用户名: " + user.getUsername() +
                    ", 平台: " + user.getPlatform() + ", 状态: " + user.getUserStatus());
        }
        System.out.println("========================================");
    }

    @Test
    @Order(12)
    @DisplayName("12. 【Interface】处理NULL值")
    void testInterfaceNullValues() {
        // 用户2的extras是null
        ComplexUserInterface user = complexUserMapper.findByIdInterface(USER_ID_2);

        assertNotNull(user);
        System.out.println("========================================");
        System.out.println("【Interface-NULL值处理测试】:");

        assertNull(user.getExtras(), "extras应该为null");
        System.out.println("✓ NULL值处理正常: extras = null");

        assertNotNull(user.getTags());
        assertNotNull(user.getRoleIds());
        assertNotNull(user.getDetail());
        System.out.println("✓ 非NULL字段正常: tags, roleIds, detail");

        System.out.println("========================================");
    }

    @Test
    @Order(13)
    @DisplayName("13. 【Interface】处理空集合")
    void testInterfaceEmptyCollections() {
        // 用户3的tags和roleIds是空集合
        ComplexUserInterface user = complexUserMapper.findByIdInterface(USER_ID_3);

        assertNotNull(user);
        System.out.println("========================================");
        System.out.println("【Interface-空集合处理测试】:");

        assertNotNull(user.getTags());
        assertEquals(0, user.getTags().size());
        System.out.println("✓ 空List<String>处理正常: tags.size() = 0");

        assertNotNull(user.getRoleIds());
        assertEquals(0, user.getRoleIds().size());
        System.out.println("✓ 空List<Integer>处理正常: roleIds.size() = 0");

        assertNotNull(user.getDetail());
        assertNotNull(user.getDetail().getHobbies());
        assertEquals(0, user.getDetail().getHobbies().size());
        System.out.println("✓ 嵌套对象空集合处理正常: hobbies.size() = 0");

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


    // ==================== 枚举转换测试 ====================

    @Test
    @Order(30)
    @DisplayName("30. 【枚举转换】测试不同状态的枚举转换")
    void testEnumConversion() {
        System.out.println("========================================");
        System.out.println("【枚举转换测试】:");

        // ACTIVE状态
        ComplexUserInterface user1 = complexUserMapper.findByIdInterface(USER_ID_1);
        assertEquals(1, user1.getStatus());
        assertEquals(UserQuery.UserStatus.ACTIVE, user1.getUserStatus());
        System.out.println("✓ status=1 -> UserStatus.ACTIVE");

        // LOCKED状态
        ComplexUserInterface user3 = complexUserMapper.findByIdInterface(USER_ID_3);
        assertEquals(2, user3.getStatus());
        assertEquals(UserQuery.UserStatus.LOCKED, user3.getUserStatus());
        System.out.println("✓ status=2 -> UserStatus.LOCKED");

        System.out.println("说明: 多值枚举转换正常");
        System.out.println("========================================");
    }


    // ==================== JSON字符串测试 ====================

    @Test
    @Order(40)
    @DisplayName("40. 【JSON字符串】验证JSON字符串格式")
    void testJsonStringFormat() throws Exception {
        ComplexUserInterface user = complexUserMapper.findByIdInterface(USER_ID_1);

        System.out.println("========================================");
        System.out.println("【JSON字符串格式测试】:");

        // 验证tagsJson是有效的JSON数组字符串
        assertNotNull(user.getTagsJson());
        List<String> tags = objectMapper.readValue(user.getTagsJson(), new TypeReference<List<String>>() {});
        assertEquals(3, tags.size());
        System.out.println("✓ tagsJson是有效JSON: " + user.getTagsJson());

        // 验证roleIdsJson是有效的JSON数组字符串
        assertNotNull(user.getRoleIdsJson());
        List<Integer> roleIds = objectMapper.readValue(user.getRoleIdsJson(), new TypeReference<List<Integer>>() {});
        assertEquals(3, roleIds.size());
        System.out.println("✓ roleIdsJson是有效JSON: " + user.getRoleIdsJson());

        // 验证extrasJson是有效的JSON对象字符串
        assertNotNull(user.getExtrasJson());
        Map<String, Object> extras = objectMapper.readValue(user.getExtrasJson(), new TypeReference<Map<String, Object>>() {});
        assertEquals(3, extras.size());
        System.out.println("✓ extrasJson是有效JSON: " + user.getExtrasJson());

        System.out.println("说明: JSON字符串格式验证通过，可正确解析");
        System.out.println("========================================");
    }
}
