package cn.tannn.jdevelops.demo.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 测试建表的字段顺序
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:40
 */
@Entity
@Table(name = "test_create_table_order")
@Comment("测试建表的字段顺序")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class CraterTableTest extends CommonBean<CraterTableTest> {
    private String b;
    private String c;
    private String a;
    private String d;
}
