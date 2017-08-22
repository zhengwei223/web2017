# 传统ssm+模板完成web应用，项目种子

## ssm

1. 从web.xml开始了解顶级spring容器的配置文件在哪里，mvc配置文件在哪里

  context中我们配置了自动扫描，`@Service`，`@AutoWired`等注解都会被Spring容器解析
  
  mvc中我们也配置了自动扫描，不过只关注`@Controller`和`@ControllerAdvice`，
  同时我们使用了`FastJsonHttpMessageConverter`来处理json格式的出入参数的转换
  
  定义了处理器拦截器，对请求参数进行签名验证，[开放api接口签名验证 - CodeL](http://www.cnblogs.com/codelir/p/5327462.html);同时示范了在xml
  中如何引入一个用`@Configuration`配置的容器，见`spring-mvc.xml`
  
  定义了内部异常转Http rest响应的逻辑，见`org.zhengwei.web.rest.RestHandlerExceptionResolver`。
  更多信息请看[Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
  
  
  
2. mybatis我们使用了tk的通用Mapper，自定义了一个项目级的Mapper接口继承tk通用的系列Mapper接口，并把这个Mapper作为myBatis扫描的标识。[MyBatis通用Mapper](https://mapperhelper.github.io/docs/).

使用了MyBatis通用分页插件，注意看context.xml中org.mybatis.spring.SqlSessionFactoryBean的plugins配置。

3. org.lanqiao.rbac.core包下是一些通用的基础类，他们是Mapper的基类，Service接口和Service抽象基类，还有用来给Service抛的异常，还有生成rest结果的Generator

## sql脚本

`\web2017\rbac-traditional\src\test\resources\sql`下是本项目的示例脚本，到数据库（mysql）中执行后，生成后端代码，配合前端模板就可以呈现web效果

## 代码生成器

Java+config形式的代码生成器：`org.zhengwei.ssm.generator.CodeGenerator`；

调用方法见`\web2017\rbac-traditional\src\test\java\org\lanqiao\rbac\generator\CodeGeneratorTest.java`，有两种选择：生成Mapper的xml文件和Java接口，或者生成Service和Controller

`genServiceAndController`，参数要给模板所在路径的绝对位置，在rbac-traditional和commons模块下都有示例文件，你可以拷贝到任何位置，在生成代码的时候把绝对路径给进去即可

当然连续调这两个方法一次性生成这四种文件也是可以的。

生成代码之前要做配置，两个配置文件：generatorConfig.xml和application-dev1.properties，前者依赖后者，generatorConfig.xml是MBG的配置文件，不同支出在table的配置可以用通配符且通配符*之前的部分会作为表的前缀来解析这样在生成代码的时候所有DomainObject都是去掉前缀了的

application-dev1.properties是一些参数，这些参数是路径、包名和jdbc连接信息，以后有时间把每个参数的意义说明一下。

## 代码生成之后

### 测试

到web2017目录执行：`mvn package install -DskipTests`，执行打包和安装

然后切换到rbac-traditional目录，执行`mvn jetty:run`就会启动web服务器，这时通过访问[http://addr:port/rbac-traditional/rbac/account?page=1&size=10](http://addr:port/rbac-traditional/rbac/account?page=1&size=10)应该是能看到json数据的

### 扩展

××Controller可以自己扩展方法

××Service也是，不过很多基础方法都在Service接口中定义并且由AbstractService实现了，所以要写自己的方法之前可以看看是否已经实现了

要扩展Mapper方法，完全可以在xml中按原来的方式写一个named statement，然后将name作为方法名声明在具体的××Mapper中，不过在扩展之前应看看通用Mapper中是否已经提供了这些方法。

总之我们是基于通用Mapper来完成持久层，所以大家使用本示例要先熟悉[MyBatis通用Mapper](https://mapperhelper.github.io/docs/).

jsp/freemarker

mysql