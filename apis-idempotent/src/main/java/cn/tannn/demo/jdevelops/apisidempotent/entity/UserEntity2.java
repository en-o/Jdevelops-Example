package cn.tannn.demo.jdevelops.apisidempotent.entity;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-11-17 16:35
 */
public class UserEntity2 {

    private String name;
    private Integer age;

    private Integer sex;
    private String nickName;
    private String remark;
    private String test;

    @Override
    public String toString() {
        return "UserEntity2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", nickName='" + nickName + '\'' +
                ", remark='" + remark + '\'' +
                ", test='" + test + '\'' +
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
