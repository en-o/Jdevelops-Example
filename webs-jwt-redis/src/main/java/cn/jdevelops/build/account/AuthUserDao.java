package cn.jdevelops.build.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tan
 * @date 2022-07-18 11:00:57
 */
public interface AuthUserDao extends JpaRepository<AuthUser, Long> {

    /**
     * 通过用户名获取用户
     * @param username username
     * @return user
     */
    @Query("select au from AuthUser au where au.username = :username")
    Optional<AuthUser> findAuthUserByUsername(@Param("username") String username);


    /**
     * 通过用户名获取用户
     * @param username username
     * @return user
     */
    AuthUser findByUsername(String username);


    /**
     * 设置用户状态
     * @param username 用户唯一编码
     * @param status 用户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update AuthUser au set au.status = :status where  au.username in (:username) ")
    void settingStatus(@Param("username") String username, @Param("status")  Integer status);
}
