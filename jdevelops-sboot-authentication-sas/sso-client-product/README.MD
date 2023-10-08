1. 新增客户端
```json
{
  "clientId": "web-client-id-product",
  "clientSecret": "secret",
  "clientName": "web平台-SSO客户端-商品服务",
  "clientAuthenticationMethods": [
    "client_secret_basic"
  ],
  "authorizationGrantTypes": [
    "authorization_code",
    "refresh_token"
  ],
  "redirectUris": [
    "http://192.168.1.71:8083/login/oauth2/code/messaging-client-oidc"
  ],
  "scopes": [
    "openid",
    "profile"
  ]
}
```

2. 访问首页 受保护的资源页 [主页order页面的 接口地址]
http://192.168.1.71:8083/index
http://192.168.1.71:8083/product1


