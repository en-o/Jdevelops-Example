package cn.tannn.jdevelops.demo.jpa.enhanceSpecification.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestOr {


    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR )
    private String name;

    /**
     * 处理时间格式
     */
    @JpaSelectOperator( operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR)
    private String userNo;

}
