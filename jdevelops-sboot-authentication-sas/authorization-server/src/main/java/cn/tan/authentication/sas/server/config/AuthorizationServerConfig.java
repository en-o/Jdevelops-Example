package cn.tan.authentication.sas.server.config;


import cn.tan.authentication.sas.server.config.mobile.MobileGrantAuthenticationConverter;
import cn.tan.authentication.sas.server.config.mobile.MobileGrantAuthenticationProvider;
import cn.tan.authentication.sas.server.config.password.PasswordGrantAuthenticationConverter;
import cn.tan.authentication.sas.server.config.password.PasswordGrantAuthenticationProvider;
import cn.tan.authentication.sas.server.controller.ServerController;
import cn.tan.authentication.sas.server.entity.SysUser;
import cn.tan.authentication.sas.server.jose.Jwks;
import cn.tan.authentication.sas.server.service.SysUserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.apache.catalina.util.StandardSessionIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;


/**
 * @author tan
 */
@Configuration
public class AuthorizationServerConfig {

	@Resource
	private SysUserService sysUserService;

	/**
	 * 授权页面
	 */
	private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

	/**
	 * Spring Authorization Server 相关配置  <br/>
	 * 主要配置OAuth 2.1和OpenID Connect 1.0相关的东西
	 */
	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
																	  OAuth2AuthorizationService authorizationService,
																	  OAuth2TokenGenerator<?> tokenGenerator)
			throws Exception {

		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.authorizationEndpoint(authorizationEndpoint ->
						authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
				// 设置自定义密码模式
				.tokenEndpoint(tokenEndpoint ->
						tokenEndpoint
								.accessTokenRequestConverter(new PasswordGrantAuthenticationConverter())
								.authenticationProvider(
										new PasswordGrantAuthenticationProvider(authorizationService,tokenGenerator)
								))
				//设置自定义手机验证码模式
				.tokenEndpoint(tokenEndpoint ->
						tokenEndpoint
								.accessTokenRequestConverter(
										new MobileGrantAuthenticationConverter())
								.authenticationProvider(
										new MobileGrantAuthenticationProvider(
												authorizationService, tokenGenerator)))
				.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0[开启OpenID Connect 1.0]
		http
				// Redirect to the login page when not authenticated from the[将需要认证的请求，重定向到login页面行登录认证。]
				// authorization endpoint
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(
								new LoginUrlAuthenticationEntryPoint("/login"))
				)
				// Accept access tokens for User Info and/or Client Registration[使用jwt处理接收到的access token]
//				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
				.oauth2ResourceServer(oauth2ResourceServer ->
						oauth2ResourceServer.jwt(Customizer.withDefaults()));
		return http.build();
	}



	// ===========使用数据库 start ===========
	/**
	 * 客户端信息 [新增客户端的注册是在数据库中注册{@link ServerController#addClient()]
	 * @see https://springdoc.cn/spring-authorization-server/core-model-components.html#registered-client-repository
	 * 对应表：oauth2_registered_client
	 */
	@Bean
	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcRegisteredClientRepository(jdbcTemplate);
	}

	/**
	 * [授权信息](https://springdoc.cn/spring-authorization-server/core-model-components.html#oauth2-authorization-service)
	 * 对应表：oauth2_authorization
	 */
	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
														   RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	/**
	 * [授权确认](https://springdoc.cn/spring-authorization-server/core-model-components.html#oauth2-authorization-consent-service)
	 * 对应表：oauth2_authorization_consent
	 */
	@Bean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
																		 RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}

	// ===========使用数据库 end ===========



	/**
	 * 配置 JWK，为JWT(id_token)提供加密密钥，用于加密/解密或签名/验签
	 * [JWK详细见](https://datatracker.ietf.org/doc/html/draft-ietf-jose-json-web-key-41)
	 */
	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = Jwks.generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

	/**
	 * 配置jwt解析器
	 */
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}




	/**
	 * 配置授权服务器请求地址
	 */
	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}



	/**
	 *配置token生成器
	 */
	@Bean
	OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
		JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
		jwtGenerator.setJwtCustomizer(jwtCustomizer());
		OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
		OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
		return new DelegatingOAuth2TokenGenerator(
				jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
	}

	/**
	 * 自定义token的内容
	 */
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
		return context -> {
			// 根据登录名 查询用户信息
			Optional<SysUser> userInfo = sysUserService.findUserInfo(context.getPrincipal().getName());
			JwsHeader.Builder headers = context.getJwsHeader();
			JwtClaimsSet.Builder claims = context.getClaims();
			if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
				// Customize headers/claims for access_token
			} else if (context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)) {
				// Customize headers/claims for id_token
				claims.claim(IdTokenClaimNames.AUTH_TIME, Date.from(Instant.now()));
				StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
				claims.claim("sid", standardSessionIdGenerator.generateSessionId());
				// 给id_token添加附加信息 - 类似 userinfo查询的效果
				userInfo.ifPresent(user -> {
					claims.claim("username", user.getUsername());
					claims.claim("name", user.getNickname());
					claims.claim("description", user.getDescription());
				});

			}
		};
	}

}

