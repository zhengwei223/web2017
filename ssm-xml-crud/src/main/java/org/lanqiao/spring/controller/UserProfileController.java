package org.lanqiao.spring.controller;

import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.spring.service.RoleServiceBean;
import org.lanqiao.spring.service.UserProfileServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/rbac/user")
public class UserProfileController {
  @Autowired
  private UserProfileServiceBean userService;
  @Autowired
  private RoleServiceBean roleService;

  @GetMapping
  public ModelAndView list(Integer page, Integer size) {
    ModelAndView mv = new ModelAndView();
    if (page == null || page < 1)
      page = 1;
    if (size == null || size < 1)
      size = 10;
    mv.addObject("pageInfo", userService.findAllWithRoleName(page, size));
    mv.setViewName("rbac/user/list");
    return mv;
  }

  @GetMapping("/create")
  public ModelAndView create() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("allRoles", roleService.findAll());
    mv.addObject("user", new UserWithRole());
    mv.setViewName("rbac/user/form");
    return mv;
  }
  @GetMapping("/update/{id}")
  public ModelAndView update(@PathVariable("id") Integer id) {
    ModelAndView mv = new ModelAndView();
    final UserWithRole userWithRole = userService.findUserWithRoleByUserId(id);
    mv.addObject("user", userWithRole);
    mv.addObject("allRoles", roleService.findAll());
    mv.setViewName("rbac/user/form");
    return mv;
  }
  @PostMapping("/save")
  public String save(UserWithRole user, RedirectAttributes redirectAttributes) {
    if (null==user.getId()){
      return doSave(user,  redirectAttributes);
    }else {
      return doUpdate(user,redirectAttributes);
    }

  }

  private String doSave(UserWithRole user, RedirectAttributes redirectAttributes) {
    userService.save(user);
    redirectAttributes.addAttribute("message","添加成功");
    return "redirect:/rbac/user";
  }


  private String doUpdate(UserWithRole user, RedirectAttributes redirectAttributes){
    userService.update(user);
    redirectAttributes.addAttribute("message","修改成功");
    return "redirect:/rbac/user";
  }
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id){
    userService.remove(id);
    return "redirect:/rbac/user";
  }
}
