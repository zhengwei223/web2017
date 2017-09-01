package org.lanqiao.rbac.service;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhengwei.shiro.IAccountService;

import static org.junit.Assert.*;

public class AccountServiceTest extends BaseTester{
  @Autowired
  IAccountService accountService;
  @Test
  public void findPermissionsById() throws Exception {
    accountService.findPermissionsById("1");
  }

}