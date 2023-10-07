package cn.tan.authentication.sas.server.service;

import cn.tan.authentication.sas.server.controller.dto.RegisterUser;
import cn.tan.authentication.sas.server.entity.SysUser;

import java.util.Optional;

/**
 * 用户
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 10:33
 */
public interface SysUserService {

    /**
     * 注册用户
     * @param register RegisterUser
     */
    void register(RegisterUser register);

    /**
     * 查询用户详情
     * @param username 登录名
     * @return SysUser
     */
    Optional<SysUser> findUserInfo(String username);
}
