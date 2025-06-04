# 森启图床系统 (SenQi Media)


## 简介

SenQi Media 是一个支持图片与视频托管的图床系统，适合自用、团队协作或 SaaS 场景。项目采用前后端分离架构，基于 Spring Boot + Vue/Nuxt 实现，支持 Docker Compose 一键部署。

## 主要功能

- **多平台支持**  
  采用 SpringBoot + Nuxt.js + Ant Design Vue 技术栈，前后端分离，响应式设计，支持 PC 和移动端无缝使用。

- **多种上传方式**  
  支持单张上传、批量上传、拖拽上传、URL远程抓取等多种灵活方式。

- **强大的管理后台**  
  提供图片管理、用户管理、流量统计、权限控制、API 管理等功能。

- **流量计费支持**  
  内置按访问流量计费机制，方便业务灵活运营。

- **高效存储与 CDN 加速**  
  支持本地存储、对象存储（如阿里云OSS、腾讯COS）及 CDN 加速，提升访问速度和稳定性。

- **安全与隐私**  
  支持图片防盗链、访问权限控制、用户鉴权和日志审计。

- **丰富的 API 接口**  
  方便第三方系统集成，支持插件扩展和二次开发。



## 技术架构

- 后端：SpringBoot 3.x，Java 17，RESTful API
- 前端：Nuxt.js 3 + Ant Design Vue，SSR 支持
- 存储：支持本地文件系统和多云存储插件
- 安全：JWT 认证、权限控制
- 部署：Docker 支持，易于扩展和维护


## 项目结构

```
senqi-media/
├── senqi-media-server/       # 后端 API 服务（Spring Boot）
├── senqi-media-admin/        # 后台管理系统（Vue3 + Ant Design Vue）
├── senqi-media-front/        # 前台图床展示站（Vue3 或 Nuxt）
├── senqi-media-sql/          # 数据库初始化 SQL 脚本
├── docker-compose.yml        # 一键部署配置
└── README.md                 # 项目说明文档
````


## 快速开始

1. 克隆仓库
2. 配置数据库及环境变量
3. 编译并启动后端服务
4. 启动前端界面
5. 访问管理后台，开始上传图片！

详见 [文档链接](#)（后续补充）



## 贡献

欢迎社区贡献代码、提交问题和建议，一起打造更好的森启图床系统！



## 许可证

本项目采用 [MIT License](LICENSE)。



## 联系我们

森启网络科技  
官网：https://www.senqicloud.com  
邮箱：support@senqicloud.com  
微信：SenQiTech



> 让图片管理更简单，让业务更轻松！
