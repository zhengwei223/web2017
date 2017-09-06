 package org.lanqiao.rbac.config;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.PathSelectors;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.ApiInfo;
 import springfox.documentation.service.Contact;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 @EnableSwagger2
 public class SwaggerConfig {
   @Bean
   public Docket buildDocket() {
     return new Docket(DocumentationType.SWAGGER_2)
         .apiInfo(apiInfo())
         .select()
         .apis(RequestHandlerSelectors.basePackage("org.lanqiao.rbac.api"))//controller路径
         .paths(PathSelectors.any())
         .build();
   }

   private ApiInfo apiInfo() {
     return new ApiInfoBuilder()
         .title("API文档")
         .termsOfServiceUrl("...")
         .description("springmvc swagger2")
         .contact(new Contact("zhengwei", "http://blog.csdn.net/zhengwei223", "zhengwei@lanqiao.org"))
         .build();

   }
 }