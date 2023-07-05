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
package cn.tan.authentication.sas.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@EnableWebSecurity
public class SecurityConfig {


	/**
	 * 安全配置
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize ->
						// 任何请求都需要认证
						authorize.anyRequest().authenticated()
				)
				// 登录
//                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/messaging-client-oidc"))
				.oauth2Login(Customizer.withDefaults())
				.oauth2Client(Customizer.withDefaults());

		return http.build();
	}
}
