package org.lanqiao.rbac.generator;


import org.zhengwei.ssm.generator.CodeGenerator;

public class CodeGeneratorTest  {
  public static void main(String[] args) {
    CodeGenerator codeGenerator = new CodeGenerator("/application-dev1.properties","generatorConfig.xml");
    codeGenerator.generate();
  }
}
