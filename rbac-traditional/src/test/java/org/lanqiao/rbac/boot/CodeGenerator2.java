package org.lanqiao.rbac.boot;

import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.zhengwei.commons.Exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 *
 * @author zhengwei
 */
public class CodeGenerator2 {
  String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
  String AUTHOR;//@author
  private static final String BLANK_STRING = "";

  private String projectPath;
  private final String TEMPLATE_FILE_PATH;
  private String BASE_PACKAGE;
  private String CONTROLLER_FTL;
  private String JAVA_PATH;
  private String RESOURCES_PATH;
  private String BASE_PACKAGE_PATH;
  private String PACKAGE_PATH_SERVICE;
  private String PACKAGE_PATH_SERVICE_IMPL;
  private String PACKAGE_PATH_CONTROLLER;

  private String JDBC_USERNAME;
  private String JDBC_URL;
  private String JDBC_DIVER_CLASS_NAME;
  private String JDBC_PASSWORD;

  private String contextId;
  boolean overwrite = true;

  private String propertiesFileName = "application-development.properties";
  String configFileName = "generatorConfig.xml";

  private Properties props;
  List<String> warnings = new ArrayList<String>();
  ConfigurationParser cp = new ConfigurationParser(warnings);
  Configuration config;
  DefaultShellCallback callback = new DefaultShellCallback(overwrite);
  private MyBatisGenerator myBatisGenerator;
  final GeneratedKey generatedKey = new GeneratedKey("id", "Mysql", true, null);
  private Context context;

  private freemarker.template.Configuration freemarkerCfg;
  private Set<TableInfo1> tableInfoSet = new HashSet<>();

  public CodeGenerator2(String propName, String configName) {
    this();
    this.propertiesFileName = propName;
    this.configFileName = configName;

  }

  public CodeGenerator2() {
    Resource resource = new ClassPathResource("application-development.properties");
    Properties props = new Properties();
    try {
      props.load(resource.getInputStream());
      AUTHOR = props.getProperty("gen.author");

      JDBC_DIVER_CLASS_NAME = props.getProperty("jdbc.driver");
      JDBC_URL = props.getProperty("jdbc.url");
      JDBC_USERNAME = props.getProperty("jdbc.username");
      JDBC_PASSWORD = props.getProperty("jdbc.password");

      contextId = props.getProperty("gen.context.id");
      projectPath = props.getProperty("project.path");
      BASE_PACKAGE = props.getProperty("gen.basepackage");
      JAVA_PATH = props.getProperty("java.path");
      RESOURCES_PATH = props.getProperty("resources.path");
      BASE_PACKAGE_PATH = "/" + BASE_PACKAGE.replaceAll("\\.", "/") + "/";//项目基础包路径
      boolean NEED_REST = Boolean.parseBoolean(props.getProperty("rest"));
      CONTROLLER_FTL = "controller" + (NEED_REST ? "-restful" : "") + ".ftl"; // controller.ftl
      TEMPLATE_FILE_PATH = projectPath + "/src/test/resources/generator/template";
      PACKAGE_PATH_SERVICE = BASE_PACKAGE_PATH + "/service/";//生成的Service存放路径;
      PACKAGE_PATH_SERVICE_IMPL = BASE_PACKAGE_PATH + "/service/impl/";//生成的Service实现存放路径 ;
      PACKAGE_PATH_CONTROLLER = BASE_PACKAGE_PATH + "/web/";//生成的Controller实现存放路径;

      config = cp.parseConfiguration(new ClassPathResource(configFileName).getInputStream());
      context = config.getContext(contextId);
      Class.forName(JDBC_DIVER_CLASS_NAME);
    } catch (Exception e) {
      throw Exceptions.unchecked(e);
    }
  }

  public CodeGenerator2 addTable(String tableName) {
    return addTable(tableName, null, null);
  }

  public CodeGenerator2 addTable(String tableName, String tablePrefix, String columnPrefix) {
    tableInfoSet.add(new TableInfo1(tableName, tablePrefix, columnPrefix));
    context.getTableConfigurations().add(configTable(tableName, tablePrefix, columnPrefix));
    return this;
  }

  public CodeGenerator2 addTables(String tablePrefix) {
    return addTables(tablePrefix, null);
  }

  public CodeGenerator2 addTables(String tablePrefix, String columnPrefix) {
    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);) {
      DatabaseMetaData dbMetaData = connection.getMetaData();
      ResultSet rs = dbMetaData.getTables(null, null, null, null);
      Set<TableInfo> tableInfos = new HashSet<>();
      while (rs.next()) {
        final String tableName = rs.getString("TABLE_NAME");
        if (tablePrefix.equals("*") || tableName.startsWith(tablePrefix)) {
          final String prefix = tablePrefix.equals("*") ? null : tablePrefix;
          tableInfoSet.add(new TableInfo1(tableName, prefix, columnPrefix));
          context.getTableConfigurations().add(configTable(tableName, prefix, columnPrefix));
        }
      }
      return this;
    } catch (Exception e) {
      throw Exceptions.unchecked(e);
    }
  }
  private String tablePrefix;

  public CodeGenerator2 setTablePrefix(String tablePrefix) {
    this.tablePrefix = tablePrefix;
    return this;
  }

  private String columnPrefix;

  public CodeGenerator2 setColumnPrefix(String columnPrefix) {
    this.columnPrefix = columnPrefix;
    return this;
  }

  public CodeGenerator2 removePrefix() {
    List<TableConfiguration> tableConfigs = context.getTableConfigurations();
    for (TableConfiguration conf : tableConfigs) {
      final String tableName = conf.getTableName();
      // 处理前缀匹配的
      if (StringUtils.isNotEmpty(tablePrefix) && tableName.startsWith(tablePrefix)) {
        String domainObjectName = tableName.replaceFirst(tablePrefix, BLANK_STRING);
        conf.setDomainObjectName(tableNameConvertUpperCamel(domainObjectName));
      }
      //列前缀
      if (StringUtils.isNotEmpty(columnPrefix)) {
        ColumnRenamingRule columnRenamingRule = conf.getColumnRenamingRule();
        columnRenamingRule.setSearchString(columnPrefix);
        columnRenamingRule.setReplaceString(BLANK_STRING);
        conf.setColumnRenamingRule(columnRenamingRule);
      }
    }
    return this;
  }

  private TableConfiguration configTable(String tableName, String tablePrefix, String columnPrefix) {
    TableConfiguration tableConfiguration = new TableConfiguration(context);
    tableConfiguration.setTableName(tableName);
    tableConfiguration.setGeneratedKey(generatedKey);
    // 表前缀
    if (StringUtils.isNotEmpty(tablePrefix)) {
      String domainObjectName = tableName.replaceFirst(tablePrefix, BLANK_STRING);
      tableConfiguration.setDomainObjectName(tableNameConvertUpperCamel(domainObjectName));
    }
    //列前缀
    if (StringUtils.isNotEmpty(columnPrefix)) {
      final ColumnRenamingRule columnRenamingRule = new ColumnRenamingRule();
      columnRenamingRule.setSearchString(columnPrefix);
      columnRenamingRule.setReplaceString(BLANK_STRING);
      tableConfiguration.setColumnRenamingRule(columnRenamingRule);
    }
    return tableConfiguration;
  }

  public void generate() {
    try {
      myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
    } catch (Exception e) {
      throw Exceptions.unchecked(e);
    }
    System.out.println("Mapper生成成功");
    //  service 和 controller 可选
    genService();
    genController();
  }

  private void genController() {
    List<TableConfiguration> tableConfigs = context.getTableConfigurations();
    for (TableConfiguration conf : tableConfigs) {
      String domainName = conf.getDomainObjectName();
      if (StringUtils.isNotEmpty(tablePrefix))
        domainName = domainName.replaceFirst(tablePrefix, BLANK_STRING);
      try {
        buildFreemarkerConfiguration();
        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        data.put("baseRequestMapping", tableNameConvertMappingPath(domainName));
        String modelNameUpperCamel = tableNameConvertUpperCamel(domainName);
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", tableNameConvertLowerCamel(domainName));
        data.put("basePackage", BASE_PACKAGE);


        File file = new File(projectPath + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        freemarkerCfg.getTemplate(CONTROLLER_FTL).process(data, new FileWriter(file));

        System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
      } catch (Exception e) {
        throw new RuntimeException("生成Controller失败", e);
      }
    }
  }

  public void genService() {
    List<TableConfiguration> tableConfigs = context.getTableConfigurations();
    for (TableConfiguration conf : tableConfigs) {
      String domainName = conf.getDomainObjectName();
      if (StringUtils.isNotEmpty(tablePrefix))
        domainName = domainName.replaceFirst(tablePrefix, BLANK_STRING);
      try {
        buildFreemarkerConfiguration();

        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        String modelNameUpperCamel = tableNameConvertUpperCamel(domainName);
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", tableNameConvertLowerCamel(domainName));
        data.put("basePackage", BASE_PACKAGE);

        File file = new File(projectPath + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        freemarkerCfg.getTemplate("service.ftl").process(data,
            new FileWriter(file));
        System.out.println(modelNameUpperCamel + "Service.java 生成成功");

        File file1 = new File(projectPath + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
        if (!file1.getParentFile().exists()) {
          file1.getParentFile().mkdirs();
        }
        freemarkerCfg.getTemplate("service-impl.ftl").process(data,
            new FileWriter(file1));
        System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
      } catch (Exception e) {
        throw new RuntimeException("生成Service失败", e);
      }
    }
  }

  private void buildFreemarkerConfiguration() throws IOException {
    if (freemarkerCfg != null)
      return;
    freemarkerCfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
    freemarkerCfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
    freemarkerCfg.setDefaultEncoding("UTF-8");
    freemarkerCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
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

  private class TableInfo1 {
    String tableName;
    String tablePrefix;
    String columnPrefix;

    public TableInfo1(String tableName) {
      this.tableName = tableName;
    }

    public TableInfo1(String tableName, String tablePrefix, String columnPrefix) {
      this.tableName = tableName;
      this.tablePrefix = tablePrefix;
      this.columnPrefix = columnPrefix;
    }

  }

  public static void main(String[] args) {
    new CodeGenerator2().generate();
  }
}

