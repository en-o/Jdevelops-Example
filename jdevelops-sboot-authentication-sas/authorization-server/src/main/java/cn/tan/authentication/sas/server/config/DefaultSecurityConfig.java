/*
 * Copyright 2020-2021 the original author or authors.
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

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author Joe Grandja
 */
@EnableWebSecurity
public class DefaultSecurityConfig {




	/**
	 * 配置Spring Security相关的东西  <br/>
	 *  Spring Security 过滤链配置（此处是纯Spring Security相关配置）
	 */
	@Bean
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http
				// 设置所有请求都需要认证，未认证的请求都被重定向到login页面进行登录
				.authorizeHttpRequests((authorize) -> authorize
						// 放行静态资源
						.mvcMatchers("/api/**", "/assets/**", "/webjars/**", "/login").permitAll()
						// 拦截其余所有
						.anyRequest().authenticated()
				)
				// Form login handles the redirect to the login page from the
				// authorization server filter chain
				// [由Spring Security过滤链中UsernamePasswordAuthenticationFilter过滤器拦截处理“login”页面提交的登录信息。]
				.formLogin(Customizer.withDefaults());

		return http.build();
	}




	/**
	 * 设置用户信息，校验用户名、密码 <br/>
	 * 授权平台的登录认证操作，是脱离客户端（第三方平台）的使用的
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		//基于内存的用户数据校验
		return new InMemoryUserDetailsManager(userDetails);
	}
}
