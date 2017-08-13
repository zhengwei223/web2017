# 简介

这是一个用spring-boot-starter-web配置来完成http rest 服务接口编写的示例

参考资料http://blog.csdn.net/column/details/spring-boot.html?&page=2

本示例主要使用SpringMVC来完成restful web service的开发；为消费者提供json数据接口

也就是说，没有页面，没有模板，只负责服务层

# mvc的配置

因为使用springboot很多配置都和原来不一样了，要注意MvcConfig这个类的使用方法

Spring 提供了基础类WebMvcConfigurerAdapter ，我们只需要重写其中的方法就可以轻松完成很多配置，如修改DispatcherServlet的URL拦截规则；添加拦截器等。

# 连接池和mybatis

连接池：HikariDataSource，听说性能很好

引入mybatis

通用泛型Mapper、代码生成工具

分页插件

