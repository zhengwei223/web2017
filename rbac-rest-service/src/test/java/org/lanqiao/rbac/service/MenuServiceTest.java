package org.lanqiao.rbac.service;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseUnitTester;
import org.lanqiao.rbac.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MenuServiceTest extends BaseUnitTester{
  @Autowired
  private MenuService menuService;

  @Test
  public void findByPid() throws Exception {
    List<Menu> menus = menuService.findByPid(1);
    assertThat(menus).hasSize(3);
  }

}