package org.lanqiao.scloud.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class GreetingService {
  @RequestMapping(method = RequestMethod.GET, value = "/greeting/{name}")
  public String greet(@PathVariable("name") String name) {
    return "::02:: Hello, " + name + "!";
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(GreetingService.class).web(true).run(args);
  }

}

