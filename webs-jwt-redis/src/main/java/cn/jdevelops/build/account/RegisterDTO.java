package cn.jdevelops.build.account;

import cn.jdevelops.entity.basics.vo.SerializableVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 注册
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-18 15:11
 */
@ApiModel(value = "用户注册", description = "用户注册")
@ToString
@Getter
@Setter
public class RegisterDTO  extends SerializableVO<RegisterDTO> {

    /**
     * 用户名(登录名
     */
    @ApiModelProperty(value = "用户名(登录名", required = true)
    @NotBlank
    private String username;

    /**
     * 登录密码（MD5(password+userName+salt)
     */
    @ApiModelProperty(value = "登录密码", required = true)
    @NotBlank
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank
    private String nickName;


}
