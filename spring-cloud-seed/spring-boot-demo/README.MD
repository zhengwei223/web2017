# 简介

这是一个spring-boot综合示例

[参考资料](http://blog.csdn.net/column/details/spring-boot.html?&page=2)


# mvc的配置

因为使用`springboot`很多配置都和原来不一样了，要注意**MvcConfig**这个类的使用方法

`Spring` 提供了基础类`WebMvcConfigurerAdapter` ，我们只需要重写其中的方法就可以轻松完成很多配置，
如修改`DispatcherServlet`的URL拦截规则；添加拦截器等。

第一个控制器：`org.lanqiao.restdemo.endpoint.Greet`

其测试类：`org.lanqiao.restdemo.endpoint.GreetAPITest`

# 连接池

连接池：HikariDataSource，听说性能很好，请查看`spring.datasource.type`属性

# 内存数据库

使用Spring Boot时，默认情况下，配置DataSource非常容易。

添加依赖：

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>${h2.version}</version>
    </dependency>

配置连接信息，见文件`config/application-dev.properties`

    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.url=jdbc:h2:mem:db_users;MODE=MYSQL;\
      INIT=RUNSCRIPT FROM './src/main/resources/init-table-user.sql'
    spring.datasource.username=
    spring.datasource.password=
    
url后头那个脚本是建表的语句；测试类：`org.lanqiao.restdemo.DataSourceTest`，`org.lanqiao.restdemo.repository.UserMapperTest`

# profile

在主配置文件中：

    # 激活dev
    spring.profiles.active=dev
    
上面的数据库配置信息用于开发环境，因此其文件命名为

    application-dev.properties
    
# 引入mybatis

1. 添加依赖；

```    
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>${mybatis-spring-boot-starter.version}</version>
</dependency>
```

2. 编写Mapper接口，见：org.lanqiao.restdemo.repository.UserMapper
3. 配置MyBatis，扫描mapper接口：org.lanqiao.restdemo.config.MybatisConfig
4. 编写测试类：org.lanqiao.restdemo.repository.UserMapperTest
5. 注意测试类可以限定Spring容器加载哪些bean，在测试Mapper的时候没必要启动MVC

