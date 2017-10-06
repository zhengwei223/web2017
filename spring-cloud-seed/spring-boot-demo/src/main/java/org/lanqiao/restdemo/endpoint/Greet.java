package org.lanqiao.restdemo.endpoint;

import com.codahale.metrics.annotation.Counted;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class Greet {
  @Counted
  @GetMapping(value = "/hi")
  public String sayHi(){
    return "hi jpa core web";
  }
}
