package com.example.authorizationresource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     token 可以从客户端的token接口中获取{@link http://192.168.1.71:8081/token} {accessToken.tokenValue}
 *     token解析：https://jwt.io/ {接口权限：scope}
 *     权限来源于客户端的配置文件的配置{spring.security.oauth2.client.registration.client1-oidc.scope}
 * </p>
 */
@RestController
public class MessagesController {

    /**
     * 放行接口
     */
    @GetMapping("/hi")
    public String hi() {
        return " hello hi";
    }

    /**
     * 登录就行
     */
    @GetMapping("/messages1")
    public String getMessages1() {
        return " hello Message 1";
    }


    /**
     * PreAuthorize(如果有指定权限, 则返回 true)
     */
    @GetMapping("/messages2")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public String getMessages2() {
        return " hello Message 2";
    }


    /**
     * PreAuthorize(如果有指定权限, 则返回 true)
     */
    @GetMapping("/messages3")
    @PreAuthorize("hasAuthority('SCOPE_Message')")
    public String getMessages3() {
        return " hello Message 3";
    }


}
