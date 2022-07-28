package cn.jdevelops.build.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 登录
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-18 14:01
 */
@ApiModel(value = "用户登录", description = "用户登录")
@ToString
@Getter
@Setter
public class LoginDTO {

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    @NotBlank
    private String username;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密密", required = true)
    @NotBlank
    private String password;


    /**
     * 是否永久在线（默认 false
     */
    @ApiModelProperty(value = "登录密密", required = true, example = "true")
    Boolean alwaysOnline;
}
