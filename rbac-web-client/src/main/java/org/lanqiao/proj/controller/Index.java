package org.lanqiao.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.web2017.web.rest.Result;

@Controller
@RequestMapping("/index")
public class Index {
  @Autowired
  RestTemplate restTemplate;

  @RequestMapping
  public ModelAndView index(){
    Result result = restTemplate.getForObject( "http://localhost:8080/rbac-rest-service/rbac/account?pageNumber=1&pageSize=10" , Result.class );
    final ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("result",result);
    return modelAndView;
  }
}
