package cn.tannn.jdevelopssbootjpademo.dto;

import cn.jdevelops.api.result.bean.SerializableBean;
import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.jdevelops.data.jap.enums.SQLOperator;
import lombok.Data;

/**
 * 查询
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:50
 */
@Data
public class CompanyFind  extends SerializableBean<CompanyFind> {


    @JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.OR)
    private String name;


    @JpaSelectOperator(operator = SQLOperator.LIKE ,connect = SQLConnect.OR)
    private String des;

    @JpaSelectOperator(
            operator = SQLOperator.EQ
            ,connect = SQLConnect.OR
            ,fieldName = "address.code")
    private String addressCode;

    @JpaSelectOperator(
            operator = SQLOperator.LIKE
            ,connect = SQLConnect.OR
            ,fieldName = "address.path")
    private String addressPath;
}
