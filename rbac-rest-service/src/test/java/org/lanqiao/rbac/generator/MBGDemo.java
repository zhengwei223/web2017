package org.lanqiao.rbac.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.sql.*;
import java.util.*;


public class MBGDemo {
  public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    genMapper();
  }

  private static void genMapper() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(new ClassPathResource("/generatorConfig.xml").getInputStream());
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }
}

