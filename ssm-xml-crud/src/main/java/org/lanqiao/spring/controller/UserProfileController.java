package org.lanqiao.spring.controller;

import org.lanqiao.spring.service.UserProfileServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rbac/user")
public class UserProfileController {
  @Autowired
  private UserProfileServiceBean userService;

  @GetMapping
  public ModelAndView list( int page, int size) {
    ModelAndView mv = new ModelAndView();
    mv.addObject( "pageInfo", userService.findAllPaging( page, size ) );
    mv.setViewName( "/rbac/user/list" );
    return mv;
  }
}
