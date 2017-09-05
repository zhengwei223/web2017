package org.lanqiao.rbac.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.web2017.commons.Exceptions;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * MyBatis原始的代码生成器使用demo
 * <br/>
 * 注意配置文件的
 * targetProject：目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录
 */
public class MBGDemo {
  public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    genMapper("/generatorConfig_traditional.xml","/application-development.properties");
  }

  private static void genMapper(String xmlPath,String propsPath) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    makeNecessaryDirs(propsPath);

    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(new ClassPathResource( xmlPath ).getInputStream());
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  private static void makeNecessaryDirs(String propsPath) {
    Resource resource = new ClassPathResource( propsPath );
    Properties props = new Properties();
    try {
      props.load( resource.getInputStream() );
      String PROJECT_PATH = props.getProperty( "project.path" ).trim();
      File projPathFile = new File( PROJECT_PATH );
      if (projPathFile.exists() == false) {
        projPathFile.mkdirs();
      }
      String JAVA_PATH = props.getProperty( "java.path" ).trim();
      File javaPathFile = new File( PROJECT_PATH, JAVA_PATH );
      if (javaPathFile.exists() == false) {
        javaPathFile.mkdirs();
      }
      String RESOURCES_PATH = props.getProperty( "resources.path" ).trim();
      File resourcePathFile = new File( PROJECT_PATH, RESOURCES_PATH );
      if (resourcePathFile.exists() == false) {
        resourcePathFile.mkdirs();
      }
    }catch (Exception e) {
      Exceptions.unchecked( e );
    }
  }
}

