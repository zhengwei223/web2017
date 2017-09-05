# ssm配置+rest风格+json数据交换，项目种子

相对传统模板技术，它可以适应多客户端如手机端

## 目录结构

api包：rest风格的Controller

  所有Controller直接标注为RestController=Controller+ResponseBody
  
aspect：aop切面逻辑

base包：一些通用的基础类，他们是Mapper的基类，Service接口和Service抽象基类

config：系统配置或者spring Java风格的bean配置

entity：实体类

repository：传统的DAO层，mybatis的Mapper接口

service：业务实现层，事务控制在这一层，缓存也在这一层

resources：配置文件；

  `\src\test\resources\sql`下是本项目的示例脚本，到数据库（mysql）中执行后，就可以调用代码生成器生成Controller、Service、mapper文件、mapper接口等内容

## 开发顺序（如果是新项目）

### 项目骨架

1. 拷贝本项目所有代码到任意支持maven的IDE中
2. 删除api&entity&repository&service及mapper文件
3. 规划并修改包名，主要是groupID和artifactID，子包建议用本项目的名称，
否则还得精心修改代码生成器中的相关内容（代码生成器按api/entity/service/repository/mapper这样的命名约定）
4. 修改spring配置文件，主要是改改包名
5. 配置代码生成器，并调用代码生成器客户端代码来生成api/entity/service/repository/mapper五个文件夹
6. 将生成的代码拷贝至项目对应目录中
7. 编写单元测试类，测试你的Service或Mapper，也可以启动web server访问一个API试试能否得到数据列表（json）

项目的外部依赖，我们依赖了commons模块，见pom.xml，要正式部署当前项目需要到commons目录下去执行
    
    mvn install  # 打包commons为jar包并安装到mvn仓库
    
也可以写一个脚本来完成mvn的打包、发布和启动jetty等任务，见rbac-service-quick-start.sh

至此项目骨架完成

### 正式开发后不要反复生成代码

正式开发开始之后，有可能发现以前的数据表、代码模板都设计得不是很好，但是现在已经基于原有骨架开发一段时间了；
这时千万别想着重新生成一次，这时最好的办法就是按传统开发方式去修改表-修改实体-修改Controller代码；
好在Mapper一层都是动态的通用工具，没啥好修改的。

### 程序员

项目骨架只是完成了项目的基础配置和所有单表的增删改查，以及集成了一些有用的工具、插件、基础类；

仍然有很多项目的业务功能需要程序员去开发；

根据不同的业务需求，程序员有以下任务：

- 根据API设计来扩展或修改Controller
- 扩展或修改Service
- 设计新的DTO，建议不要改Entity，因为它们和数据表是一一对应的
- 扩展或修改Mapper文件与Mapper接口


## 代码生成器

Java+config形式的代码生成器：`org.web2017.ssm.generator.CodeGenerator`；

调用方法见test目录下：`org.lanqiao.rbac.generator.CodeGeneratorClient`，有两种选择：生成Mapper的xml文件和Java接口，或者生成Service和Controller

genServiceAndController，参数要给模板所在路径的绝对位置，在rbac-rest-service和commons模块下都有示例文件，你可以拷贝到任何位置，在生成代码的时候把绝对路径给进去即可

当然连续调这两个方法一次性生成这四种文件也是可以的。

生成代码之前要做配置，两个配置文件：generatorConfig.xml和application-dev1.properties，前者依赖后者，generatorConfig.xml是MBG的配置文件，不同支出在table的配置可以用通配符且通配符*之前的部分会作为表的前缀来解析这样在生成代码的时候所有DomainObject都是去掉前缀了的

application-dev1.properties是一些参数，这些参数是路径、包名和jdbc连接信息，以后有时间把每个参数的意义说明一下。


## ssm整合开发的较佳实践

### 1. 配置 

  从web.xml开始了解顶级spring容器的配置文件在哪里，mvc配置文件在哪里

  context中我们配置了自动扫描，`@Service`，`@AutoWired`等注解都会被Spring容器解析
  
  mvc中我们也配置了自动扫描，不过只关注`@Controller`和`@ControllerAdvice`，
  我们使用了`FastJsonHttpMessageConverter`来处理json格式的出入参数的转换
  
  定义了处理器拦截器，对请求参数进行签名验证，[开放api接口签名验证 - CodeL](http://www.cnblogs.com/codelir/p/5327462.html);
  
  同时示范了在xml中如何引入一个用`@Configuration`配置的容器，见`spring-mvc.xml`
  
  注：api签名验证的org.lanqiao.rbac.config.WebMvcConfig已被废弃，不过可以作为showcase告诉大家怎么写拦截器，以及怎么基于Java来配置spring容器。
  关于权限以及签名验证已经被挪到shiro相关代码中。

  
### 2. 关于rest的处理

所有的Controller都是`@RestController`


#### 首先是handler的返回值

  封装了统一的handler返回值：`org.web2017.web.rest.Result`结果生成器`org.web2017.web.rest.ResultGenerator`，在Controller中只需如此调用：
  
    @GetMapping
    public Result list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = userProfileService.findAll(pageNumber,pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

####  然后是异常的处理

  传统的web应用，出现内部错误时会返回一个500页面，但是rest服务端假设客户端并非一定是浏览器，所以只能响应一个状态码或者信息。
  我们需要后置处理所有内部异常，将其转换成json来进行响应。
   
  定义了内部异常转Http rest响应的逻辑，见`org.web2017.web.rest.RestHandlerExceptionResolver`。
  
### 3. 关于mybatis

#### 通用mapper

mybatis我们使用了tk的通用Mapper，自定义了一个项目级的Mapper接口继承tk通用的系列Mapper接口，并把这个Mapper作为myBatis扫描的标识。[MyBatis通用Mapper](https://mapperhelper.github.io/docs/).

请阅读`org.lanqiao.rbac.base.Mapper`都提供了哪些已经实现的db操作，勿要重复造轮子。只当有额外需求时才去mapper文件中新增语句并在mapper接口中声明方法。

因为有通用的Mapper和AbstractService的存在，完成单表的crud几乎不用写代码，程序员可关注于其他复杂的业务逻辑。

#### 分页插件

集成了MyBatis分页插件，用法：org.lanqiao.rbac.base.AbstractService.findAll(java.lang.Integer, java.lang.Integer)

      @Override
      public PageInfo findAll(Integer pageNumber, Integer pageSize) {
        return PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(new ISelect() {
          @Override
          public void doSelect() {
            mapper.selectAll();
          }
        });
      }
  
  
更多信息请看[Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
  
注意看context.xml中org.mybatis.spring.SqlSessionFactoryBean的plugins配置。

### 4. 多数据源

本示例完成了多数据源的自动切换，目标是Service的所有find/count方法用slave读，其他方法连接master进行写，适用于MySQL的主从模式实现读写分离

首先是配置多个数据源

    <!-- Hikari JDBC连接池 1-->
    <bean id="master" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
      ...
    </bean>
    <!-- Hikari JDBC连接池 2 -->
    <bean id="slave" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
      ...
    </bean>

然后是继承org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource实现一个自己的动态数据源切换，
见org.web2017.multiDataSources.DynamicDataSource

这个的路由逻辑是：从当前线程环境中取数据源bean的key，这样spring在mybatis需要数据源的时候就会根据determineCurrentLookupKey返回的key到IOC容器中查找一个DataSource的实例给它

配置这个动态数据源，并在sqlSessionFactory和transactionManager里面注入：

    <!--多源动态数据源-->
    <bean id="dataSource" class="org.web2017.multiDataSources.DynamicDataSource">
      <!-- 通过key-value关联数据源 -->
      <property name="targetDataSources">
        <map>
          <entry value-ref="master" key="master"></entry>
          <entry value-ref="slave" key="slave"></entry>
        </map>
      </property>
      <property name="defaultTargetDataSource" ref="master"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        ...
        
但是什么时候往线程环境中放置合适的数据源（key）呢？我们用AOP拦截所有的service方法，
在service方法调用之前(而且必须是事务开始之前)判断service方法的名称，如果是find或者count方法则在线程中放置只读源的key
，见`org.lanqiao.rbac.aspect.DynamicDataSourceAdvisor`。

项目技术负责人应该关注这个切面，特别是切入点表达式是否符合你的项目包路径。

在开发阶段，没有主从多数据源，就把多个DataSource的连接信息写成一样就好了。
 
### 5. 多套spring配置

实际开发中，开发环境和测试环境以及产品环境是有区别的，而且大家都愿意连接自己的数据源，如果每个人都改公共配置，就会造成冲突。

spring支持profile切换来整体切换配置，因此在spring配置文件中大家会看到：

    <beans profile="production">
    <context:property-placeholder ignore-resource-not-found="true"
                                  location="classpath*:/application.properties"/>
    ...
    </beans>
    
    <!-- local dev1环境 -->
    <beans profile="dev1">
    <context:property-placeholder ignore-resource-not-found="true"
                                  location="classpath*:/application.properties,
                      classpath*:/application-dev1.properties"/>
    ...
    </beans>
    <beans profile="dev2">
    <context:property-placeholder ignore-resource-not-found="true"
                                  location="classpath*:/application.properties,
                      classpath*:/application-dev2.properties"/>
    ...
    </beans>
    
在运行程序或者启动web server时，spring该加载哪一套配置呢？在以下地方体现：

- 测试类：
    
    @ContextConfiguration("classpath:spring-context.xml")
    @ActiveProfiles("dev2")
    public class BaseTester extends AbstractJUnit4SpringContextTests {
    }
   
- jetty插件

      <!--jetty插件-->
      <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>${jetty.version}</version>
        <configuration>
          <scanIntervalSeconds>2</scanIntervalSeconds>
          <reload>manual</reload>
          <systemProperties>
            <systemProperty>
              <name>spring.profiles.active</name>
              <value>test</value>
            </systemProperty>
          </systemProperties>
          <useTestClasspath>true</useTestClasspath>

          <webAppConfig>
            <contextPath>/${project.artifactId}</contextPath>
          </webAppConfig>
          <stopKey/>
          <stopPort/>
        </configuration>
      </plugin>

- 环境变量

    java ... -Dspring.profiles.active=development
    
### 6. Service测试

一般我们会针对Service进行单元测试，我们可以先写一个基础的Tester类，在里面设定spring的配置文件和profile，
这样对于所有的service不用每次去配置这些东西

    @ContextConfiguration("classpath:spring-context.xml")
    @ActiveProfiles("dev2")
    public class Tester extends AbstractJUnit4SpringContextTests {
      ...
    }
    
测试类一定要指定`@ActiveProfiles`，否则可能加载错误的jdbc连接信息。

然后我们的Service测试类只要继承这个类，用Autowired注入真正的Service实现类，在test方法里面调用其方法并对结果进行断言即可
 
### 7.API测试

API（web接入层）测试，可以用restTemplate，但是要启动web server，如果我们不想启动web server，该怎么测试呢？

见[API-TEST](./APITEST.MD)

