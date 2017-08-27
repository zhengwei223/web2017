package org.lanqiao.rbac.generator;


import org.junit.Test;
import org.zhengwei.ssm.generator.CodeGenerator;

public class CodeGeneratorTest {
  CodeGenerator codeGenerator = new CodeGenerator( "/application-dev2.properties", "generatorConfig.xml" );

  @Test
  public void testGenerateMapper() {
    codeGenerator.generateMapper();
  }

  @Test
  public void testGenerateServiceAndController() {
    String TEMPLATE_FILE_PATH = "/Users/zhengwei/workspace/web2017/rbac-traditional/src/test/resources/generator/template";
    codeGenerator.genServiceAndController( TEMPLATE_FILE_PATH );
  }

}
