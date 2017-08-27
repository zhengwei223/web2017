package org.lanqiao.rbac.generator;


import org.junit.Test;
import org.zhengwei.ssm.generator.CodeGenerator;

public class CodeGeneratorTest {
  CodeGenerator codeGenerator = new CodeGenerator( "/application-dev1.properties", "generatorConfig.xml" );

  @Test
  public void testGenerateMapper() {
    codeGenerator.generateMapper();
  }

  @Test
  public void testGenerateServiceAndController() {
    String TEMPLATE_FILE_PATH = "e:/workspace/web2017/rbac-service/src/test/resources/generator/template";
    System.out.println(TEMPLATE_FILE_PATH);
    codeGenerator.genServiceAndController( TEMPLATE_FILE_PATH );
  }

}
