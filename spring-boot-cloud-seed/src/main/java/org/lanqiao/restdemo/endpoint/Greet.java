package org.lanqiao.restdemo.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class Greet {
  @RequestMapping(value = "/hi",method = RequestMethod.GET)
  public String sayHi(){
    return "hi spring core web";
  }
}
