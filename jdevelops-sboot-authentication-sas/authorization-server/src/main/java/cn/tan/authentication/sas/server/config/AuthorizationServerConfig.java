/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.tan.authentication.sas.server.config;


import cn.tan.authentication.sas.server.jose.Jwks;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe Grandja
 * @author Daniel Garnier-Moiroux
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";


    /**
     *
     * 端点过滤器链
     * <ul>
     *     <li>OAuth2 Authorization endpoint</li>
     *     <li>OAuth2 Token endpoint</li>
     *     <li>OAuth2 Token Introspection endpoint</li>
     *     <li>OAuth2 Token Revocation endpoint</li>
     *     <li>OAuth2 Authorization Server Metadata endpoint</li>
     *     <li>JWK Set endpoint</li>
     *     <li>OpenID Connect 1.0 Provider Configuration endpoint</li>
     *     <li>OpenID Connect 1.0 UserInfo endpoint</li>
     * </ul>
     *
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

        // 定义授权服务配置器
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        authorizationServerConfigurer
                // 设置自定义用户承认授权页
                .authorizationEndpoint(authorizationEndpoint ->
                        authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
                // Enable OpenID Connect 1.0, 启用 OIDC 1.0
//                .oidc(Customizer.withDefaults());
                //  自定义返回 oidc userinfo 信息
                .oidc(oidcConfigurer -> oidcConfigurer.userInfoEndpoint(userInfoEndpointConfigurer ->
                        userInfoEndpointConfigurer.userInfoMapper(userInfoAuthenticationContext -> {
                            OAuth2AccessToken accessToken = userInfoAuthenticationContext.getAccessToken();
                            Map<String, Object> claims =new HashMap<>();
                            claims.put("url", "http://127.0.0.1:9000");
                            claims.put("accessToken", accessToken);
                            claims.put("sub", userInfoAuthenticationContext.getAuthorization().getPrincipalName());
                            return new OidcUserInfo(claims);
                        })));


        // 获取授权服务器相关的请求端点
        RequestMatcher endpointsMatcher = authorizationServerConfigurer
                .getEndpointsMatcher();

        http
                // 拦截对授权服务器相关端点的请求
                .requestMatcher(endpointsMatcher)
                // 拦载到的请求需要认证
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                // 忽略掉相关端点的 CSRF(跨站请求): 对授权端点的访问可以是跨站的
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                // 处理运用access token拜访用户信息端点和客户端注册端点
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                // 访问端点时表单登录
                .formLogin()
                .and()
                // 应用授权服务器的配置
                .apply(authorizationServerConfigurer);
        return http.build();
    }


    /**
     * 客户端注册 对应 oauth2_registered_client 表
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 令牌的发放记录, 对应 oauth2_authorization 表
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 把资源拥有者授权确认操作保存到数据库, 对应 oauth2_authorization_consent 表
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 用于给 access_token 签名使用。
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }


    /**
     * 用于给 access_token 解码使用。
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     *  AuthorizationServerS 的相关配置
     * - 增加认证服务器装备，设置jwt签发者、默许端点请求地址等
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }
}
