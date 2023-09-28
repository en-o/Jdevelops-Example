package cn.tan.authentication.sas.server.dao;

import cn.tan.authentication.sas.server.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 用户表
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 10:32
 */
public interface SysUserDao extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    /**
     * 查询用户
     * @param username 登录名
     * @return SysUser
     */
    Optional<SysUser> findByUsername(String username);
}
