# java web技术的研究

## ssm单表增删改查+普通模板技术

见 [ssm-xml-crud](./ssm-xml-crud/README.MD)

## ssm restful service

传统的基于xml和注解配置的示例项目在[rbac-rest-service](./rbac-rest-service/README.MD)

这个项目传统而经典，是基于角色的权限管理；

该项目示范了SpringXML和注解混合配置的通行实践方式；
示范了SSM整合开发的较佳实践方式（如profile，基础Mapper和Service简化开发）；

在项目中集成了基于MBG的自定义代码生成策略；
集成了通用化Mapper，使用了`HikariDataSource`连接池；
集成了FastJson；

## ssm restful client

restTemplate调用rest服务，构建web界面

## spring boot&cloud 

见 [spring-boot-cloud-seed](./spring-boot-cloud-seed/README.MD)


## commons 工具包


## showcase 各种使用案例汇集在这里

如：
- 缓存
  - EhCache
  - GuavaCache
- nosql
  - redis
  - MongoDB
- rpc
  - dubbo
  - cxf
- search
  - lucene
  - solr
  
等等


## 技术选型参考文档汇总

Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）

SpringMVC ([跟我学SpringMVC目录汇总贴、PDF下载、源码下载 - 开涛的博客](http://jinnianshilongnian.iteye.com/blog/1752171)

MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）

MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）

MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）

Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
