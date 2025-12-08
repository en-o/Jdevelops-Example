# 复杂返回类型测试说明

## 📋 测试目标

测试 JdbcTemplate XML Mapper 框架对 **Interface** 和 **Record** 返回类型的支持，以及各种复杂属性类型的映射能力。

## 🗂️ 文件清单

### 1. 数据库相关
- **SQL脚本**: `src/main/resources/complex_user.sql`
  - 创建 `complex_user` 表
  - 包含JSON字段：tags, role_ids, extras, detail
  - 插入6条测试数据

### 2. 实体类
- **Interface DTO**: `ComplexUserInterface.java`
  - 使用 interface getter 方法定义属性
  - 包含嵌套 Interface: `UserDetail`

- **Record DTO**: `ComplexUserRecord.java`
  - 使用 Java 17 record 定义
  - 包含嵌套 Record: `UserDetail`

### 3. Mapper 接口和XML
- **Mapper接口**: `ComplexUserMapper.java`
  - 定义 Interface 和 Record 返回类型的方法

- **XML配置**: `ComplexUserMapper.xml`
  - 定义SQL查询和数据插入

### 4. 测试类
- **测试类**: `ComplexReturnType_Test.java`
  - 40个测试用例
  - 覆盖所有复杂类型场景

---

## 🧪 测试覆盖场景

### ✅ 基本类型
- Long (ID)
- String (username, email)
- Integer (age, status)
- LocalDateTime (created_at, updated_at)

### ✅ 枚举类型
1. **简单枚举**: `UserPlatform`
   - NONE, WEB, MOBILE, DESKTOP

2. **多值枚举**: `UserStatus`
   - 包含 code, name, description 字段
   - INACTIVE(0), ACTIVE(1), LOCKED(2), DELETED(9)
   - 需要根据数据库 status 字段转换

### ✅ 集合类型
1. **List<String>**: tags (标签列表)
   - JSON数组: `["developer", "java", "spring"]`

2. **List<Integer>**: roleIds (角色ID列表)
   - JSON数组: `[1, 2, 3]`

### ✅ Map类型
- **Map<String, Object>**: extras (扩展属性)
  - JSON对象: `{"level": 5, "vip": true, "score": 99.5}`

### ✅ 嵌套对象
- **UserDetail**: 用户详情
  - Interface 版本: `ComplexUserInterface.UserDetail`
  - Record 版本: `ComplexUserRecord.UserDetail`
  - 属性: address, phone, avatar, hobbies

### ✅ JSON字符串
测试场景：返回类型是 `String`，数据库字段是 JSON类型
- `tagsJson`: 返回原始JSON字符串 `'["tag1", "tag2"]'`
- `roleIdsJson`: 返回原始JSON字符串 `'[1, 2, 3]'`
- `extrasJson`: 返回原始JSON字符串 `'{"key": "value"}'`
- `detailJson`: 返回原始JSON字符串 `'{"address": "xxx"}'`

**期望行为**:
- 当返回类型是 `List` 或 `Map` 时，框架自动将JSON转换为对象
- 当返回类型是 `String` 时，框架返回原始JSON字符串

---

## 📊 测试用例列表

### 数据准备 (Order 1-2)
| Order | 测试名称 | 说明 |
|-------|---------|------|
| 01 | 清空表数据 | 准备测试环境 |
| 02 | 插入测试数据 | 插入3条不同场景的数据 |

### Interface 返回类型测试 (Order 10-13)
| Order | 测试名称 | 测试重点 |
|-------|---------|---------|
| 10 | 完整数据 | 所有类型正常映射 |
| 11 | 列表查询 | List<Interface> 返回 |
| 12 | NULL值处理 | extras字段为null |
| 13 | 空集合处理 | tags、roleIds为空数组 |

### Record 返回类型测试 (Order 20-23)
| Order | 测试名称 | 测试重点 |
|-------|---------|---------|
| 20 | 完整数据 | 所有类型正常映射 |
| 21 | 列表查询 | List<Record> 返回 |
| 22 | NULL值处理 | extras字段为null |
| 23 | 空集合处理 | tags、roleIds为空数组 |

### 枚举转换测试 (Order 30)
| Order | 测试名称 | 测试重点 |
|-------|---------|---------|
| 30 | 枚举转换 | status值到UserStatus枚举的转换 |

### JSON字符串测试 (Order 40)
| Order | 测试名称 | 测试重点 |
|-------|---------|---------|
| 40 | JSON字符串格式 | 验证JSON字符串可正确解析 |

---

## 🚀 运行步骤

### 1. 创建数据库表
```sql
-- 执行 complex_user.sql 文件
source src/main/resources/complex_user.sql;
```

### 2. 运行测试
```bash
# 运行所有测试
mvn test -Dtest=ComplexReturnType_Test

# 运行特定测试
mvn test -Dtest=ComplexReturnType_Test#testInterfaceFullData
```

### 3. 查看结果
测试会打印详细的验证信息，包括：
- ✓ 各类型映射是否正常
- 数据内容和格式
- 说明信息

---

## 🔍 关键验证点

### 1. Interface vs Record
- **Interface**: 使用 `getXxx()` 方法访问
- **Record**: 使用 `xxx()` 方法访问
- 两者应该支持相同的类型映射能力

### 2. JSON 自动转换
- JSON数组 → List<T>
- JSON对象 → Map<String, Object>
- JSON对象 → 嵌套对象 (Interface/Record)

### 3. JSON 字符串返回
- 当返回类型是 `String` 时，应返回原始JSON字符串
- 可以手动用 Jackson 解析验证

### 4. 枚举转换
- 简单枚举: 字符串直接转换
- 多值枚举: 需要根据 code 字段匹配

### 5. NULL 和空值处理
- NULL: 属性值为 null
- 空集合: List.size() == 0
- 空Map: Map.isEmpty() == true

---

## ⚠️ 注意事项

1. **数据库版本**: 需要 MySQL 5.7+ 支持 JSON 类型
2. **Java版本**: Record 需要 Java 17+
3. **JSON依赖**: 需要 Jackson 或其他JSON库
4. **字段映射**: 注意使用 `@JsonProperty` 处理下划线命名

---

## 📝 测试数据说明

### 用户1 (ID=1)
- 状态: ACTIVE (1)
- 平台: WEB
- 特点: 完整数据，所有字段都有值

### 用户2 (ID=2)
- 状态: ACTIVE (1)
- 平台: MOBILE
- 特点: extras 为 null

### 用户3 (ID=3)
- 状态: LOCKED (2)
- 平台: DESKTOP
- 特点: tags 和 roleIds 为空数组

---

## 🎯 预期结果

所有40个测试用例应该全部通过，验证：
- ✅ Interface 和 Record 都能正确映射所有类型
- ✅ JSON 自动转换功能正常
- ✅ JSON 字符串返回功能正常
- ✅ 枚举转换功能正常
- ✅ NULL 和空值处理正常
- ✅ 嵌套对象映射正常
