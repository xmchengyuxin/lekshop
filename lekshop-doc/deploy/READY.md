# 部署准备
### 上线准备
一套完整的域名（PC端、移动端、商家端、管理端、以及4个API端）

ssl证书

域名备案

微信支付参数

微信小程序参数

七牛云参数

阿里云短信参数

快递鸟物流查询参数

### 最低服务器配置
4核8G内存服务器一台（推荐使用 CentOS 7.6 64位）（如果基础中间件使用外部服务器的，则可以适量减少内存）

### 环境准备
```
jdk >= 1.8

Mysql 8.x.x

Redis >= 6.2.5

nginx 1.22.0

以上环境为演示站点环境，尽可能使用同一版本

docker-compose环境安装（如果自行部署基础中间件，则可以略过这里，这里主要安装基础中间件）
安装docker-compose环境(已经安装的可以略过)

```
