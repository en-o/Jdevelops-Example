package com.example.jdevelopsconfigdemo.test2jpa;

import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.jdevelops.data.jap.enums.SQLOperator;
import cn.jdevelops.data.jap.modle.JpaAuditFields;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户表
 *
 * @author : tn
 * @date : 2021-9-10
 */
@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "用户表")
@Getter
@Setter
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User extends JpaAuditFields<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    @Comment("主键，自动生成")
    private Long id;

    /**
     * 姓名
     */
    @Column(columnDefinition = " varchar(100)  not null ")
    @Comment("姓名")
    @JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR)
    private String name;

}
