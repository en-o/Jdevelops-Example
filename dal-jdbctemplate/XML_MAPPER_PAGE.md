# XML Mapper 分页查询完整指南

本文档详细介绍如何使用 XML Mapper 实现完整的分页功能。

---

## 📦 分页组件

### 1. PageRequest - 分页请求参数

```java
public class PageRequest {
    private Integer pageNum = 1;      // 当前页码(从1开始)
    private Integer pageSize = 10;    // 每页大小
    private String orderBy;           // 排序字段
    private String orderDir = "DESC"; // 排序方向: ASC/DESC

    // 计算偏移量
    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }

    // 获取完整的排序SQL
    public String getOrderBySql() {
        if (orderBy != null && !orderBy.trim().isEmpty()) {
            String dir = "DESC".equalsIgnoreCase(orderDir) ? "DESC" : "ASC";
            return orderBy + " " + dir;
        }
        return null;
    }
}
```

### 2. PageResult - 分页响应结果

```java
public class PageResult<T> {
    private Integer pageNum;    // 当前页码
    private Integer pageSize;   // 每页大小
    private Long total;         // 总记录数
    private Integer pages;      // 总页数
    private List<T> list;       // 数据列表
    private Boolean hasNext;    // 是否有下一页
    private Boolean hasPrevious; // 是否有上一页

    public PageResult(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.pages = (int) Math.ceil((double) total / pageSize);
        this.hasNext = pageNum < this.pages;
        this.hasPrevious = pageNum > 1;
    }
}
```

---

## 📝 XML Mapper 配置

### UserMapper.xml

```xml
<!-- 分页查询 SQL 片段(通用查询条件 - 单参数) -->
<sql id="pageWhereConditions">
    <where>
        <if test="username != null and username != ''">
            AND username LIKE CONCAT('%', #{username}, '%')
        </if>
        <if test="email != null and email != ''">
            AND email = #{email}
        </if>
        <if test="status != null">
            AND status = #{status}
        </if>
        <if test="minAge != null">
            AND age >= #{minAge}
        </if>
        <if test="maxAge != null">
            AND age &lt;= #{maxAge}
        </if>
    </where>
</sql>

<!-- 分页查询用户(带条件) - 注意多参数需要使用 arg0/arg1 -->
<select id="findUsersPageWithTotal" resultType="UserMapperEntity">
    SELECT
        <include refid="baseColumns"/>
    FROM users_mapper
    <where>
        <if test="arg0.username != null and arg0.username != ''">
            AND username LIKE CONCAT('%', #{arg0.username}, '%')
        </if>
        <if test="arg0.email != null and arg0.email != ''">
            AND email = #{arg0.email}
        </if>
        <if test="arg0.status != null">
            AND status = #{arg0.status}
        </if>
        <if test="arg0.minAge != null">
            AND age >= #{arg0.minAge}
        </if>
        <if test="arg0.maxAge != null">
            AND age &lt;= #{arg0.maxAge}
        </if>
    </where>
    ORDER BY
    <if test="arg1.orderBySql != null and arg1.orderBySql != ''">
        ${arg1.orderBySql}
    </if>
    <if test="arg1.orderBySql == null or arg1.orderBySql == ''">
        created_at DESC
    </if>
    LIMIT #{arg1.pageSize} OFFSET #{arg1.offset}
</select>

<!-- 统计符合条件的用户总数(单参数) -->
<select id="countUsersByCondition" resultType="java.lang.Long">
    SELECT COUNT(*)
    FROM users_mapper
    <include refid="pageWhereConditions"/>
</select>
```

**注意事项:**
- 多参数方法中,使用 `arg0`、`arg1` 访问参数(第一个参数 query 是 arg0,第二个参数 pageRequest 是 arg1)
- **重要**: `findUsersPageWithTotal` 是双参数方法,必须使用 `arg0.status`、`arg1.pageSize` 这样的形式访问属性
- `countUsersByCondition` 是单参数方法,可以直接使用 `status` 访问属性,因此可以复用 `pageWhereConditions` 片段
- `${arg1.orderBySql}` 使用 `${}` 而不是 `#{}`,因为排序字段需要直接拼接
- 为保证条件一致,分页查询和统计查询的 WHERE 条件逻辑应该相同

---

## 🔧 Mapper 接口

### UserMapper.java

```java
@XmlMapper(namespace = "com.example.mapper.UserMapper")
public interface UserMapper {

    /**
     * 分页查询用户(带总数统计)
     */
    @XmlSelect("findUsersPageWithTotal")
    List<UserMapperEntity> findUsersPageWithTotal(UserQuery query, PageRequest pageRequest);

    /**
     * 统计符合条件的用户总数(用于分页)
     */
    @XmlSelect("countUsersByCondition")
    Long countUsersByCondition(UserQuery query);
}
```

---

## 💻 使用示例

### 1. 基础分页查询

```java
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询用户
     */
    public PageResult<UserMapperEntity> getUsersPage(int pageNum, int pageSize) {
        // 1. 创建分页参数
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        // 2. 创建查询条件
        UserQuery query = new UserQuery();

        // 3. 查询数据列表
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);

        // 4. 查询总数
        Long total = userMapper.countUsersByCondition(query);

        // 5. 构建分页结果
        return new PageResult<>(pageNum, pageSize, total, list);
    }
}
```

### 2. 带条件的分页查询

```java
public PageResult<UserMapperEntity> searchUsers(String username, Integer status,
                                                 int pageNum, int pageSize) {
    // 创建分页参数
    PageRequest pageRequest = new PageRequest(pageNum, pageSize);
    pageRequest.setOrderBy("created_at");
    pageRequest.setOrderDir("DESC");

    // 创建查询条件
    UserQuery query = new UserQuery();
    query.setUsername(username);
    query.setStatus(status);

    // 查询数据
    List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
    Long total = userMapper.countUsersByCondition(query);

    return new PageResult<>(pageNum, pageSize, total, list);
}
```

### 3. 带排序的分页查询

```java
public PageResult<UserMapperEntity> getUsersPageSorted(int pageNum, int pageSize,
                                                        String sortField, String sortDir) {
    // 创建分页参数
    PageRequest pageRequest = new PageRequest(pageNum, pageSize);
    pageRequest.setOrderBy(sortField);  // 如: "age", "created_at"
    pageRequest.setOrderDir(sortDir);   // "ASC" 或 "DESC"

    // 查询数据
    UserQuery query = new UserQuery();
    List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
    Long total = userMapper.countUsersByCondition(query);

    return new PageResult<>(pageNum, pageSize, total, list);
}
```

### 4. 复杂条件分页查询

```java
public PageResult<UserMapperEntity> advancedSearch(String username, Integer status,
                                                    Integer minAge, Integer maxAge,
                                                    int pageNum, int pageSize) {
    // 创建分页参数
    PageRequest pageRequest = new PageRequest(pageNum, pageSize);
    pageRequest.setOrderBy("age");
    pageRequest.setOrderDir("ASC");

    // 创建查询条件
    UserQuery query = new UserQuery();
    query.setUsername("%" + username + "%");  // 模糊查询
    query.setStatus(status);
    query.setMinAge(minAge);
    query.setMaxAge(maxAge);

    // 查询数据
    List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
    Long total = userMapper.countUsersByCondition(query);

    return new PageResult<>(pageNum, pageSize, total, list);
}
```

---

## 🌐 Controller 示例

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    public PageResult<UserMapperEntity> getUsersPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false, defaultValue = "DESC") String sortDir) {

        // 创建分页参数
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        if (sortField != null) {
            pageRequest.setOrderBy(sortField);
            pageRequest.setOrderDir(sortDir);
        }

        // 创建查询条件
        UserQuery query = new UserQuery();
        query.setUsername(username);
        query.setStatus(status);

        // 查询数据
        List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
        Long total = userMapper.countUsersByCondition(query);

        return new PageResult<>(pageNum, pageSize, total, list);
    }
}
```

**请求示例:**
```
GET /api/users/page?pageNum=1&pageSize=10&status=1&sortField=age&sortDir=ASC
```

**响应示例:**
```json
{
  "pageNum": 1,
  "pageSize": 10,
  "total": 100,
  "pages": 10,
  "hasNext": true,
  "hasPrevious": false,
  "list": [
    {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com",
      "age": 25,
      "status": 1
    }
  ]
}
```

---

## 🧪 测试示例

```java
@Test
void testPageQuery() {
    // 创建分页参数
    PageRequest pageRequest = new PageRequest(1, 5);

    // 创建查询条件
    UserQuery query = new UserQuery();
    query.setStatus(1);

    // 查询数据
    List<UserMapperEntity> list = userMapper.findUsersPageWithTotal(query, pageRequest);
    Long total = userMapper.countUsersByCondition(query);

    // 构建分页结果
    PageResult<UserMapperEntity> result = new PageResult<>(
            pageRequest.getPageNum(),
            pageRequest.getPageSize(),
            total,
            list
    );

    // 验证结果
    assertNotNull(result.getList());
    assertTrue(result.getList().size() <= 5);
    assertEquals(1, result.getPageNum());
    System.out.println("分页结果: " + result);
}
```

---

## ⚠️ 注意事项

### 1. 参数访问方式

**多参数方法中的参数访问:**
- 第一个参数: `arg0` 或参数名(如果启用了参数名保留)
- 第二个参数: `arg1`

**示例:**
```xml
<!-- query 是第一个参数,pageRequest 是第二个参数 -->
<select id="findUsersPageWithTotal">
    WHERE status = #{arg0.status}   <!-- 或 #{query.status} -->
    LIMIT #{arg1.pageSize}          <!-- 或 #{pageRequest.pageSize} -->
</select>
```

### 2. 排序字段安全性

**使用 `${}` 拼接排序字段时要注意 SQL 注入风险:**

```java
// 好的做法: 限制可排序的字段
public PageResult<User> getUsersPage(String sortField) {
    // 白名单验证
    List<String> allowedFields = Arrays.asList("id", "username", "age", "created_at");
    if (!allowedFields.contains(sortField)) {
        sortField = "created_at";  // 默认排序字段
    }

    PageRequest pageRequest = new PageRequest();
    pageRequest.setOrderBy(sortField);
    // ...
}
```

### 3. 性能优化

**对于大数据量查询:**

1. 添加索引:
```sql
CREATE INDEX idx_status ON users(status);
CREATE INDEX idx_age ON users(age);
CREATE INDEX idx_created_at ON users(created_at);
```

2. 避免深分页:
```java
// 限制最大页码
if (pageNum > 100) {
    pageNum = 100;
}
```

3. 使用游标分页(对于超大数据量):
```sql
<!-- 使用 ID 游标而不是 OFFSET -->
SELECT * FROM users
WHERE id > #{lastId}
ORDER BY id
LIMIT #{pageSize}
```

---

## 📚 完整示例

完整的测试示例请查看:
- [XmlMapper_annotation_Test.java](./src/test/java/.../XmlMapper_annotation_Test.java) - 包含完整的分页测试用例
- [XmlMapper_registry_Test.java](./src/test/java/.../XmlMapper_registry_Test.java) - Registry 方式的分页测试
- [UserMapper.xml](./src/main/resources/jmapper/UserMapper.xml) - 完整的分页 SQL 配置

---

## 🎯 总结

使用 XML Mapper 实现分页功能的步骤:

1. ✅ 创建 `PageRequest` 和 `PageResult` 类
2. ✅ 在 XML 中定义分页查询和统计查询
3. ✅ 在 Mapper 接口中定义方法
4. ✅ 在 Service 层组合查询结果并构建 `PageResult`
5. ✅ 注意参数访问方式和 SQL 注入防护

**优势:**
- ✅ SQL 可见,易于优化
- ✅ 支持复杂的动态条件
- ✅ 统一的查询条件复用
- ✅ 类型安全的接口定义
