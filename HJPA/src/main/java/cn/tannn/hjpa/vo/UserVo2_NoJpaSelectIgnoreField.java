package cn.tannn.hjpa.vo;

import cn.jdevelops.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import lombok.Data;

@Data
public class UserVo2_NoJpaSelectIgnoreField {
    /**
     * 用户编号
     */
    @JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR)
    private String userNo;

    /**
     * 姓名
     */
    @JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR, ignoreNull = false)
    private String name;

    /**
     * 地址
     */
    @JpaSelectOperator(operator = SQLOperator.LIKE, connect = SQLConnect.OR)
    private String address;

    /**
     * 登录名称
     */
    @JpaSelectOperator(operator = SQLOperator.EQ, connect = SQLConnect.AND)
    private String loginName;

    /**
     * 测试忽略 @JpaSelectIgnoreField
     */
    private String ignoreMe;

}
