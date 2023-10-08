package cn.tan.authentication.sas.server.service.impl;

import cn.tan.authentication.sas.error.CustomAuthenticationException;
import cn.tan.authentication.sas.server.controller.dto.RegisterUser;
import cn.tan.authentication.sas.server.dao.SysUserDao;
import cn.tan.authentication.sas.server.entity.SysUser;
import cn.tan.authentication.sas.server.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 10:34
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService, UserDetailsService {


    private final SysUserDao sysUserDao;

    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    /**
     * 设置用户信息，校验用户名、密码 <br/>
     * 授权平台的登录认证操作，是脱离客户端（第三方平台）的使用的
     * @param username 登录名
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> byUsername = sysUserDao.findByUsername(username);
        if(byUsername.isPresent()){
            SysUser sysUser = byUsername.get();
            // 用户角色
            String[] roles = StringUtils.split(sysUser.getRoles(), ",");
            return User.builder()
                    .password(sysUser.getPassword())
                    .username(sysUser.getUsername())
                    .authorities(roles)
                    .build();
        }else {
            log.warn("Username not found !");
            throw new CustomAuthenticationException("账户密码错误，请重试！");
        }
    }

    @Override
    public void register(RegisterUser register) {
        sysUserDao.save(register.ofSysUser());
    }

    @Override
    public Optional<SysUser> findUserInfo(String username) {
        return sysUserDao.findByUsername(username);
    }
}
