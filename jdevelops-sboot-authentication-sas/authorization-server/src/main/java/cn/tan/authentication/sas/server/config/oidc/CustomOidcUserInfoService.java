package cn.tan.authentication.sas.server.config.oidc;

import cn.tan.authentication.sas.server.entity.SysUser;
import cn.tan.authentication.sas.server.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 *  自定义 oidc的 userinfo接口的数据获取和组装
 * @author tan
 */
@Service
public class CustomOidcUserInfoService {

    @Resource
    private SysUserService sysUserService;

    public CustomOidcUserInfo loadUser(String username) {
        Optional<SysUser> userInfo = sysUserService.findUserInfo(username);
        return new CustomOidcUserInfo(this.createUser(userInfo));
    }

    private Map<String, Object> createUser(Optional<SysUser> userInfo) {
        if(userInfo.isPresent()){
            SysUser sysUser = userInfo.get();
            return CustomOidcUserInfo.customBuilder()
                    .nickname(sysUser.getNickname())
                    .username(sysUser.getUsername())
                    .description(sysUser.getDescription())
                    .status(sysUser.getStatus())
                    .phoneNumber(sysUser.getUsername())
                    .email(sysUser.getUsername() + "@example.com")
                    .profile("https://example.com/" + sysUser.getNickname())
                    .address("XXX共和国XX省XX市XX区XXX街XXX号")
                    .build()
                    .getClaims();
        }
        return Collections.emptyMap();
    }

}
