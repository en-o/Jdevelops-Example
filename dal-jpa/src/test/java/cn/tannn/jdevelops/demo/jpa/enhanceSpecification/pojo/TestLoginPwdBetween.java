package cn.tannn.jdevelops.demo.jpa.enhanceSpecification.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import lombok.Data;

@Data
public class TestLoginPwdBetween {
    /**
     * 处理时间格式
     */
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.BETWEEN)
    private String loginPwd;
}
