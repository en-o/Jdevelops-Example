//package cn.tannn.hjpa.entity;
//
//import cn.tannn.hjpa.ITable;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//
///**
// * 测试注解
// *
// * @author tnnn
// * @version V1.0
// * @date 2022-07-13 11:46
// */
//@ITable(appliesTo = "db_test",name = "db_test", indexes = {
//        @Index(name = "userNo_index", columnList = "userNo", unique = true),
//},comment = "测试合并注解")
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//public class TestBean {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "int(11) COMMENT '主键，自动生成'")
//    private Integer id;
//
//    /**
//     * 用户编号
//     */
//    @Column(columnDefinition = " varchar(50)  not null   comment ' 用户编号' ")
//    private String userNo;
//
//    /**
//     * 姓名
//     */
//    @Column(columnDefinition = " varchar(100)  not null   comment ' 姓名' ")
//    private String name;
//}
