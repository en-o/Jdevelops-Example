package com.example.jdevelopsdataddssdemo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 数据源管理
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/20 12:50
 */

@Entity
@Table(name = "dy_datasource")
@org.hibernate.annotations.Table(appliesTo = "dy_datasource", comment = "数据源管理")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class DyDatasource {

    /**
     * 数据源名称
     */
    @Comment("数据源名称")
    @Id
    @Column(columnDefinition = "varchar(50)  not null")
    String datasourceName;

    /**
     * 数据源url
     */
    @Comment("数据源url")
    @Column(columnDefinition = "text  not null")
    String datasourceUrl;

    /**
     * 数据源帐号
     */
    @Comment("数据源帐号")
    @Column(columnDefinition = "varchar(100)  not null")
    String datasourceUsername;

    /**
     * 数据源密码
     */
    @Comment("数据源密码")
    @Column(columnDefinition = "varchar(100)  not null")
    String datasourcePassword;

    /**
     * 备注
     */
    @Comment("备注")
    @Column(columnDefinition = "varchar(200)")
    String remark;

    /**
     * 数据库驱动
     */
    @Comment("数据库驱动")
    @Column(columnDefinition = "varchar(100)  not null")
    String driverClassName;

    /**
     * 是否启用[0:禁用 1:启用]
     */
    @Comment("是否启用[0:禁用 1:启用]")
    @Column(columnDefinition = "int default 1")
    Integer enable;
}
