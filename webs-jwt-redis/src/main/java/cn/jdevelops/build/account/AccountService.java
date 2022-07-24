package cn.jdevelops.build.account;


import cn.jdevelops.jredis.entity.RedisAccount;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tan
 * @date 2022-07-18 11:18:26
 */
public interface AccountService {
    /**
     * 验证帐号、用户名和密码的有效性
     * @param login 登录
     * @return success-true failed-false
     */
    boolean authenticateAccount(LoginDTO login);

    /**
     * 注册用户
     * @param register account info}
     * @param request  request
     * @return success-true failed-false
     */
    boolean registerAccount(RegisterDTO register, HttpServletRequest request);

    /**
     * 确定帐号是否已经存在
     * @param username account info
     * @return exist-true no-false
     */
    boolean isAccountExist(String username);

    /**
     * 此接口不要在前端接口上使用，会暴露敏感信息
     * 按用户名加载帐号信息(查redis并存redis
     * (ps: 用于 header: username,password 方式登录时的校验)
     * @param username account username
     * @return account
     */
    RedisAccount loadAccount(String username);



    /**
     * 此接口不要在前端接口上使用，会暴露敏感信息
     * 按用户名加载帐号信息（不会查redis，但会存redis
     * @param username account username
     * @return account
     */
    RedisAccount loadAccountNoRedis(String username);


    /**
     * 查询所有用户
     * @return List
     */
    List<AuthUser> findAll();

    /**
     * 设置用户状态
     * @param username 用户唯一编码
     * @param status 用户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    void settingStatus(String username,Integer status);

    /**
     * 获取用户
     * @param username 用户唯一编码
     * @return AuthUser
     */
    AuthUserVO getUser(String username);

}
