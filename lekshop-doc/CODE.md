# 代码结构
### 后端
```
lekshop-parent
├── lekshop-common -- 工具类及通用代码
├── lekshop-mbg -- MyBatisGenerator生成的数据库操作代码
├── lekshop-security -- SpringSecurity封装公用模块
├── lekshop-admin -- 运营后台商城管理系统接口
├── lekshop-shop -- 商家后台商城管理系统接口
├── lekshop-portal -- 前台商城系统接口
├── lekshop-netty -- 基于Netty的消息中心, 主要用于IM和推送
└── lekshop-service -- 接口业务逻辑
```


### 前端
lekshop_ui_admin：运营后台

lekshop_ui_shop：商家后台

lekshop_ui_portal：买家
