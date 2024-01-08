1. 启动授权服务 authorization-server
2. 启动资源服务 resource-server
3. 启动客户端1 sso-client-order
4. 启动客户端2 sso-client-product
5. 分别访问其中一个且登录之后，在访问另一个查看是否需要再一次登录
- 账户密码在 apifox -> authorization 项目 -> one -> 设备授权码认证服务器 -> 添加测试账户
> http://192.168.1.71:8083/index
> http://192.168.1.71:8083/product1

> http://192.168.1.71:8082/index
> http://192.168.1.71:8082/order1
