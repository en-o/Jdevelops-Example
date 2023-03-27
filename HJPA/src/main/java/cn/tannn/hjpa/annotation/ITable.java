//package cn.tannn.hjpa.annotation;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.springframework.core.annotation.AliasFor;
//
//import javax.persistence.Entity;
//import javax.persistence.Index;
//import javax.persistence.Table;
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
//public @interface ITable {
//
//    /**
//     * javax.persistence.Table
//     * 常用： 设置表名
//     */
//    @AliasFor(attribute =  "name", annotation = Table.class)
//    String name() default "";
//
//    /**
//     * javax.persistence.Table
//     * 不常用：设置库名
//     */
//    @AliasFor(attribute =  "schema", annotation = Table.class)
//    String schema() default "";
//
//    /**
//     * javax.persistence.Table
//     * 常用：设置索引
//     *  - @Index(name = "enName_index", columnList = "esIndexType,enName", unique = true),
//     */
//    @AliasFor(attribute =  "indexes", annotation = Table.class)
//    Index[] indexes() default {};
//
//
//    /**
//     * org.hibernate.annotations.Table
//     * 必须使用：表名
//     */
//    @AliasFor(attribute =  "appliesTo", annotation = org.hibernate.annotations.Table.class)
//    String appliesTo();
//
//
//    /**
//     * org.hibernate.annotations.Table
//     * 常用：设置标注释
//     */
//    @AliasFor(attribute =  "comment", annotation = org.hibernate.annotations.Table.class)
//    String comment() default "";
//
//
//}
