# 天天吃货电商平台用户手册

## 前端启动项

前端分为两个项目foodie-shop和foodie-center，他们是两个互相关联但是又独立的项目，下面是两个项目的启动方案：

1. 下载tomcat
2. 将foodie-shop和foodie-center这两个目录拷贝到tomcat的webapp目录中
3. 点击tomcat源路径下bin/start-up.bat启动tomcat
4. 这里通过http://localhost/foodie-shop访问电商门户网页，通过http://localhost/foodie-center访问个人中心网页
5. 两个门户网页前端和后端对接时，后端的ip地址和端口配置均配置在两个项目的app.js中

## 后端启动项

后端api服务只有一个foodie-dev项目，个人中心和门户网站的api均写在该项目内部。

1.启动foodie-api模块下的FoodieApplication即可运行后端api项目，运行端口为8088

