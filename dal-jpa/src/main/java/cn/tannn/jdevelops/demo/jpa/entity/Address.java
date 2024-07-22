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
 * 地址
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:40
 */
@Entity
@Table(name = "sys_address")
@Comment("地址")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Address extends CommonBean<Address>{
    String code;
    String path;
}
