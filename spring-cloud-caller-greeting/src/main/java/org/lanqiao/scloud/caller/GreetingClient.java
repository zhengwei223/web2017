package org.lanqiao.scloud.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class GreetingClient {
  public static void main(String[] args) {
    new SpringApplicationBuilder(GreetingClient.class)
        .web(false)
        .run(args);
  }
  @Bean
  @LoadBalanced
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

@Component
class RestTemplateExample implements CommandLineRunner {
  @Autowired
  private RestTemplate restTemplate;
  // 通过服务名称来调用，而不需要知道目标服务的IP和端口
  private static final String GREETING_SERVICE_URI = "http://greeting.service/greeting/{name}";

  @Override
  public void run(String... strings) throws Exception {
    while (true) {
      String greetingSentence = this.restTemplate.getForObject(
          GREETING_SERVICE_URI,
          String.class,
          "Sansa"); // 透明调用远程服务
      System.out.println("Response result: " + greetingSentence);

      Thread.sleep(5000);
    }
  }
}