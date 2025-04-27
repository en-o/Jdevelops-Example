package cn.tannn.demo.jdevelops.authenticationsrjwt.bean;

import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
import cn.tannn.jdevelops.utils.jwt.constant.UserStatusMark;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 14:24
 */
@Data
public class Login {

    String username;

    String password;
    /**
     * 状态:1[正常],2[锁定],3[逻辑删除]
     */
    Integer status;

    /**
     * 状态标记说明
     *
     * @see UserStatusMark
     */
    String statusMark;

    /**
     * 以前的是否会被挤下线
     */
    boolean onlyOnline;


    /**
     * 用户角色
     */
    List<String> roles;

    /**
     * 用户权限[用户能当问的接口组(url组) e.g /edit/password  ] 为空则默认不验证
     * <p>
     *      （2）* 匹配0个或多个字符
     *      （3）**匹配0个或多个目录
     *      参考： @see  UserRoleUtilTest
     * </p>
     *
     */
    List<String> permissions;

    /**
     * 登录平台，默认 COMMON
     */
    List<String> platform;

    public List<String> getPlatform() {
        return platform==null||platform.isEmpty()? Collections.singletonList(PlatformConstant.COMMON) :platform;
    }

    public Integer getStatus() {
        return status==null?1:status;
    }
}
