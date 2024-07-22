package cn.tannn.jdevelops.demo.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * ExpressionRootDemo
 * @author tan
 */
@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "test_expression_root")
public class ExpressionRootDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 昵称
     */
    @Column(columnDefinition = " varchar(100) ")
    @Comment("昵称")
    private String nickName;

    @Column(columnDefinition = "int default 0 ")
    @Comment("性别[0:未知，1:男，2:女]")
    private Integer gender;

    public ExpressionRootDemo(String nickName, Integer gender) {
        this.nickName = nickName;
        this.gender = gender;
    }

    public ExpressionRootDemo(Long id, String nickName, Integer gender) {
        this.id = id;
        this.nickName = nickName;
        this.gender = gender;
    }

    public ExpressionRootDemo() {
    }

    @Override
    public String toString() {
        return "ExpressionRootDemo{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
