package cn.tannn.jdevelops.demo.jpa.controller.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaUpdate;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.service.J2Service;
import cn.tannn.jdevelops.jpa.utils.JpaUtils;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * @author tan
 */
@Data
public class UpdateUser {


    /**
     * 用户编号
     * {@link JpaUpdate#unique}  设置当前更新的添加key, update set name="" where userNo = ""
     * <p> 以{@link JpaUtils#updateBean(Object, EntityManager, Class, SQLOperator, String...)}</p> 中的 uniqueKey 为主
     *  <p> 1. uniqueKey为空的话
     *  <p> 1.1 首先判断 bean里是个否标注了{@link JpaUpdate#unique()},标注了就使用他做条件
     *  <p> 1.2 其次如何自定义的注解为标注那就回去拿实体的 {@link Id} 标注的字段名
     */
    @JpaUpdate(unique = true)
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
     * 头像
     */
    private String userIcon;


    /**
     * {@link J2Service#update} 是不会处理JPA的审计字段这里需要自己处理下
     * <p> 如果是 {@link LocalDateTime} 类型可以使用  {@link JpaUpdate#autoTime()}  注解来自定复制
     * <p> 如何不是 那就自己手动在 getter 方法里处理下把
     */
    @JpaUpdate(autoTime = true)
    private LocalDateTime updateTime;

    /**
     * {@link J2Service#update} 是不会处理JPA的审计字段这里需要自己处理下
     * <p> 自己手动在 getter 方法里处理下把
     */
    private String updateUserName = "tann" ;


}
