# 简介

这是一个用spring-boot-starter-web配置来完成http rest 服务接口编写的示例

参考资料http://blog.csdn.net/column/details/spring-boot.html?&page=2

本示例主要使用SpringMVC来完成restful web service的开发；为消费者提供json数据接口

也就是说，没有页面，没有模板，只负责服务层

# mvc的配置

因为使用springboot很多配置都和原来不一样了，要注意**MvcConfig**这个类的使用方法

Spring 提供了基础类WebMvcConfigurerAdapter ，我们只需要重写其中的方法就可以轻松完成很多配置，
如修改DispatcherServlet的URL拦截规则；添加拦截器等。

第一个控制器：org.lanqiao.restdemo.endpoint.Greet

其测试类：org.lanqiao.restdemo.endpoint.GreetTest

# 连接池

连接池：HikariDataSource，听说性能很好，请查看`spring.datasource.type`属性

# 内存数据库

使用Spring Boot时，默认情况下，配置DataSource非常容易。

添加依赖：

    <dependency>
        <groupId>org.hsqldb</groupId>
        <artifactId>hsqldb</artifactId>
        <scope>runtime</scope>
    </dependency>

配置连接信息

    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.url=jdbc:h2:mem:db_users;MODE=MYSQL;\
      INIT=RUNSCRIPT FROM './src/main/resources/init-table-user.sql'
    spring.datasource.username=
    spring.datasource.password=
    
url后头那个脚本是建表的语句；测试类：org.lanqiao.restdemo.DataSourceTest

# profile

在主配置文件中：

    # 激活dev
    spring.profiles.active=dev
    
上面的连接用于开发环境，因此其文件命名为

    application-dev.properties
    
# 引入mybatis

1. 添加依赖；
2. 编写Mapper接口，见：org.lanqiao.restdemo.repository.UserMapper
3. 配置MyBatis，扫描mapper接口：org.lanqiao.restdemo.config.MybatisConfig
4. 编写测试类：org.lanqiao.restdemo.repository.UserMapperTest
5. 注意测试类可以限定Spring容器加载哪些bean，在测试Mapper的时候没必要启动MVC

