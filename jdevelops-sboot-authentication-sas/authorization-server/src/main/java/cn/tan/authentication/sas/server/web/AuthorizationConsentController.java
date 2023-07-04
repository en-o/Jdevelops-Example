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
package cn.tan.authentication.sas.server.web;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Daniel Garnier-Moiroux
 */
@Controller
public class AuthorizationConsentController {
	private final RegisteredClientRepository registeredClientRepository;
	private final OAuth2AuthorizationConsentService authorizationConsentService;

	public AuthorizationConsentController(RegisteredClientRepository registeredClientRepository,
			OAuth2AuthorizationConsentService authorizationConsentService) {
		this.registeredClientRepository = registeredClientRepository;
		this.authorizationConsentService = authorizationConsentService;
	}

	@GetMapping(value = "/oauth2/consent")
	public String consent(Principal principal, Model model,
			@RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
			@RequestParam(OAuth2ParameterNames.SCOPE) String scope,
			@RequestParam(OAuth2ParameterNames.STATE) String state) {

		// Remove scopes that were already approved
		// 要批准的范围和以前批准的范围
		Set<String> scopesToApprove = new HashSet<>();
		Set<String> previouslyApprovedScopes = new HashSet<>();
		// 查询 clientId 是否存在
		RegisteredClient registeredClient = this.registeredClientRepository.findByClientId(clientId);
		// 查询当前的授权许可
		OAuth2AuthorizationConsent currentAuthorizationConsent =
				this.authorizationConsentService.findById(registeredClient.getId(), principal.getName());

		// 已授权范围
		Set<String> authorizedScopes;
		if (currentAuthorizationConsent != null) {
			authorizedScopes = currentAuthorizationConsent.getScopes();
		} else {
			authorizedScopes = Collections.emptySet();
		}
		for (String requestedScope : StringUtils.delimitedListToStringArray(scope, " ")) {
			if (OidcScopes.OPENID.equals(requestedScope)) {
				continue;
			}
			// 如果已授权范围包含了请求范围，则添加到以前批准的范围的 Set, 否则添加到要批准的范围
			if (authorizedScopes.contains(requestedScope)) {
				previouslyApprovedScopes.add(requestedScope);
			} else {
				scopesToApprove.add(requestedScope);
			}
		}

		model.addAttribute("clientId", clientId);
		model.addAttribute("state", state);
		model.addAttribute("scopes", withDescription(scopesToApprove));
		model.addAttribute("previouslyApprovedScopes", withDescription(previouslyApprovedScopes));
		model.addAttribute("principalName", principal.getName());

		return "consent";
	}

	private static Set<ScopeWithDescription> withDescription(Set<String> scopes) {
		Set<ScopeWithDescription> scopeWithDescriptions = new HashSet<>();
		for (String scope : scopes) {
			scopeWithDescriptions.add(new ScopeWithDescription(scope));

		}
		return scopeWithDescriptions;
	}

	public static class ScopeWithDescription {
//		private static final String DEFAULT_DESCRIPTION = "UNKNOWN SCOPE - We cannot provide information about this permission, use caution when granting this.";
		private static final String DEFAULT_DESCRIPTION = "未知范围 - 我们无法提供有关此权限的信息, 请在授予此权限时谨慎";
		private static final Map<String, String> scopeDescriptions = new HashMap<>();
		static {
			scopeDescriptions.put(
					OidcScopes.PROFILE,
//					"This application will be able to read your profile information."
					"此应用程序将能够读取您的个人资料信息"
			);
			scopeDescriptions.put(
					"message.read",
//					"This application will be able to read your message."
					"此应用程序将能够读取您的信息"
			);
			scopeDescriptions.put(
					"message.write",
//					"This application will be able to add new messages. It will also be able to edit and delete existing messages."
					"此应用程序将能够添加新信息, 它还可以编辑和删除现有信息"
			);
			scopeDescriptions.put(
					"other.scope",
//					"This is another scope example of a scope description."
					"这是范围描述的另一个范围示例"
			);
		}

		public final String scope;
		public final String description;

		ScopeWithDescription(String scope) {
			this.scope = scope;
			this.description = scopeDescriptions.getOrDefault(scope, DEFAULT_DESCRIPTION);
		}
	}
}