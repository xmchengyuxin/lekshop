# 部署程序
#### 新建数据库
mysql环境安装完成后, 将document文件夹里面的 lekshop.sql 和 schedule.sql分别导入

#### 后端代码打包
```
    1. 需要部署的代码项目为 
       lekshop-admin 运营后台
       lekshop-shop 商家后台
       lekshop-portal 买家前台
       lekshop-netty Im聊天框架

    2. 在代码根目录执行 mvn install , 执行成功后, 在以上四个项目中的target的.jar包上传到服务器
    
    3. 执行命令 
       nohup java -jar /jar/lekshop-admin-1.0-SNAPSHOT.jar --server.port=7073 --spring.profiles.active=prod &
       nohup java -jar /jar/lekshop-shop-1.0-SNAPSHOT.jar --server.port=7074 --spring.profiles.active=prod &
       nohup java -jar /jar/lekshop-portal-1.0-SNAPSHOT.jar --server.port=7076 --spring.profiles.active=prod &
       nohup java -jar /jar/lekshop-netty-1.0-SNAPSHOT.jar &
       
    4. 项目启动成功

```

#### 前端代码打包
```
    使用的前后端分离, 所以对应的前端项目有如下三个
    1. lekshop_ui_admin 运营后台前端项目
    2. lekshop_ui_shop 商家后台前端项目
    3. lekshop_ui_portal 买家前台uniapp项目
    
    前两个项目使用Hbuilder打开, .env.production相关参数的配置后, 执行一下命令打包
    npm run build:prod
    打包成功后会在项目生成dist文件夹, 里面就是要上传服务器的html文件

    关于uniapp项目如何打包成小程序, H5, APP, 可自行百度
```

#### 域名解析(以下是演示站nginx的解析配置, 仅做参考)
``` 
#user  nobody;
worker_processes  2;

#error_log  logs/error.log 
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  10240;
}


http {

	proxy_connect_timeout 10;
    proxy_read_timeout 180;
    proxy_send_timeout 5;
    #proxy_buffer_size 16k;
    #proxy_buffers 4 32k;
    #proxy_busy_buffers_size 96k;
    #proxy_temp_file_write_size 96k;
    #proxy_temp_path C:/java/nginx-1.8.0/temp;
    #proxy_cache_path C:/java/nginx-1.8.0/temp/cache levels=1:2 keys_zone=cache_one:500m inactive=1d max_size=10g;
		
	server_names_hash_bucket_size 64; 
	
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;
    client_max_body_size 20m;

    gzip on;
    gzip_min_length 1k;
    gzip_http_version 1.0;
    gzip_disable "MSIE [1-6].";
    gzip_comp_level 5;
    gzip_types text/plain application/x-javascript text/css text/javascript application/javascript application/json;
		
	upstream websocket{
		server 127.0.0.1:8008; 
	}

	server {
        listen       80;
        server_name  adminapi.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 proxy_pass http://127.0.0.1:7073/;
			 proxy_set_header Host $host:80;
			 proxy_set_header X-Real-IP $remote_addr;
			 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
				
    }
	
	server {
        listen       80;
        server_name  admin.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 root /www/lekshop/admin_ui/;
			 index index.html;
        }
				
    }
	
	server {
        listen       80;
        server_name  shopapi.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 proxy_pass http://127.0.0.1:7074/;
			 proxy_set_header Host $host:80;
			 proxy_set_header X-Real-IP $remote_addr;
			 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
				
    }
	
	server {
        listen       80;
        server_name  shop.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 root /www/lekshop/shop_ui/;
			 index index.html;
        }
				
    }
	
	server {
        listen       80;
        server_name  portalapi.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 proxy_pass http://127.0.0.1:7076/;
			 proxy_set_header Host $host:80;
			 proxy_set_header X-Real-IP $remote_addr;
			 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
				
    }
	
	server {
        listen       443 ssl;
        server_name  portalapi.lekshop.cn;

		ssl_certificate ../cert/7922963_portalapi.lekshop.cn.pem; 
		ssl_certificate_key ../cert/7922963_portalapi.lekshop.cn.key; 
		ssl_session_timeout 5m; 
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4; 
		ssl_protocols TLSv1 TLSv1.1 TLSv1.2; 
		ssl_prefer_server_ciphers on; 
	
        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 proxy_pass http://127.0.0.1:7076/;
			 proxy_set_header Host $host:80;
			 proxy_set_header X-Real-IP $remote_addr;
			 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }
		
    }
	
	
	server {
        listen       80;
        server_name  m.lekshop.cn;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
			 root /www/lekshop/portal_ui/;
			 index index.html;
        }
				
    }
	
	server {
	    listen       443 ssl;
        server_name  socket.lekshop.cn;

		ssl_certificate ../cert/7924395_socket.lekshop.cn.pem; 
		ssl_certificate_key ../cert/7924395_socket.lekshop.cn.key; 
		ssl_session_timeout 5m; 
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4; 
		ssl_protocols TLSv1 TLSv1.1 TLSv1.2; 
		ssl_prefer_server_ciphers on; 
       
	    
	   location / {
        proxy_pass http://websocket; #代理到上面的地址去
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "Upgrade";
	   }

	}
	
	
}
```
#### 启动nginx
```
    service nginx restart
```



