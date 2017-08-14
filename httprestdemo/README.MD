# 简介

这是一个用spring-boot-starter-web配置来完成http rest 服务接口编写的示例

参考资料http://blog.csdn.net/column/details/spring-boot.html?&page=2

本示例主要使用SpringMVC来完成restful web service的开发；为消费者提供json数据接口

也就是说，没有页面，没有模板，只负责服务层

# mvc的配置

因为使用springboot很多配置都和原来不一样了，要注意**MvcConfig**这个类的使用方法

Spring 提供了基础类WebMvcConfigurerAdapter ，我们只需要重写其中的方法就可以轻松完成很多配置，如修改DispatcherServlet的URL拦截规则；添加拦截器等。

# 连接池

连接池：HikariDataSource，听说性能很好，请查看`spring.datasource.type`属性

# 内存数据库

使用Spring Boot时，默认情况下，配置DataSource非常容易。Spring Boot会自动为我们配置好一个DataSource。
如果在application.yml中指定了spring.datasource的相关配置，Spring Boot就会使用该配置创建一个DataSource。如果在application.yml中没有指定任何spring.datasource的相关配置，Spring Boot会在classpath中搜索H2、hsqldb等内存数据库的jar包，如果找到了，就会自动配置一个内存数据库的DataSource，所以，我们只要引入jar包即可。例如，配置一个hsqldb数据源：

    <dependency>
        <groupId>org.hsqldb</groupId>
        <artifactId>hsqldb</artifactId>
        <scope>runtime</scope>
    </dependency>

scheme和data两个sql文件放在classpath根目录下。

# 引入mybatis

通用泛型Mapper、代码生成工具

分页插件

