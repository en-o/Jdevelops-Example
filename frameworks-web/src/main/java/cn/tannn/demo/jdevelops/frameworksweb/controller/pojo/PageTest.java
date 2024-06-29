package cn.tannn.demo.jdevelops.frameworksweb.controller.pojo;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectIgnoreField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectNullField;
import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLConnect;
import cn.tannn.jdevelops.annotations.jpa.enums.SQLOperatorWrapper;
import cn.tannn.jdevelops.jpa.request.PagingSorteds;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author tan
 */
@Data
@Schema(description = "page")
public class PageTest {

    @Schema(description = "名字")
    @JpaSelectOperator(operatorWrapper = SQLOperatorWrapper.LIKE, connect = SQLConnect.OR)
    @JpaSelectNullField(ignoreNullEnhance = false)
    String name;

    @Schema(description = "地址")
    @JpaSelectNullField(ignoreNullEnhance = false)
    String address;

    @JpaSelectIgnoreField
    PagingSorteds page;

    public PagingSorteds getPage() {
        return page == null ? new PagingSorteds() : page;
    }
}
