package com.example.ssoclientorder.controller;

import cn.hutool.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OrderController {
    private final OAuth2AuthorizedClientService authorizedClientService;

    public OrderController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/order1")
    public String order1(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication:" + authentication);
        String body = "";
        try {
            if (authentication != null && authentication.isAuthenticated()
                    && authentication instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                String authorizedClientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
                OAuth2AuthorizedClient authorizedClient =
                        authorizedClientService.loadAuthorizedClient(
                                authorizedClientRegistrationId,
                                authentication.getName());
                // 可以使用 authorizedClient 来获取授权客户端的信息
                if (authorizedClient != null) {
                    // 例如，获取访问令牌
                    String accessToken = authorizedClient.getAccessToken().getTokenValue();
                    body  = HttpRequest.get("http://127.0.0.1:8090/messages1").bearerAuth(accessToken).execute().body();
                }

            } else {
                // 用户尚未认证
            }
        } catch (Exception e) {
            System.err.printf("调用资源接口失败");
            e.printStackTrace();
        }

        model.addAttribute("authentication", authentication);
        model.addAttribute("body", body);
        return "order_1";
    }

    @GetMapping("/order2")
    public String order2(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication:" + authentication);

        model.addAttribute("authentication", authentication);
        return "order_2";
    }

}
