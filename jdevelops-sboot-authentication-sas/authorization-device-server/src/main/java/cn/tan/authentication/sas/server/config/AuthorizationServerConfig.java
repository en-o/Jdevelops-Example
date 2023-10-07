package cn.tan.authentication.sas.server.config;


import cn.tan.authentication.sas.server.controller.ServerController;
import cn.tan.authentication.sas.server.jose.Jwks;
import cn.tan.authentication.sas.server.security.DeviceClientAuthenticationConverter;
import cn.tan.authentication.sas.server.security.DeviceClientAuthenticationProvider;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;


/**
 * @author tan
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

	private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

	/**
	 * Spring Authorization Server 相关配置
	 * 此处方法与下面defaultSecurityFilterChain都是SecurityFilterChain配置，配置的内容有点区别，
	 * 因为Spring Authorization Server是建立在Spring Security 基础上的，defaultSecurityFilterChain方法主要
	 * 配置Spring Security相关的东西，而此处authorizationServerSecurityFilterChain方法主要配置OAuth 2.1和OpenID Connect 1.0相关的东西
	 */
	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,RegisteredClientRepository registeredClientRepository,
																	  AuthorizationServerSettings authorizationServerSettings)
			throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		//AuthenticationConverter(预处理器)，尝试从HttpServletRequest提取客户端凭据,用以构建OAuth2ClientAuthenticationToken实例。
		DeviceClientAuthenticationConverter deviceClientAuthenticationConverter =
				new DeviceClientAuthenticationConverter(
						authorizationServerSettings.getDeviceAuthorizationEndpoint());
		//AuthenticationProvider(主处理器)，用于验证OAuth2ClientAuthenticationToken。
		DeviceClientAuthenticationProvider deviceClientAuthenticationProvider =
				new DeviceClientAuthenticationProvider(registeredClientRepository);

		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.deviceAuthorizationEndpoint(deviceAuthorizationEndpoint ->
						//设置用户码校验地址
						deviceAuthorizationEndpoint.verificationUri("/activate")
				)
				.deviceVerificationEndpoint(deviceVerificationEndpoint ->
						//设置授权页地址
						deviceVerificationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI)
				)
				.clientAuthentication(clientAuthentication ->
						//设置AuthenticationConverter(预处理器)和AuthenticationProvider(主处理器)
						clientAuthentication
								.authenticationConverter(deviceClientAuthenticationConverter)
								.authenticationProvider(deviceClientAuthenticationProvider)
				)
				.authorizationEndpoint(authorizationEndpoint ->
						authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
				//开启OpenID Connect 1.0（其中oidc为OpenID Connect的缩写）。
				.oidc(Customizer.withDefaults());

		//设置登录地址，需要进行认证的请求被重定向到该地址
		http
				.exceptionHandling((exceptions) -> exceptions
						.defaultAuthenticationEntryPointFor(
								new LoginUrlAuthenticationEntryPoint("/login"),
								new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
						)
				)
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
	 * 配置密码解析器，使用BCrypt的方式对密码进行加密和验证
	 *
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

