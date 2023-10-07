package cn.tan.authentication.sas.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * EnableMethodSecurity  {@link https://www.cnblogs.com/goloving/p/14891241.html}
 * <p>
 *   EnableGlobalMethodSecurity(securedEnabled=true) 开启@Secured 注解过滤权限
 *   EnableGlobalMethodSecurity(jsr250Enabled=true)开启@RolesAllowed 注解过滤权限
 *   EnableGlobalMethodSecurity(prePostEnabled=true) 使用表达式时间方法级别的安全性 4个注解可用【默认启用】
 * 		PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问
 * 		PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常
 * 		PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果
 * 		PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值
 * </p>
 * @author tan
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class ResourceServerConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
                        //所有的访问都需要通过身份认证
						.anyRequest().authenticated()
				)
				.oauth2ResourceServer(oauth2 -> oauth2
								.jwt(Customizer.withDefaults())

				);
		return http.build();

	}
}
