package cn.jdevelops.build.account;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.jdevelops.entity.basics.util.UUIDUtils;
import cn.jdevelops.exception.exception.UserException;
import cn.jdevelops.http.core.IpUtil;
import cn.jdevelops.jredis.entity.RedisAccount;
import cn.jdevelops.jredis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static cn.jdevelops.enums.result.UserExceptionEnum.DISABLED_ACCOUNT;
import static cn.jdevelops.enums.result.UserExceptionEnum.EXCESSIVE_ATTEMPTS_ACCOUNT;

/**
 * @author tomsun28
 * @date 10:58 2019-08-04
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AuthUserDao authUserDao;


    private final RedisService redisService;


    @Override
    public boolean authenticateAccount(LoginDTO login) {
        Optional<AuthUser> authUserOptional = authUserDao.findAuthUserByUsername(login.getUsername());
        if (!authUserOptional.isPresent()) {
            return false;
        }
        AuthUser authUser = authUserOptional.get();
        String password = login.getPassword();
        if (password == null) {
            return false;
        }
        if (Objects.nonNull(authUser.getSalt())) {
            // md5 with salt
            password = SecureUtil.md5(password + authUser.getSalt());
        }
        if (authUser.disabledAccount()) {
            throw new UserException(DISABLED_ACCOUNT);
        }
        if (authUser.disabledAccount()) {
            throw new UserException(EXCESSIVE_ATTEMPTS_ACCOUNT);
        }
        boolean equals = authUser.getPassword().equals(password);
        if(equals){
            // 存储到redis中，验证要用
            getDefaultAccount(login.getUsername(), authUserOptional);
        }
        return equals;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerAccount(RegisterDTO register, HttpServletRequest request) {
        if (isAccountExist(register.getUsername())) {
            return false;
        }
        String salt = RandomUtil.randomString(6);
        String userCode = UUIDUtils.getInstance().generateShortUuid();
        AuthUser authUser = register.to(AuthUser.class);
        authUser.setCreateIp(IpUtil.getPoxyIp(request));
        authUser.setSalt(salt);
        authUser.setPassword(authUser.getMd5Password());
        authUser.setStatus(1);
        authUser.setCode(userCode);
        authUserDao.save(authUser);
        return true;
    }

    @Override
    public boolean isAccountExist(String userName) {
        Optional<AuthUser> authUserOptional = authUserDao.findAuthUserByUsername(userName);
        return authUserOptional.isPresent();
    }

    @Override
    public RedisAccount loadAccount(String username) {
        RedisAccount surenessAccount = redisService.loadUserStatus(username);
        if(Objects.isNull(surenessAccount)){
            Optional<AuthUser> authUserOptional = authUserDao.findAuthUserByUsername(username);
            // 密码的验证在 authenticated Subject.getCredential()
            return getDefaultAccount(username, authUserOptional);
        }else {
            return surenessAccount;
        }
    }


    @Override
    public RedisAccount loadAccountNoRedis(String username) {
        Optional<AuthUser> authUserOptional = authUserDao.findAuthUserByUsername(username);
        // 密码的验证在 authenticated Subject.getCredential()
        return getDefaultAccount(username, authUserOptional);
    }

    @Override
    public List<AuthUser> findAll() {
        return authUserDao.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settingStatus(String username, Integer status) {
        authUserDao.settingStatus(username, status);
        Optional<AuthUser> authUserByUsername = authUserDao.findAuthUserByUsername(username);
        // 存储用户信息到redis中，验证要用
        getDefaultAccount(username, authUserByUsername);
    }

    @Override
    public AuthUserVO getUser(String username) {
        return authUserDao.findByUsername(username).to(AuthUserVO.class);
    }


    @Nullable
    private RedisAccount getDefaultAccount(String username, Optional<AuthUser> authUserOptional) {
       try {
           if (authUserOptional.isPresent()) {
               AuthUser authUser = authUserOptional.get();
               RedisAccount.RedisAccountBuilder accountBuilder = RedisAccount.builder().userCode(username)
                       .password(authUser.getPassword())
                       .salt(authUser.getSalt())
                       //  是否禁用
                       .disabledAccount(authUser.disabledAccount())
                       // 是否锁定账户
                       .excessiveAttempts(authUser.excessiveAttempts());
               RedisAccount build = accountBuilder.build();
               redisService.storageUserStatus(build);
               return build;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }
}
