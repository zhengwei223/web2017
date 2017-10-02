package org.lanqiao.proj.controller;

import org.lanqiao.rbac.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.web2017.web.rest.Result;

@Controller
@RequestMapping("/account")
public class AccountController {
  @Autowired
  RestTemplate restTemplate;

  @PostMapping("/register")
  public String register(Account account, Model model) {
    final String url = "http://localhost:8080/rbac-rest-service/rbac/account";
    Result result = restTemplate.postForObject(url, account, Result.class);
    model.addAttribute("toJspKey", "toJspValue");
    if (result.getCode() == 200)
      return "forward:/index";
    else
      return "accountForm";
  }

  @GetMapping("/form")
  public ModelAndView form() {
    return new ModelAndView("accountForm");
  }
}
