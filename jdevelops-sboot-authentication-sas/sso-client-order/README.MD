1. 新增客户端
```json
{
    "clientId": "web-client-id-order",
    "clientSecret": "secret",
    "clientName": "web平台-SSO客户端-订单服务",
    "clientAuthenticationMethods": [
        "client_secret_basic"
    ],
    "authorizationGrantTypes": [
        "authorization_code",
        "refresh_token"
    ],
    "redirectUris": [
        "http://192.168.1.71:8082/login/oauth2/code/messaging-client-oidc"
    ],
    "scopes": [
        "openid",
        "profile"
    ]
}
```

2. 访问首页 受保护的资源页 [主页order页面的 接口地址]
http://192.168.1.71:8082/index
http://192.168.1.71:8082/order1


