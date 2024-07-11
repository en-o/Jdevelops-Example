package cn.tannn.jdevelops.demo.jpa.enhanceSpecification.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import lombok.Data;

@Data
    public class TestTimeBetween{
        /**
         * 处理时间格式
         */
        @JpaSelectOperator(function = SpecBuilderDateFun.DATE_FORMAT_DATE, operatorWrapper = SQLOperatorWrapper.BETWEEN)
        private String createTime;
    }
