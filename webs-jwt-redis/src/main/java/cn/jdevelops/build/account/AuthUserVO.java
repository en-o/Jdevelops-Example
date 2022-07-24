package cn.jdevelops.build.account;

import cn.jdevelops.entity.basics.vo.SerializableVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 用户vo
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-19 13:32
 */
@ApiModel(value = "用户vo", description = "用户管理")
@ToString
@Getter
@Setter
public class AuthUserVO extends SerializableVO<AuthUserVO> {

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String code;

    /**
     * 用户名(登录名
     */
    @ApiModelProperty(value = "用户名(登录名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;


    /**
     * 用户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    @ApiModelProperty(value = "用户状态(1.正常 2.锁定 3.删除 4.非法)")
    private Integer status;

    /**
     * 注册的IP
     */
    @ApiModelProperty(value = "注册的IP")
    private String createIp;

}
