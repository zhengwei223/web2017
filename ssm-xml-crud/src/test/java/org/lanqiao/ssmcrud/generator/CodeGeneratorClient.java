package org.lanqiao.ssmcrud.generator;


import org.junit.Test;
import org.web2017.ssm.generator.CodeGenerator;

public class CodeGeneratorClient {
  CodeGenerator codeGenerator = new CodeGenerator( "/application-dev.properties", "generatorConfig.xml" );

  @Test
  public void testGenerateMapper() {
    codeGenerator.generateMapper();
  }

  @Test
  public void testGenerateServiceAndController() {
    String TEMPLATE_FILE_PATH = "E:/workspace/web2017/rbac-rest-service/src/test/resources/generator/template";

    codeGenerator.genServiceAndController( TEMPLATE_FILE_PATH );
  }

}
