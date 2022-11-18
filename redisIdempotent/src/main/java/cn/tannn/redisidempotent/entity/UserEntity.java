package cn.tannn.redisidempotent.entity;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-11-17 16:35
 */
public class UserEntity {

    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
