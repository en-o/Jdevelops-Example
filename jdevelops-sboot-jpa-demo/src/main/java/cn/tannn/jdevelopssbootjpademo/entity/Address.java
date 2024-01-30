package cn.tannn.jdevelopssbootjpademo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地址
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:40
 */
@Entity
@Table(name = "sys_address")
@org.hibernate.annotations.Table(appliesTo = "sys_address", comment = "地址")
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
