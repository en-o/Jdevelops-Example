package cn.tannn.demo.jdevelops.daljdbctemplate.mapper;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserMapperEntity;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.UserQuery;
import cn.tannn.jdevelops.annotations.jdbctemplate.xml.*;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest;
import cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult;

import java.util.List;

/**
 * 用户 Mapper 接口（示例）
 * <p>
 * 配合 XML Mapper 使用，提供类型安全的方法定义
 * <p>
 * 对应 XML 文件：resources/jmapper/UserMapper.xml
 * <p>
 * namespace: cn.tannn.jdevelops.jdectemplate.xmlmapper.example.UserMapper
 *
 * @author tnnn
 */
@XmlMapper(namespace = "cn.tannn.demo.jdevelops.daljdbctemplate.mapper.UserMapper")
public interface UserMapper {

    /**
     * 根据 ID 查询用户
     * <p>
     * 对应 XML 中的 &lt;select id="findById"&gt;
     *
     * @param query 查询参数（包含 id 字段）
     * @return 用户对象，如果不存在返回 null
     */
    @XmlSelect("findById")
    UserMapperEntity findById(UserQuery query);

    /**
     * 动态条件查询用户列表
     * <p>
     * 对应 XML 中的 &lt;select id="findUsers"&gt;
     * <p>
     * 支持的查询条件：
     * <ul>
     *     <li>username - 用户名（模糊匹配）</li>
     *     <li>email - 邮箱（精确匹配）</li>
     *     <li>status - 状态（精确匹配）</li>
     *     <li>minAge - 最小年龄</li>
     *     <li>maxAge - 最大年龄</li>
     * </ul>
     *
     * @param query 查询参数对象
     * @return 用户列表
     */
    @XmlSelect("findUsers")
    List<UserMapperEntity> findUsers(UserQuery query);

    /**
     * 根据 ID 列表批量查询用户
     * <p>
     * 对应 XML 中的 &lt;select id="findByIds"&gt;
     *
     * @param query 查询参数（包含 ids 列表）
     * @return 用户列表
     */
    @XmlSelect("findByIds")
    List<UserMapperEntity> findByIds(UserQuery query);

    /**
     * 插入用户
     * <p>
     * 对应 XML 中的 &lt;insert id="insertUser"&gt;
     *
     * @param user 用户对象
     * @return 自增ID
     */
    @XmlInsert("insertUser")
    Long insertUser(UserMapperEntity user);

    /**
     * 批量插入用户
     * <p>
     * 对应 XML 中的 &lt;insert id="batchInsert"&gt;
     *
     * @param insert
     * @return 影响的行数
     */
    @XmlInsert("batchInsert")
    int batchInsert(List<UserMapperEntity> insert);

    /**
     * 动态更新用户信息
     * <p>
     * 对应 XML 中的 &lt;update id="updateUser"&gt;
     * <p>
     * 只更新非 null 的字段
     *
     * @param user 用户对象（id 必须，其他字段可选）
     * @return 影响的行数
     */
    @XmlUpdate("updateUser")
    int updateUser(UserMapperEntity user);

    /**
     * 根据 ID 删除用户
     * <p>
     * 对应 XML 中的 &lt;delete id="deleteById"&gt;
     *
     * @param user 用户对象（只需要 id）
     * @return 影响的行数
     */
    @XmlDelete("deleteById")
    int deleteById(UserMapperEntity user);

    /**
     * 根据 ID 列表批量删除用户
     * <p>
     * 对应 XML 中的 &lt;delete id="deleteByIds"&gt;
     *
     * @param query 查询参数（包含 ids 列表）
     * @return 影响的行数
     */
    @XmlDelete("deleteByIds")
    int deleteByIds(UserQuery query);


    /**
     * 删除所有用户
     *
     * @return 影响的行数
     */
    @XmlDelete("deleteAll")
    int deleteAll();

    /**
     * 统计用户数量
     * <p>
     * 对应 XML 中的 &lt;select id="countUsers"&gt;
     *
     * @param query 查询参数（支持 status 条件）
     * @return 用户数量
     */
    @XmlSelect("countUsers")
    Integer countUsers(UserQuery query);

    /**
     * 分页查询用户
     * <p>
     * 对应 XML 中的 &lt;select id="findUsersPage"&gt;
     *
     * @param query 查询参数（包含 pageSize 和 offset）
     * @return 用户列表
     */
    @XmlSelect("findUsersPage")
    List<UserMapperEntity> findUsersPage(UserQuery query);

    /**
     * 高级查询 - 支持关键字搜索、状态列表、日期范围、自定义排序
     * <p>
     * 对应 XML 中的 &lt;select id="findUsersAdvanced"&gt;
     *
     * @param query 查询参数
     * @return 用户列表
     */
    @XmlSelect("findUsersAdvanced")
    List<UserMapperEntity> findUsersAdvanced(UserQuery query);

    /**
     * 分页查询用户(带总数统计)
     * <p>
     * 对应 XML 中的 &lt;select id="findUsersPageWithTotal"&gt;
     *
     * @param query 查询参数
     * @param pageRequest 分页参数
     * @return 用户列表
     */
    @XmlSelect("findUsersPageWithTotal")
    List<UserMapperEntity> findUsersPageWithTotal(UserQuery query, PageRequest pageRequest);

    /**
     * 统计符合条件的用户总数(用于分页)
     * <p>
     * 对应 XML 中的 &lt;select id="countUsersByCondition"&gt;
     *
     * @param query 查询参数
     * @return 总数
     */
    @XmlSelect("countUsersByCondition")
    Long countUsersByCondition(UserQuery query);

    /**
     * 【框架内置分页】分页查询用户
     * <p>
     * 使用 @XmlPageSelect 注解，框架自动处理分页逻辑
     * <p>
     * 只需要指定数据查询SQL和统计SQL，框架会自动：
     * <ul>
     *     <li>执行数据查询</li>
     *     <li>执行统计查询</li>
     *     <li>组装成 PageResult 返回</li>
     * </ul>
     *
     * @param query 查询参数
     * @param pageRequest 分页参数（框架提供的 cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageRequest）
     * @return 分页结果（框架提供的 cn.tannn.jdevelops.jdectemplate.xmlmapper.page.PageResult）
     */
    @XmlPageSelect(dataStatement = "findUsersPageAuto", countStatement = "countUsersByCondition")
    PageResult<UserMapperEntity> findUsersPageAuto(UserQuery query, PageRequest pageRequest);

    /**
     * 批量插入用户 - 通过 UserQuery 对象的 users 属性
     * <p>
     * 对应 XML 中的 &lt;insert id="batchInsertFromQuery"&gt;
     * <p>
     * 测试场景：当参数是对象，对象中包含 List&lt;Bean&gt; 属性时的处理方式
     * <p>
     * UserQuery.users 是 List&lt;UserMapperEntity&gt; 类型，
     * XML 中使用 collection="users" 访问该属性
     *
     * @param query 查询参数（包含 users 列表）
     * @return 影响的行数
     */
    @XmlInsert("batchInsertFromQuery")
    int batchInsertFromQuery(UserQuery query);

    // ========================================
    // 特殊符号处理测试方法
    // 演示如何在 XML 中正确处理 <、>、& 等特殊符号
    // ========================================

    /**
     * 测试1: 使用 XML 实体转义处理特殊符号
     * <p>
     * 演示如何使用 &lt;、&gt; 等实体转义符号
     *
     * @param query 查询参数（支持 minAge、maxAge、minAgeEqual、maxAgeEqual）
     * @return 用户列表
     */
    @XmlSelect("findUsersWithLessThan")
    List<UserMapperEntity> findUsersWithLessThan(UserQuery query);

    /**
     * 测试2: 使用 CDATA 区块处理特殊符号
     * <p>
     * 演示如何使用 &lt;![CDATA[ ... ]]&gt; 区块直接书写特殊符号
     *
     * @param query 查询参数（支持 minAge、maxAge、minAgeEqual、maxAgeEqual）
     * @return 用户列表
     */
    @XmlSelect("findUsersWithCDATA")
    List<UserMapperEntity> findUsersWithCDATA(UserQuery query);

    /**
     * 测试3: BETWEEN 范围查询
     * <p>
     * 演示 BETWEEN ... AND ... 语法的使用
     *
     * @param query 查询参数（支持 minAge、maxAge、status）
     * @return 用户列表
     */
    @XmlSelect("findUsersBetweenAge")
    List<UserMapperEntity> findUsersBetweenAge(UserQuery query);

    /**
     * 测试4: BETWEEN 日期范围查询（使用 CDATA）
     * <p>
     * 演示日期范围查询和 CDATA 的结合使用
     *
     * @param query 查询参数（支持 startDate、endDate）
     * @return 用户列表
     */
    @XmlSelect("findUsersBetweenDate")
    List<UserMapperEntity> findUsersBetweenDate(UserQuery query);

    /**
     * 测试5: 复杂条件组合（AND + OR + NOT）
     * <p>
     * 演示多种条件的组合使用
     *
     * @param query 查询参数（支持 minAge、maxAge、status1、status2、excludeUsername、excludeIds）
     * @return 用户列表
     */
    @XmlSelect("findUsersComplexConditions")
    List<UserMapperEntity> findUsersComplexConditions(UserQuery query);

    /**
     * 测试6: LIKE 模糊查询（多种方式）
     * <p>
     * 演示 LIKE 的多种使用方式：前缀匹配、全模糊匹配、后缀匹配
     *
     * @param query 查询参数（支持 usernamePrefix、usernameLike、emailSuffix）
     * @return 用户列表
     */
    @XmlSelect("findUsersWithLike")
    List<UserMapperEntity> findUsersWithLike(UserQuery query);

    /**
     * 测试7: IN 查询（多种数据类型）
     * <p>
     * 演示 IN 条件对不同类型字段的使用
     *
     * @param query 查询参数（支持 ids、statusList、usernames）
     * @return 用户列表
     */
    @XmlSelect("findUsersInConditions")
    List<UserMapperEntity> findUsersInConditions(UserQuery query);

    /**
     * 测试8: NULL 值处理
     * <p>
     * 演示 IS NULL、IS NOT NULL、COALESCE 等 NULL 值处理方式
     *
     * @param query 查询参数（支持 emailIsNull、emailIsNotNull、defaultAge）
     * @return 用户列表
     */
    @XmlSelect("findUsersWithNull")
    List<UserMapperEntity> findUsersWithNull(UserQuery query);

    /**
     * 测试9: 混合使用实体转义和 CDATA
     * <p>
     * 演示在同一个查询中混合使用两种特殊符号处理方式
     *
     * @param query 查询参数（支持 status、minAge、maxAge、keyword）
     * @return 用户列表
     */
    @XmlSelect("findUsersMixedSpecialChars")
    List<UserMapperEntity> findUsersMixedSpecialChars(UserQuery query);

    /**
     * 测试10: 数学运算和比较
     * <p>
     * 演示在 SQL 中使用数学运算
     *
     * @param query 查询参数（支持 ageMultiplier、ageBase、agePercent）
     * @return 用户列表
     */
    @XmlSelect("findUsersWithMath")
    List<UserMapperEntity> findUsersWithMath(UserQuery query);

    // ========================================
    // 枚举和 Record 类型方法调用测试
    // 测试修复：支持枚举的 name()、ordinal() 等方法
    // ========================================

    /**
     * 测试11: 枚举 name() 方法调用
     * <p>
     * 演示在 XML 中调用枚举的 name() 方法
     * <p>
     * XML 中的条件：platform != null and platform.name() != 'NONE'
     *
     * @param query 查询参数（包含 platform 枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByPlatform")
    List<UserMapperEntity> findUsersByPlatform(UserQuery query);

    /**
     * 测试12: 枚举 ordinal() 方法调用
     * <p>
     * 演示在 XML 中调用枚举的 ordinal() 方法
     * <p>
     * XML 中的条件：platform != null and platform.ordinal() > 0
     *
     * @param query 查询参数（包含 platform 枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByPlatformOrdinal")
    List<UserMapperEntity> findUsersByPlatformOrdinal(UserQuery query);

    /**
     * 测试13: 多参数形式的枚举 name() 方法调用 (arg0.platform.name())
     * <p>
     * 演示在 XML 中使用 arg0 形式调用枚举的 name() 方法
     * <p>
     * XML 中的条件：arg0.platform != null and arg0.platform.name() != 'NONE'
     *
     * @param query 查询参数（包含 platform 枚举字段）
     * @param limit 查询限制数量
     * @return 用户列表
     */
    @XmlSelect("findUsersByPlatformWithArg0")
    List<UserMapperEntity> findUsersByPlatformWithArg0(UserQuery query, Integer limit);

    // ========================================
    // 多值枚举测试
    // 测试修复：支持枚举的 getCode()、getName()、getDescription() 等方法
    // ========================================

    /**
     * 测试14: 多值枚举 getCode() 方法调用
     * <p>
     * 演示在 XML 中调用多值枚举的 getCode() 方法
     * <p>
     * XML 中的条件：userStatus != null and userStatus.getCode() == 1
     *
     * @param query 查询参数（包含 userStatus 多值枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByUserStatusCode")
    List<UserMapperEntity> findUsersByUserStatusCode(UserQuery query);

    /**
     * 测试15: 多值枚举 getName() 方法调用
     * <p>
     * 演示在 XML 中调用多值枚举的 getName() 方法
     * <p>
     * XML 中的条件：userStatus != null and userStatus.getName() == '已激活'
     *
     * @param query 查询参数（包含 userStatus 多值枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByUserStatusName")
    List<UserMapperEntity> findUsersByUserStatusName(UserQuery query);

    /**
     * 测试16: 多值枚举 getDescription() 方法调用
     * <p>
     * 演示在 XML 中调用多值枚举的 getDescription() 方法
     * <p>
     * XML 中的条件：userStatus != null and userStatus.getDescription() != null
     *
     * @param query 查询参数（包含 userStatus 多值枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByUserStatusDescription")
    List<UserMapperEntity> findUsersByUserStatusDescription(UserQuery query);

    /**
     * 测试17: 多值枚举复杂条件组合
     * <p>
     * 演示在 XML 中组合使用多值枚举的多个方法
     * <p>
     * 组合条件：
     * <ul>
     *     <li>userStatus.getCode() > 0</li>
     *     <li>userStatus.name() != 'DELETED'</li>
     *     <li>userStatus.getName() != null</li>
     * </ul>
     *
     * @param query 查询参数（包含 userStatus 多值枚举字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByUserStatusComplex")
    List<UserMapperEntity> findUsersByUserStatusComplex(UserQuery query);

    /**
     * 测试18: 多参数形式的多值枚举方法调用 (arg0.userStatus.getCode())
     * <p>
     * 演示在 XML 中使用 arg0 形式调用多值枚举的方法
     * <p>
     * XML 中的条件：
     * <ul>
     *     <li>arg0.userStatus.getCode() == 1</li>
     *     <li>arg0.userStatus.getName() == '已激活'</li>
     * </ul>
     *
     * @param query 查询参数（包含 userStatus 多值枚举字段）
     * @param limit 查询限制数量
     * @return 用户列表
     */
    @XmlSelect("findUsersByUserStatusWithArg0")
    List<UserMapperEntity> findUsersByUserStatusWithArg0(UserQuery query, Integer limit);

    // ========================================
    // Choose/When/Otherwise 测试
    // 测试新增的 choose 标签支持
    // ========================================

    /**
     * 测试19: choose/when/otherwise - orgNo 判断
     * <p>
     * 演示 choose 标签的基本用法：
     * <ul>
     *     <li>当 orgNo == '-' 时，使用 IS NULL 条件</li>
     *     <li>其他情况使用 = #{orgNo} 条件</li>
     * </ul>
     * <p>
     * 这是一个典型的业务场景：特殊值 "-" 代表查询组织编号为空的数据
     *
     * @param query 查询参数（包含 orgNo 字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByOrgNoWithChoose")
    List<UserMapperEntity> findUsersByOrgNoWithChoose(UserQuery query);

    /**
     * 测试20: choose/when/otherwise - 年龄级别分类查询
     * <p>
     * 演示 choose 标签的多分支用法：
     * <ul>
     *     <li>当 ageLevel == 'YOUNG' 时，查询 18-25 岁用户</li>
     *     <li>当 ageLevel == 'MIDDLE' 时，查询 26-40 岁用户</li>
     *     <li>当 ageLevel == 'SENIOR' 时，查询 41 岁以上用户</li>
     *     <li>otherwise：查询所有用户（不限年龄）</li>
     * </ul>
     * <p>
     * 演示了 choose 类似 switch-case 的特性：只会执行第一个匹配的分支
     *
     * @param query 查询参数（包含 ageLevel 字段）
     * @return 用户列表
     */
    @XmlSelect("findUsersByAgeLevelWithChoose")
    List<UserMapperEntity> findUsersByAgeLevelWithChoose(UserQuery query);
}
