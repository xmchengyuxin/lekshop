# 常见问题

### 解决问题究极方法
将报错信息最根源的问题，放到百度/谷歌/bing 等网站，进行简单的搜索，可以解决百分之80的问题。

### 后端如何配置各种链接
本地运行时，更改根目录的 config目录下的 application-dev.yml。

### SQL在哪里
在项目的doc文件夹

### Docker镜像端口号如何设置
群友反馈端口号没有映射，无法访问。
解释这个问题需要先了解docker 的网络模式的区别：
安装Docker时，它会自动创建三个网络，bridge（创建容器默认连接到此网络）、 none 、host
host：容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口。
Container：创建的容器不会创建自己的网卡，配置自己的IP，而是和一个指定的容器共享IP、端口范围。
None：该模式关闭了容器的网络功能。
Bridge：此模式会为每一个容器分配、设置IP等，并将容器连接到一个docker0虚拟网桥，通过docker0网桥以及Iptables nat表配置与宿主机通信。
完整的解释https://www.cnblogs.com/zuxing/articles/8780661.html
示例中应用的镜像脚本，网络模型都是host，所以它采用的是宿主机的端口，所以无法看到映射内容。

### Docker API 没有dockerfile如何打包？
使用的是maven打包插件，执行命令即可
mvn clean package docker:build  -DskipTests  -DpushImage
