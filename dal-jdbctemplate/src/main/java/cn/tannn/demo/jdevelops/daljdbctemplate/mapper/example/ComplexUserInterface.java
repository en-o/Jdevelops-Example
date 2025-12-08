package cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 复杂用户 Interface DTO
 * 用于测试 interface getter 方法返回复杂属性类型
 * <p>
 * 测试场景：
 * 1. 基本类型：Long, String, Integer
 * 2. 简单枚举：UserPlatform
 * 3. 多值枚举：UserStatus
 * 4. List<String>：标签列表
 * 5. List<Integer>：角色ID列表
 * 6. Map<String, Object>：扩展属性
 * 7. 嵌套对象：UserDetail
 * 8. LocalDateTime：时间类型
 * 9. JSON字符串类型：tagsJson, roleIdsJson (数据库返回JSON字符串，框架自动转换)
 *
 * @author tnnn
 */
public interface ComplexUserInterface {

    /**
     * 主键ID
     */
    Long getId();

    /**
     * 用户名
     */
    String getUsername();

    /**
     * 邮箱
     */
    String getEmail();

    /**
     * 年龄
     */
    Integer getAge();

    /**
     * 状态值 (数据库原始值: 0, 1, 2, 9)
     */
    Integer getStatus();

    /**
     * 简单枚举 - 平台
     */
    UserQuery.UserPlatform getPlatform();

    /**
     * 多值枚举 - 用户状态 (需要根据 status 字段转换)
     */
    UserQuery.UserStatus getUserStatus();

    /**
     * List<String> - 标签列表 (直接映射JSON类型)
     * JSON格式: ["tag1", "tag2"]
     */
    List<String> getTags();

    /**
     * List<Integer> - 角色ID列表 (直接映射JSON类型)
     * JSON格式: [1, 2, 3]
     */
    @JsonProperty("role_ids")
    List<Integer> getRoleIds();

    /**
     * String - 标签列表JSON字符串 (数据库返回JSON字符串)
     * 测试场景：返回类型是String，数据库字段是JSON类型
     * 期望：返回原始JSON字符串 '["tag1", "tag2"]'
     */
    @JsonProperty("tags_json")
    String getTagsJson();

    /**
     * String - 角色ID列表JSON字符串 (数据库返回JSON字符串)
     * 测试场景：返回类型是String，数据库字段是JSON类型
     * 期望：返回原始JSON字符串 '[1, 2, 3]'
     */
    @JsonProperty("role_ids_json")
    String getRoleIdsJson();

    /**
     * Map<String, Object> - 扩展属性 (直接映射JSON类型)
     * JSON格式: {"key1": "value1", "key2": 123}
     */
    Map<String, Object> getExtras();

    /**
     * String - 扩展属性JSON字符串 (数据库返回JSON字符串)
     * 测试场景：返回类型是String，数据库字段是JSON类型
     * 期望：返回原始JSON字符串 '{"key1": "value1"}'
     */
    @JsonProperty("extras_json")
    String getExtrasJson();

    /**
     * 嵌套对象 - 用户详情 (直接映射JSON类型)
     * JSON格式: {"address": "xxx", "phone": "xxx", "avatar": "xxx", "hobbies": ["hobby1", "hobby2"]}
     */
    UserDetail getDetail();

    /**
     * String - 用户详情JSON字符串 (数据库返回JSON字符串)
     * 测试场景：返回类型是String，数据库字段是JSON类型
     * 期望：返回原始JSON字符串 '{"address": "xxx", ...}'
     */
    @JsonProperty("detail_json")
    String getDetailJson();

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    LocalDateTime getCreatedAt();

    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    LocalDateTime getUpdatedAt();

    /**
     * 用户详情嵌套接口
     */
    interface UserDetail {
        /**
         * 地址
         */
        String getAddress();

        /**
         * 电话
         */
        String getPhone();

        /**
         * 头像URL
         */
        String getAvatar();

        /**
         * 爱好列表
         */
        List<String> getHobbies();
    }
}
