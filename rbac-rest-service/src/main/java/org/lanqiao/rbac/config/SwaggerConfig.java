package org.lanqiao.rbac.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath:spring-context.xml")
//@Configuration
//@EnableSwagger
public class SwaggerConfig {
  private SpringSwaggerConfig springSwaggerConfig;

  @Autowired
  public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
    this.springSwaggerConfig = springSwaggerConfig;
  }

  /**
   * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
   * framework - allowing for multiple swagger groups i.e. same code base
   * multiple swagger resource listings.
   */
  @Bean
  public SwaggerSpringMvcPlugin customImplementation() {
    return new SwaggerSpringMvcPlugin(springSwaggerConfig)
        .apiInfo(apiInfo())
        .includePatterns(".*?");
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "用户与权限管理中心",
        "REST API",
        "My Apps API terms of service",
        "zhengwei@lanqiao.org",
        "My Apps API Licence Type",
        "My Apps API License URL");
    return apiInfo;
  }
}