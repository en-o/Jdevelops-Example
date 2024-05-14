package cn.tannn.jdevelops.demo.jpa.controller.pojo;

import cn.hutool.core.date.DateTime;
import cn.tannn.jdevelops.annotations.jpa.JpaUpdate;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author tan
 */
@Data
public class UpdateUser {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号/联系电话
     */
    @JpaUpdate(ignore = true)
    private String phone;


    /**
     * 表示该字段为修改时间字段，在这个实体被update的时候，会自动为其赋值
     */
    private LocalDateTime updateTime =  DateTime.now().toLocalDateTime();

    /**
     * 表示该字段为修改人，在这个实体被update的时候，会自动为其赋值
     */
    private String updateUserName = "tann" ;

    /**
     * 头像
     */
    private String userIcon;




}
