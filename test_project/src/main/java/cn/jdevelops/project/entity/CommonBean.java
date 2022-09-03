package cn.jdevelops.project.entity;

import cn.jdevelops.jpa.server.entity.mysql.JpaAuditFields;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

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
public class CommonBean<B> extends JpaAuditFields<B> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="int(11) COMMENT '主键，自动生成'")
    private Integer id;
}
