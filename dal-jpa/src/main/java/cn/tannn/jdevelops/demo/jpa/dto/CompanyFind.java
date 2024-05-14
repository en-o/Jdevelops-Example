package cn.tannn.jdevelops.demo.jpa.dto;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.result.bean.SerializableBean;
import lombok.Data;

/**
 * 查询
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:50
 */
@Data
public class CompanyFind  extends SerializableBean<CompanyFind> {


    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR)
    private String name;


    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE ,connect = SQLConnect.OR)
    private String des;

    @JpaSelectOperator(
            operatorWrapper = SQLOperatorWrapper.EQ
            ,connect = SQLConnect.OR
            ,fieldName = "address.code")
    private String addressCode;

    @JpaSelectOperator(
            operatorWrapper = SQLOperatorWrapper.LIKE
            ,connect = SQLConnect.OR
            ,fieldName = "address.path")
    private String addressPath;
}
