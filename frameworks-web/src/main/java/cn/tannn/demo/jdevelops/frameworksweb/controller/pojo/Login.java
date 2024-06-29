package cn.tannn.demo.jdevelops.frameworksweb.controller.pojo;

import cn.tannn.jdevelops.annotations.web.constant.PlatformConstant;
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
     * 禁用[账号禁用，跟锁定有点像，但是这个是没有失效，除非管理员主动解除], 同类状态有：
     * - 删除
     * - 停用
     */
    private boolean disabledAccount;

    /**
     * 锁定[过度的尝试，判断未异常操作进行账号锁定，一般是有时效的]
     */
    private boolean excessiveAttempts;

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
    List<PlatformConstant> platform;

    public List<PlatformConstant> getPlatform() {
        return platform==null||platform.isEmpty()? Collections.singletonList(PlatformConstant.COMMON) :platform;
    }
}
