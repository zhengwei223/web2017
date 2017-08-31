package org.lanqiao.ssmcrud.generator;


import org.junit.Test;
import org.zhengwei.ssm.generator.CodeGenerator;

public class CodeGeneratorTest {
  CodeGenerator codeGenerator = new CodeGenerator( "/application-dev.properties", "generatorConfig.xml" );

  @Test
  public void testGenerateMapper() {
    codeGenerator.generateMapper();
  }

  @Test
  public void testGenerateServiceAndController() {
    String TEMPLATE_FILE_PATH = "E:/workspace/web2017/rbac-service/src/test/resources/generator/template";

    codeGenerator.genServiceAndController( TEMPLATE_FILE_PATH );
  }

}
