## 工学云蘑菇钉每日签到


### 非常感谢 [工学云项目](https://github.com/github123666/gonxueyun)
>> 本项目在  [工学云项目](https://github.com/github123666/gonxueyun)的项目基础上进行了封装


<!-- TOC -->
  * [工学云蘑菇钉每日签到](#工学云蘑菇钉每日签到)
  * [Star History](#star-history)
    * [1. 项目简介](#1-项目简介)
    * [2. 项目特点](#2-项目特点)
    * [3. 项目部署](#3-项目部署)
      * [3.1 项目环境](#31-项目环境)
  * [配置](#配置)
    * [1. 配置文件](#1-配置文件)
      * [1.1 application.yml](#11-applicationyml)
<!-- TOC -->

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=mirai-MIC/AutuSignMoguDing&type=Date)](https://star-history.com/#mirai-MIC/AutuSignMoguDing&Date)




### 1. 项目简介
本项目是基于Springboot3 的工学云蘑菇钉每日签到项目，每天自动签到，无需人工干预，无需手机APP.

### 2. 项目特点
- 项目基于Springboot3开发，使用Maven进行项目管理,可以自行使用Docker进行容器化部署
- 项目使用了Springboot3、Spring、SpringMVC、Mybatis、Mybatis-Plus、HttpClient等技术。

### 3. 项目部署
#### 3.1 项目环境
- JDK 17
- Maven 3.9
- Mysql 8
- 服务器 1核2G

## 配置
### 1. 配置文件
#### 1.1 application.yml
```yaml
spring:
  #数据库配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: username
    password: password
    url: jdbc:mysql://localhost:3306/autosign
    # 自行替换数据库链接


  #发送邮件
  mail:
    host: smtp.163.com
    username: # 邮箱账号
    password: #授权码
    default-encoding: UTF-8
    protocol: smtp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
          ssl:
            enable: true
            required: true
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
            fallback: false
          port: 465
          timeout: 5000

```




