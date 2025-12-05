package cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example;

import java.time.LocalDateTime;

/**
 * 用户实体类（示例）
 *
 * @author tnnn
 */
public class UserMapperEntity {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private Integer status;
    private LocalDateTime createdAt;

    public UserMapperEntity() {
    }

    public UserMapperEntity(Long id, String username, String email, Integer age, Integer status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserMapperEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
