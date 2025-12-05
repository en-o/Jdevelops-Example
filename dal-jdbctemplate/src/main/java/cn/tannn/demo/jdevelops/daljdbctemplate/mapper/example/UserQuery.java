package cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example;

import java.util.List;

/**
 * 用户查询参数（示例）
 *
 * @author tnnn
 */
public class UserQuery {

    private String username;
    private String email;
    private Integer status;
    private Integer minAge;
    private Integer maxAge;

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
}
