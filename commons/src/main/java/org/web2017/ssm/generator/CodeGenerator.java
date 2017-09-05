package org.web2017.ssm.generator;

import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.web2017.commons.Exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。<br/>
 * 该工具类需要MBG的配置文件和properties属性文件<br />
 * 使用方法参考http://blog.csdn.org/zhengwei223...
 *
 * @author web2017
 */
public class CodeGenerator {
  private static final Logger LOGGER = LoggerFactory.getLogger( CodeGenerator.class );

  //from constructor or keep default
  private String propertiesPath = "/application-dev1.properties";
  private String configPath = "/generatorConfig.xml";

  //fields with default value
  private static final String BLANK_STRING = "";
  private final String DATE = new SimpleDateFormat( "yyyy/MM/dd" ).format( new Date() );//@date
  private final List<String> WARNINGS = new ArrayList<String>();
  private final DefaultShellCallback CALLBACK = new DefaultShellCallback( true );

  // from configuration
  private String AUTHOR;//@author
  private String CONTEXTID;
  private String PROJECT_PATH;
  //private final String TEMPLATE_FILE_PATH;
  private String BASE_PACKAGE;
  private String CONTROLLER_FTL;
  private String JAVA_PATH;
  private String RESOURCES_PATH;
  private String BASE_PACKAGE_PATH;
  private String PACKAGE_PATH_SERVICE;
  // private final String PACKAGE_PATH_SERVICE_IMPL;
  private String PACKAGE_PATH_CONTROLLER;
  private String JDBC_DIVER_CLASS_NAME;
  private String JDBC_URL;
  private String JDBC_USERNAME;
  private String JDBC_PASSWORD;

  //mbg config
  private Configuration config;
  private Context context;

  //freemarker config
  private freemarker.template.Configuration freemarkerCfg;
  private boolean need_rest;
  private boolean dealed = false;

  public CodeGenerator() {
    init();
  }

  public CodeGenerator(String propertiesPath, String mgbXmlPath) {
    this.propertiesPath = propertiesPath;
    this.configPath = mgbXmlPath;
    init();
  }

  private void init() {
    Resource resource = new ClassPathResource( propertiesPath );
    Properties props = new Properties();
    try {
      props.load( resource.getInputStream() );
      AUTHOR = props.getProperty( "gen.author" );
      CONTEXTID = props.getProperty( "gen.context.id" ).trim();
      PROJECT_PATH = props.getProperty( "project.path" ).trim();
      File projPathFile = new File( PROJECT_PATH );
      if (projPathFile.exists() == false) {
        projPathFile.mkdirs();
      }
      //TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";
      BASE_PACKAGE = props.getProperty( "gen.basepackage" ).trim();
      need_rest = Boolean.parseBoolean( props.getProperty( "rest" ).trim() );
      CONTROLLER_FTL = "controller" + (need_rest ? "-restful" : "") + ".ftl"; // controller.ftl
      JAVA_PATH = props.getProperty( "java.path" ).trim();
      File javaPathFile = new File( PROJECT_PATH, JAVA_PATH );
      if (javaPathFile.exists() == false) {
        javaPathFile.mkdirs();
      }
      RESOURCES_PATH = props.getProperty( "resources.path" ).trim();
      File resourcePathFile = new File( PROJECT_PATH, RESOURCES_PATH );
      if (resourcePathFile.exists() == false) {
        resourcePathFile.mkdirs();
      }
      BASE_PACKAGE_PATH = "/" + BASE_PACKAGE.replaceAll( "\\.", "/" ) + "/";//项目基础包路径
      PACKAGE_PATH_SERVICE = BASE_PACKAGE_PATH + "/service/";//生成的Service存放路径;
      // PACKAGE_PATH_SERVICE_IMPL = BASE_PACKAGE_PATH + "/service/impl/";//生成的Service实现存放路径 ;
      if (need_rest)
        PACKAGE_PATH_CONTROLLER = BASE_PACKAGE_PATH + "/api/";//生成的API存放路径;
      else
        PACKAGE_PATH_CONTROLLER = BASE_PACKAGE_PATH + "/web/";//生成的Controller存放路径;

      JDBC_DIVER_CLASS_NAME = props.getProperty( "jdbc.driver" ).trim();
      JDBC_URL = props.getProperty( "jdbc.url" ).trim();
      JDBC_USERNAME = props.getProperty( "jdbc.username" ).trim();
      JDBC_PASSWORD = props.getProperty( "jdbc.password" ).trim();


      ConfigurationParser cp = new ConfigurationParser( WARNINGS );
      config = cp.parseConfiguration( new ClassPathResource( configPath ).getInputStream() );
      context = config.getContext( CONTEXTID );

      //load jdbc driver
      Class.forName( JDBC_DIVER_CLASS_NAME );
    } catch (Exception e) {
      throw Exceptions.unchecked( e );
    }
  }

  public void generateMapper() {
    dealTables();
    try {
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator( config, CALLBACK, WARNINGS );
      myBatisGenerator.generate( null );
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug( "代码成功生成，路径：" + PROJECT_PATH );
      }
    } catch (InvalidConfigurationException | SQLException | IOException | InterruptedException e) {
      Exceptions.unchecked( e );
    }
  }


  /**
   * 解析所有table配置，正确地和数据库中的表对应起来，形成新的TableConfiguration列表
   */
  private void dealTables() {
    if (!dealed) {
      List<TableConfiguration> tcs = context.getTableConfigurations();
      Set<TableConfiguration> newTcs = new HashSet<>();
      Set<String> dbTableNameSet = getDbTableNames();
      for (TableConfiguration tc : tcs) {
        dealTable( newTcs, dbTableNameSet, tc );
      }
      tcs.clear();
      tcs.addAll( newTcs );
      dealed = true;
    }
  }

  /**
   * 模板文件的路径
   * @param templateDir
   */
  public void genServiceAndController(String templateDir) {
    dealTables();
    List<TableConfiguration> tableConfigs = context.getTableConfigurations();
    for (TableConfiguration conf : tableConfigs) {
      String domainName = conf.getDomainObjectName();
      try {
        //build config
        buildFreemarkerConfiguration( templateDir );
        //prepare data used in template
        Map<String, Object> data = new HashMap<>();
        data.put( "date", DATE );
        data.put( "author", AUTHOR );
        data.put( "baseRequestMapping", tableNameConvertMappingPath( conf.getTableName() ) );
        data.put( "modelNameUpperCamel", domainName );
        String modelNameLowerCamel = domainName.substring( 0, 1 ).toLowerCase() + domainName.substring( 1 );
        data.put( "modelNameLowerCamel", modelNameLowerCamel );
        data.put( "basePackage", BASE_PACKAGE );

        String javaFileName = domainName + "Service.java";
        File file = new File( PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + javaFileName );
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        freemarkerCfg.getTemplate( "service.ftl" ).process( data, new FileWriter( file ) );
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug( "路径：" + PROJECT_PATH );
          LOGGER.debug( javaFileName + "生成成功" );
        }
        // File file1 = new File( PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + domainName + "ServiceImpl.java" );
        // if (!file1.getParentFile().exists()) {
        //   file1.getParentFile().mkdirs();
        // }
        // freemarkerCfg.getTemplate( "service-impl.ftl" ).process( data,
        //     new FileWriter( file1 ) );
        // System.out.println( domainName + "ServiceImpl.java 生成成功" );

        File file2 = new File( PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + domainName + (need_rest ? "API" : "Controller") + ".java" );
        if (!file2.getParentFile().exists()) {
          file2.getParentFile().mkdirs();
        }
        freemarkerCfg.getTemplate( CONTROLLER_FTL ).process( data, new FileWriter( file2 ) );
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug( "路径：" + PROJECT_PATH );
          LOGGER.debug( domainName + (need_rest ? "API" : "Controller") + ".java 生成成功" );
        }
      } catch (Exception e) {
        Exceptions.unchecked( e );
      }
    }
  }


  private void buildFreemarkerConfiguration(String TEMPLATE_FILE_PATH) throws IOException {
    if (freemarkerCfg != null)
      return;
    freemarkerCfg = new freemarker.template.Configuration( freemarker.template.Configuration.VERSION_2_3_23 );
    freemarkerCfg.setDirectoryForTemplateLoading( new File( TEMPLATE_FILE_PATH ) );
    freemarkerCfg.setDefaultEncoding( "UTF-8" );
    freemarkerCfg.setTemplateExceptionHandler( TemplateExceptionHandler.IGNORE_HANDLER );
  }

  /**
   * 只连一次数据库获得所有的表名，获取表名后关闭数据库连接
   * @return
   */
  private Set<String> getDbTableNames() {
    Set<String> dbTableNameSet = new HashSet<>();
    try (Connection connection = DriverManager.getConnection( JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD );) {
      DatabaseMetaData dbMetaData = connection.getMetaData();
      ResultSet rs = dbMetaData.getTables( null, null, null, null );
      while (rs.next()) {
        final String tableName = rs.getString( "TABLE_NAME" );
        dbTableNameSet.add( tableName );
      }
    } catch (Exception e) {
      LOGGER.error( JDBC_URL + "::" + JDBC_USERNAME + "::" + JDBC_PASSWORD, e );
      throw Exceptions.unchecked( e );
    }
    return dbTableNameSet;
  }

  /**
   * 根据原Table配置中是否有通配符来解析数据库中的表名，将符合要求的表配置后加入新配置容器
   * @param newTcs 新Table配置容器
   * @param dbTableNameSet 数据库表名容器
   * @param oldTc 原Table配置
   */
  private void dealTable(Set<TableConfiguration> newTcs, Set<String> dbTableNameSet, TableConfiguration oldTc) {
    String tcTableName = oldTc.getTableName();
    // 分两类，带通配符和不带通配符
    if (tcTableName.contains( "*" )) {
      Iterator<String> dbTableNameIter = dbTableNameSet.iterator();
      while (dbTableNameIter.hasNext()) {
        String dbTableName = dbTableNameIter.next();
        // 配置中表名为*，则所有表都符合要求
        if (tcTableName.equals( "*" )) {
          newTcs.add( configTable( dbTableName, null, oldTc.getGeneratedKey() ) );
        } else {
          int starIndex = tcTableName.indexOf( "*" );
          //截取前缀
          String prefix = tcTableName.substring( 0, starIndex );
          //满足前缀要求
          if (dbTableName.startsWith( prefix )) {
            newTcs.add( configTable( dbTableName, prefix, oldTc.getGeneratedKey() ) );
          }
        }
      }
    } else { // xml中配置的就是要使用的表名和domain
      if (newTcs.contains( tcTableName )) {
        newTcs.add( oldTc );
      } else {
        LOGGER.warn( "数据库中不存在此表：%s", tcTableName );
      }
    }

  }

  /**
   * 配置新的TableConfiguration
   * @param tableName 原生表名
   * @param tablePrefix 待去除的前缀
   * @param generatedKey 主键生成方式
   * @return
   */
  private TableConfiguration configTable(String tableName, String tablePrefix, GeneratedKey generatedKey) {
    TableConfiguration tableConfiguration = new TableConfiguration( context );
    tableConfiguration.setTableName( tableName );
    tableConfiguration.setGeneratedKey( generatedKey );
    // 表前缀
    if (StringUtils.isNotEmpty( tablePrefix )) {
      String domainObjectName = tableName.replaceFirst( tablePrefix, BLANK_STRING );
      tableConfiguration.setDomainObjectName( tableNameConvertUpperCamel( domainObjectName ) );
    }
    return tableConfiguration;
  }

  private static String tableNameConvertUpperCamel(String tableName) {
    String camel = tableNameConvertLowerCamel( tableName );
    return camel.substring( 0, 1 ).toUpperCase() + camel.substring( 1 );

  }

  private static String tableNameConvertLowerCamel(String tableName) {
    StringBuilder result = new StringBuilder();
    if (tableName != null && tableName.length() > 0) {
      boolean flag = false;
      for (int i = 0; i < tableName.length(); i++) {
        char ch = tableName.charAt( i );
        if ("_".charAt( 0 ) == ch) {
          flag = true;
        } else {
          if (flag) {
            result.append( Character.toUpperCase( ch ) );
            flag = false;
          } else {
            result.append( ch );
          }
        }
      }
    }
    return result.toString();
  }


  private static String tableNameConvertMappingPath(String tableName) {
    return "/" + (tableName.contains( "_" ) ? tableName.replaceAll( "_", "/" ) : tableName);
  }

  private class TableInfo {
    String tableName;
    String tablePrefix;
    String columnPrefix;

    public TableInfo(String tableName) {
      this.tableName = tableName;
    }

    public TableInfo(String tableName, String tablePrefix, String columnPrefix) {
      this.tableName = tableName;
      this.tablePrefix = tablePrefix;
      this.columnPrefix = columnPrefix;
    }

  }

  public static void main(String[] args) throws Exception {
    CodeGenerator codeGenerator = new CodeGenerator();
    codeGenerator.generateMapper();
    codeGenerator.genServiceAndController( "E:\\workspace\\web2017\\commons\\src\\main\\resources\\ssm_template_demo" );
  }
}
