package org.lanqiao.rbac.boot;

import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.zhengwei.commons.Exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 * @author zhengwei
 */
public  class CodeGenerator {
  public static final String OBJECT_BEGINNING_DELIMITER = "`";
  public static final String OBJECT_ENDING_DELIMITER = "`";
  public static final String BLANK_STRING = "";
  /*--------------需要修改的常量 begin----------*/
  private static final String GROUP_ID = "org.lanqiao";
  private static final String ARTIFACT_ID = "rbac";
  /*---包的配置---*/
  //项目基础包名称，根据自己的公司项目修改
  public static final String BASE_PACKAGE = GROUP_ID + "." + ARTIFACT_ID;
  public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".repository";//Mapper所在包
  public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";//Model所在包
  public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名
  //项目在硬盘上的基础路径,绝对路径
  private static final String PROJECT_PATH = "/Users/zhengwei/workspace/web2017/rbac-traditional";
  //是否需要rest风格的controller
  private static final Boolean NEED_REST = false;
  /*JDBC配置，请修改为你项目的实际配置*/
  private static final String JDBC_URL = "jdbc:mysql://10.100.40.185:3306/test";
  private static final String JDBC_USERNAME = "lemoncs";
  private static final String JDBC_PASSWORD = "lemoncs";
  private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
  private static final String AUTHOR = "zhengwei";//@author
  private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
  private static final String[] prefixes = {"common_","common_rbac_"};
  /*--------------需要修改的常量 end----------*/

  /*---文件路径的配置---*/
  //模板文件所在相对路径
  private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";
  //控制器模板名
  private static final String CONTROLLER_FTL = "controller" + (NEED_REST ? "-restful" : "") + ".ftl"; // controller.ftl
  private static final String JAVA_PATH = "/src/main/java"; //java文件路径
  private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径
  private static final String BASE_PACKAGE_PATH = "/" + BASE_PACKAGE.replaceAll("\\.", "/") + "/";//项目基础包路径
  private static final String PACKAGE_PATH_SERVICE = BASE_PACKAGE_PATH + "/service/";//生成的Service存放路径
  private static final String PACKAGE_PATH_SERVICE_IMPL = BASE_PACKAGE_PATH + "/service/impl/";//生成的Service实现存放路径
  private static final String PACKAGE_PATH_CONTROLLER = BASE_PACKAGE_PATH + "/web/";//生成的Controller实现存放路径
  private static Context context;
  private static JDBCConnectionConfiguration jdbcConnectionConfiguration;
  private static PluginConfiguration pluginConfiguration;
  private static JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;
  private static SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;
  private static JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;
  private static freemarker.template.Configuration freemarkerCfg;

  public static void main(String[] args) {
    final Set<TableInfo> tableInfoSet = autoScanTables();
    TableInfo[] infos = new TableInfo[tableInfoSet.size()];
    genCode(tableInfoSet.toArray(infos));
  }

  public static Set<TableInfo> autoScanTables() {
    try {
      Class.forName(JDBC_DIVER_CLASS_NAME);
    } catch (ClassNotFoundException e) {
      Exceptions.unchecked(e);
    }
    try (
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
      DatabaseMetaData dbMetaData = conn.getMetaData();
      ResultSet rs = dbMetaData.getTables(null, null, null, null);
      Set<TableInfo> tableInfos = new HashSet<>();
      while (rs.next()) {
        final String table_name = rs.getString("TABLE_NAME");
        if (ArrayUtils.contains(prefixes, "*")) {
          tableInfos.add(new TableInfo(table_name));
          continue;
        }
        for (String pre : prefixes) {
          if (table_name.startsWith(pre)) {
            tableInfos.add(new TableInfo(table_name, pre, null));
            continue;
          }
        }
      }
      return tableInfos;
    } catch (SQLException e) {
      throw Exceptions.unchecked(e);
    }
  }

  public static void genCode(TableInfo... tables) {
    prepareContext();
    genModelAndMapper(tables);
    for (TableInfo table : tables) {
      //根据需求生成，不需要的注掉，模板有问题的话可以自己修改。
      genService(table);
      genController(table);
    }
  }

  public static void genModelAndMapper(TableInfo[] tables) {

    try {
      for (TableInfo table : tables) {
        context.addTableConfiguration(configTable(table));
      }
      Configuration config = buildConfiguration();
      DefaultShellCallback callback = new DefaultShellCallback(true);
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, new ArrayList<>());
      myBatisGenerator.generate(null);
    } catch (Exception e) {
      throw new RuntimeException("生成Model和Mapper失败", e);
    }
  }

  private static Configuration buildConfiguration() throws InvalidConfigurationException {
    Configuration config = new Configuration();
    config.addContext(context);
    config.validate();
    return config;
  }

  private static void prepareContext() {
    if (context == null) {
      configContext();
    }
    if (jdbcConnectionConfiguration == null) {
      configJdbc();
    }
    if (pluginConfiguration == null) {
      configPlugin();
    }
    if (javaModelGeneratorConfiguration == null) {
      configJavaModel();
    }
    if (sqlMapGeneratorConfiguration == null) {
      configSqlMapper();
    }
    if (javaClientGeneratorConfiguration == null) {
      configJavaClient();
    }
  }

  private static void configContext() {
    context = new Context(ModelType.HIERARCHICAL);
    context.setId("Potato");
    context.setTargetRuntime("MyBatis3Simple");
    context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, OBJECT_BEGINNING_DELIMITER);
    context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, OBJECT_ENDING_DELIMITER);
  }

  private static void configJdbc() {
    jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
    jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
    jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
    jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
    jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
  }

  private static void configPlugin() {
    pluginConfiguration = new PluginConfiguration();
    pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
    pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
    context.addPluginConfiguration(pluginConfiguration);
  }

  private static void configJavaModel() {
    javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
    javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
    javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
    context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
  }

  private static void configSqlMapper() {
    sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
    sqlMapGeneratorConfiguration.setTargetPackage("mapper");
    context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
  }

  private static void configJavaClient() {
    javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
    javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
    javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
    javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
    context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
  }

  private static TableConfiguration configTable(TableInfo table) {
    TableConfiguration tableConfiguration = new TableConfiguration(context);
    String tableName = table.tableName;
    tableConfiguration.setTableName(tableName);
    tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
    // 表前缀
    if (StringUtils.isNotEmpty(table.tablePrefix)) {
      String domainObjectName = tableName.replaceFirst(table.tablePrefix, "");

      tableConfiguration.setDomainObjectName(tableNameConvertUpperCamel(domainObjectName));
    }
    //列前缀
    if (StringUtils.isNotEmpty(table.columnPrefix)) {
      final ColumnRenamingRule columnRenamingRule = new ColumnRenamingRule();
      columnRenamingRule.setSearchString(table.columnPrefix);
      columnRenamingRule.setReplaceString("");
      tableConfiguration.setColumnRenamingRule(columnRenamingRule);
    }
    return tableConfiguration;
  }

  /**
   * 生成Service类
   * @param table
   */
  public static void genService(TableInfo table) {
    String tableName = table.tableName;
    if (StringUtils.isNotEmpty(table.tablePrefix))
      tableName = tableName.replaceFirst(table.tablePrefix, BLANK_STRING);
    try {
      freemarker.template.Configuration cfg = getConfiguration();

      Map<String, Object> data = new HashMap<>();
      data.put("date", DATE);
      data.put("author", AUTHOR);
      String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
      data.put("modelNameUpperCamel", modelNameUpperCamel);
      data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
      data.put("basePackage", BASE_PACKAGE);

      File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      cfg.getTemplate("service.ftl").process(data,
          new FileWriter(file));
      System.out.println(modelNameUpperCamel + "Service.java 生成成功");

      File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
      if (!file1.getParentFile().exists()) {
        file1.getParentFile().mkdirs();
      }
      cfg.getTemplate("service-impl.ftl").process(data,
          new FileWriter(file1));
      System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
    } catch (Exception e) {
      throw new RuntimeException("生成Service失败", e);
    }
  }

  public static void genController(TableInfo table) {
    String tableName = table.tableName;
    if (StringUtils.isNotEmpty(table.tablePrefix))
      tableName = tableName.replaceFirst(table.tablePrefix, BLANK_STRING);
    try {
      freemarker.template.Configuration cfg = getConfiguration();

      Map<String, Object> data = new HashMap<>();
      data.put("date", DATE);
      data.put("author", AUTHOR);
      data.put("baseRequestMapping", tableNameConvertMappingPath(tableName));
      String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
      data.put("modelNameUpperCamel", modelNameUpperCamel);
      data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
      data.put("basePackage", BASE_PACKAGE);


      File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      cfg.getTemplate(CONTROLLER_FTL).process(data, new FileWriter(file));

      System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
    } catch (Exception e) {
      throw new RuntimeException("生成Controller失败", e);
    }

  }

  private static freemarker.template.Configuration getConfiguration() throws IOException {
    if (freemarkerCfg!=null)
      return freemarkerCfg;
    freemarkerCfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
    freemarkerCfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
    freemarkerCfg.setDefaultEncoding("UTF-8");
    freemarkerCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
    return freemarkerCfg;
  }

  private static String tableNameConvertLowerCamel(String tableName) {
    StringBuilder result = new StringBuilder();
    if (tableName != null && tableName.length() > 0) {
      boolean flag = false;
      for (int i = 0; i < tableName.length(); i++) {
        char ch = tableName.charAt(i);
        if ("_".charAt(0) == ch) {
          flag = true;
        } else {
          if (flag) {
            result.append(Character.toUpperCase(ch));
            flag = false;
          } else {
            result.append(ch);
          }
        }
      }
    }
    return result.toString();
  }

  private static String tableNameConvertUpperCamel(String tableName) {
    String camel = tableNameConvertLowerCamel(tableName);
    return camel.substring(0, 1).toUpperCase() + camel.substring(1);

  }

  private static String tableNameConvertMappingPath(String tableName) {
    return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
  }

  private static class TableInfo {
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

}

