package org.lanqiao.rbac.service;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseUnitTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.web2017.shiro.IAccountService;

import java.util.Set;

public class AccountServiceTest extends BaseUnitTester {
  @Autowired
  IAccountService accountService;

  @Test
  public void findPermissionsById() throws Exception {
    final Set<String> permissions = accountService.findPermissionsById("1");
    for (String p : permissions) {
      System.out.println(p);
    }
  }

}