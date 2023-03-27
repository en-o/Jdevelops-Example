//package cn.tannn.hjpa.annotation;
//
//import org.hibernate.annotations.*;
//import org.springframework.core.annotation.AliasFor;
//
//import javax.persistence.Entity;
//import javax.persistence.Index;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import java.lang.annotation.Target;
//import java.lang.annotation.*;
//
///**
// * 合并实体类常用注解
// * @author tnnn
// * @date 2022-07-13 11:24:38
// */
//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//@Entity
//@Table
//@org.hibernate.annotations.Table(appliesTo = "")
//@DynamicUpdate
//@DynamicInsert
//public @interface ITable2 {
//
//    /**
//     * Entity
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "name", annotation = Entity.class)
//    String ename() default "";
//
//
//    /**
//     * javax.persistence.Table
//     * 常用： 设置表名
//     */
//    @AliasFor(attribute =  "name", annotation = Table.class)
//    String xname() default "";
//
//    /**
//     * javax.persistence.Table
//     *  不常用
//     */
//    @AliasFor(attribute =  "catalog", annotation = Table.class)
//    String xcatalog() default "";
//
//    /**
//     * javax.persistence.Table
//     * 不常用：设置库名
//     */
//    @AliasFor(attribute =  "schema", annotation = Table.class)
//    String xschema() default "";
//
//    /**
//     * javax.persistence.Table
//     * 不常用：设置索引
//     */
//    @AliasFor(attribute =  "uniqueConstraints", annotation = Table.class)
//    UniqueConstraint[] xuniqueConstraints() default {};
//
//    /**
//     * javax.persistence.Table
//     * 常用：设置索引
//     *  - @Index(name = "enName_index", columnList = "esIndexType,enName", unique = true),
//     */
//    @AliasFor(attribute =  "indexes", annotation = Table.class)
//    Index[] xindexes() default {};
//
//    /**
//     * DynamicInsert
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "value", annotation = DynamicInsert.class)
//    boolean diValue() default true;
//
//    /**
//     * DynamicUpdate
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "value", annotation = DynamicUpdate.class)
//    boolean duValue() default true;
//
//    /**
//     * org.hibernate.annotations.Table
//     * 必须使用：表名
//     */
//    @AliasFor(attribute =  "appliesTo", annotation = org.hibernate.annotations.Table.class)
//    String happliesTo();
//
//    /**
//     * org.hibernate.annotations.Table
//     * 不常用
//     */
//    @AliasFor(attribute =  "indexes", annotation = org.hibernate.annotations.Table.class)
//    org.hibernate.annotations.Index[] hindexes() default {};
//
//    /**
//     * org.hibernate.annotations.Table
//     * 常用：设置标注释
//     */
//    @AliasFor(attribute =  "comment", annotation = org.hibernate.annotations.Table.class)
//    String hcomment() default "";
//
//    /**
//     * org.hibernate.annotations.Table
//     * 不常用
//     */
//    @AliasFor(attribute =  "foreignKey", annotation = org.hibernate.annotations.Table.class)
//    ForeignKey hforeignKey() default @ForeignKey(
//            name = ""
//    );
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "fetch", annotation = org.hibernate.annotations.Table.class)
//    FetchMode hfetch() default FetchMode.JOIN;
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "inverse", annotation = org.hibernate.annotations.Table.class)
//    boolean hinverse() default false;
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "optional", annotation = org.hibernate.annotations.Table.class)
//    boolean hoptional() default true;
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "sqlInsert", annotation = org.hibernate.annotations.Table.class)
//    SQLInsert hsqlInsert() default @SQLInsert(
//            sql = ""
//    );
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "sqlUpdate", annotation = org.hibernate.annotations.Table.class)
//    SQLUpdate hsqlUpdate() default @SQLUpdate(
//            sql = ""
//    );
//
//    /**
//     * org.hibernate.annotations.Table
//     * 几乎不使用
//     */
//    @AliasFor(attribute =  "sqlDelete", annotation = org.hibernate.annotations.Table.class)
//    SQLDelete hsqlDelete() default @SQLDelete(
//            sql = ""
//    );
//
//
//
//
//
//}
