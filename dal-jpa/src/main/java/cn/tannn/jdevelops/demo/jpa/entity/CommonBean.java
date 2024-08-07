package cn.tannn.jdevelops.demo.jpa.entity;


import cn.tannn.jdevelops.jpa.modle.fn.JpaAuditFnFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;



/**
 * 公共的实体类
 * @author tn
 * @date 2021-01-21 14:20
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Access(AccessType.FIELD)
@Getter
@Setter
public class CommonBean<B> extends JpaAuditFnFields<B> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidCustomGenerator")
    @GenericGenerator(name = "uuidCustomGenerator", strategy = "cn.tannn.jdevelops.jpa.generator.UuidCustomGenerator")
    @Column(columnDefinition="bigint")
    @Comment("主键，自动生成")
    private Long id;

    @Override
    public String toString() {
        return "CommonBean{" +
                "id=" + id +
                '}';
    }
}
