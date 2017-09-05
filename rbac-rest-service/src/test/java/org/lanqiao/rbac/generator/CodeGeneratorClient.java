package org.lanqiao.rbac.generator;


import org.web2017.ssm.generator.CodeGenerator;

/**
 * 调用代码生成器生成项目相关的源码
 */
public class CodeGeneratorClient {
  CodeGenerator codeGenerator = new CodeGenerator("/application-development.properties", "generatorConfig.xml" );

  public void generateMapper() {
    codeGenerator.generateMapper();
  }

  public void generateServiceAndController() {
    String TEMPLATE_FILE_PATH = "e:/workspace/web2017/rbac-rest-service/src/test/resources/generator/template";
    System.out.println(TEMPLATE_FILE_PATH);
    codeGenerator.genServiceAndController( TEMPLATE_FILE_PATH );
  }

  public static void main(String[] args) {
    final CodeGeneratorClient client = new CodeGeneratorClient();
    client.generateMapper();
    //client.generateServiceAndController();
  }

}
