package cn.tannn.demo.renewpwd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 内置配置中心
 *
 * @author tan
 */
@Entity
@Table(name = "test_demo")
@Comment("测试")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class TestDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    @Comment("id")
    private Integer id;

    /**
     * name
     */
    @Comment("name")
    @Column(columnDefinition = "varchar(64) not null")
    private String name;

    @Override
    public String toString() {
        return "id=" + id +", name='" + name + '\'';
    }
}
