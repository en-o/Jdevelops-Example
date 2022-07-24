package cn.jdevelops.build.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户状态
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-23 01:10
 */
@ApiModel(value = "设置用户状态", description = "用户管理")
@ToString
@Getter
@Setter
public class SettingUserStatusDTO {

    /**
     * 用户名(登录名
     */
    @ApiModelProperty(value = "用户名(登录名", required = true)
    @NotBlank
    private String username;

    /**
     * 用户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    @ApiModelProperty(value = "用户状态(1.正常 2.锁定 3.删除 4.非法)", required = true,example = "1")
    @NotNull
    @Max(4)
    @Min(1)
    private Integer status;
}
