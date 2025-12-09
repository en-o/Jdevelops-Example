package cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example;

import java.util.List;

/**
 * 用户查询参数（示例）
 *
 * @author tnnn
 */
public class UserQuery {

    /**
     * 用户平台枚举
     */
    public enum UserPlatform {
        NONE,    // 无平台
        WEB,     // 网页端
        MOBILE,  // 移动端
        DESKTOP  // 桌面端
    }

    /**
     * 用户状态枚举（多值枚举 - 带有code和description字段）
     */
    public enum UserStatus {
        INACTIVE(0, "未激活", "用户账号未激活"),
        ACTIVE(1, "已激活", "用户账号正常"),
        LOCKED(2, "已锁定", "用户账号被锁定"),
        DELETED(9, "已删除", "用户账号已删除");

        private final int code;
        private final String name;
        private final String description;

        UserStatus(int code, String name, String description) {
            this.code = code;
            this.name = name;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    private String username;
    private String email;
    private Integer status;
    private Integer minAge;
    private Integer maxAge;

    // 枚举测试字段
    private UserPlatform platform;
    // 多值枚举测试字段
    private UserStatus userStatus;

    // 高级查询参数
    private String keyword;
    private List<Integer> statusList;
    private String startDate;
    private String endDate;
    private String orderBy;

    // 分页参数
    private Integer pageSize;
    private Integer offset;

    // ID 列表
    private List<Long> ids;

    // 单个 ID（用于 findById）
    private Long id;

    // 批量插入
    private List<UserMapperEntity> userMapperEntities;

    // ========================================
    // 特殊符号处理测试字段
    // ========================================

    // 测试1 & 2: 实体转义和CDATA测试
    private Integer maxAgeEqual;      // 最大年龄（包含等于）
    private Integer minAgeEqual;      // 最小年龄（包含等于）

    // 测试5: 复杂条件测试
    private Integer status1;          // 状态1
    private Integer status2;          // 状态2
    private String excludeUsername;   // 排除的用户名
    private List<Long> excludeIds;    // 排除的ID列表

    // 测试6: LIKE测试
    private String usernamePrefix;    // 用户名前缀
    private String usernameLike;      // 用户名模糊匹配
    private String emailSuffix;       // 邮箱后缀

    // 测试7: IN测试
    private List<String> usernames;   // 用户名列表

    // 测试8: NULL值处理
    private Boolean emailIsNull;      // 邮箱是否为NULL
    private Boolean emailIsNotNull;   // 邮箱是否不为NULL
    private Integer defaultAge;       // 默认年龄（用于COALESCE）

    // 测试10: 数学运算
    private Integer ageMultiplier;    // 年龄乘数
    private Integer ageBase;          // 年龄基数
    private Integer agePercent;       // 年龄百分比

    // choose/when/otherwise 测试
    private String orgNo;             // 组织编号（测试 choose/when/otherwise）
    private String ageLevel;          // 年龄级别（测试 choose 多分支）

    public UserQuery() {
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserMapperEntity> getUsers() {
        return userMapperEntities;
    }

    public void setUsers(List<UserMapperEntity> userMapperEntities) {
        this.userMapperEntities = userMapperEntities;
    }

    // ========================================
    // 特殊符号处理测试字段的 Getters and Setters
    // ========================================

    public Integer getMaxAgeEqual() {
        return maxAgeEqual;
    }

    public void setMaxAgeEqual(Integer maxAgeEqual) {
        this.maxAgeEqual = maxAgeEqual;
    }

    public Integer getMinAgeEqual() {
        return minAgeEqual;
    }

    public void setMinAgeEqual(Integer minAgeEqual) {
        this.minAgeEqual = minAgeEqual;
    }

    public Integer getStatus1() {
        return status1;
    }

    public void setStatus1(Integer status1) {
        this.status1 = status1;
    }

    public Integer getStatus2() {
        return status2;
    }

    public void setStatus2(Integer status2) {
        this.status2 = status2;
    }

    public String getExcludeUsername() {
        return excludeUsername;
    }

    public void setExcludeUsername(String excludeUsername) {
        this.excludeUsername = excludeUsername;
    }

    public List<Long> getExcludeIds() {
        return excludeIds;
    }

    public void setExcludeIds(List<Long> excludeIds) {
        this.excludeIds = excludeIds;
    }

    public String getUsernamePrefix() {
        return usernamePrefix;
    }

    public void setUsernamePrefix(String usernamePrefix) {
        this.usernamePrefix = usernamePrefix;
    }

    public String getUsernameLike() {
        return usernameLike;
    }

    public void setUsernameLike(String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public String getEmailSuffix() {
        return emailSuffix;
    }

    public void setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public Boolean getEmailIsNull() {
        return emailIsNull;
    }

    public void setEmailIsNull(Boolean emailIsNull) {
        this.emailIsNull = emailIsNull;
    }

    public Boolean getEmailIsNotNull() {
        return emailIsNotNull;
    }

    public void setEmailIsNotNull(Boolean emailIsNotNull) {
        this.emailIsNotNull = emailIsNotNull;
    }

    public Integer getDefaultAge() {
        return defaultAge;
    }

    public void setDefaultAge(Integer defaultAge) {
        this.defaultAge = defaultAge;
    }

    public Integer getAgeMultiplier() {
        return ageMultiplier;
    }

    public void setAgeMultiplier(Integer ageMultiplier) {
        this.ageMultiplier = ageMultiplier;
    }

    public Integer getAgeBase() {
        return ageBase;
    }

    public void setAgeBase(Integer ageBase) {
        this.ageBase = ageBase;
    }

    public Integer getAgePercent() {
        return agePercent;
    }

    public void setAgePercent(Integer agePercent) {
        this.agePercent = agePercent;
    }

    public UserPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(UserPlatform platform) {
        this.platform = platform;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getAgeLevel() {
        return ageLevel;
    }

    public void setAgeLevel(String ageLevel) {
        this.ageLevel = ageLevel;
    }
}
