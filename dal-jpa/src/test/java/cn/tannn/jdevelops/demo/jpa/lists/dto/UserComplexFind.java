package cn.tannn.jdevelops.demo.jpa.lists.dto;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectIgnoreField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectNullField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.annotations.jpa.enums.SpecBuilderDateFun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 复杂查询测试   - and or 随便排
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/17 上午10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserComplexFind {

    /**
     * like OR (not null and not "" and not " ")
     */
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR)
    private String userNo;

    /**
     * in AND (not null and not "" and not " ")
     */
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.IN, connect = SQLConnect.AND)
    private List<String> name;

    /**
     * and (not null and not "" and not " ")
     */
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.EQ)
    private String loginName;

    /**
     * like or (not null)
     */
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, nullField = @JpaSelectNullField(ignoreNullEnhance = false)
            , connect = SQLConnect.OR)
    private String userIcon;

    /**
     * 默认 eq / and  / not null
     */
    private String loginPwd;

    /**
     * 忽略
     */
    @JpaSelectIgnoreField
    private String phone;

    /**
     * not null
     */
    @JpaSelectNullField(ignoreNullEnhance = false)
    private String address;

    /**
     * 处理时间格式
     */
    @JpaSelectOperator(function = SpecBuilderDateFun.DATE_FORMAT)
    private String createTime;
}
