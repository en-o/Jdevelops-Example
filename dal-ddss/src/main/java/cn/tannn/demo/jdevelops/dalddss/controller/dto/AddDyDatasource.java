package cn.tannn.demo.jdevelops.dalddss.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据源
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/15 12:00
 */
@Getter
@Setter
@ToString
public class AddDyDatasource {


    /**
     * 数据源名称
     */
    String datasourceName;

    /**
     * 数据源url
     */
    String datasourceUrl;

    /**
     * 数据源帐号
     */
    String datasourceUsername;

    /**
     * 数据源密码
     */
    String datasourcePassword;

    /**
     * 备注
     */
    String remark;


    /**
     * 数据库驱动
     */
    String driverClassName;


}
