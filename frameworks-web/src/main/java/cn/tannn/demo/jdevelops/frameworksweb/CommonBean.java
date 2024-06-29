package cn.tannn.demo.jdevelops.frameworksweb;


import cn.tannn.jdevelops.jpa.modle.fn.JpaAuditFnFields;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.AccessType;
import javax.persistence.*;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Override
    public String toString() {
        return "CommonBean{" +
                "id=" + id +
                '}';
    }
}
