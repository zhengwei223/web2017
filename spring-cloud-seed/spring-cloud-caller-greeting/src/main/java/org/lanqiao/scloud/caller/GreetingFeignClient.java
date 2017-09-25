package org.lanqiao.scloud.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class GreetingFeignClient {

  public static void main(String[] args) {
    SpringApplication.run(GreetingFeignClient.class,args);
  }
}

/*FeignClient采用接口注入的方式*/
@Component
class FeignClientExample implements CommandLineRunner {
  @Autowired
  private GreetingService greetingService;

  @Override
  public void run(String... strings) throws Exception {
    while (true) {
      String greetingSentence =greetingService.greet("Sansa");
      System.out.println("Response result: " + greetingSentence);
      Thread.sleep(5000);
    }
  }
}