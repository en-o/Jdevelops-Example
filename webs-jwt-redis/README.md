# 项目名称


# 分支说明

# 配置文件使用说明

# 部署说明


> ./start.sh -f prod -p 9003
1. 查看提示 ./start.sh --?
2. 选择配置文件 ./start.sh -f prod
3. 设置启动端口 ./start.sh -p 8001



```yaml
        # 接口地址
        location /build-api/ {
			proxy_pass  http://localhost:9003/;
			proxy_read_timeout 300s;
			proxy_send_timeout 300s;		
			proxy_set_header Host $proxy_host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	    }
	   
	   	    
	   # 前端访问地址
		location /build {
			alias /usr/share/nginx/html/admin;
			index index.html;
			try_files $uri $uri/ /build/index.html;
		}
```        


# 特别说明    
