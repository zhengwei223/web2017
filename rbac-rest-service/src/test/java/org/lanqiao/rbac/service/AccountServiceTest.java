package org.lanqiao.rbac.service;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.lanqiao.rbac.base.BaseUnitTester;
import org.lanqiao.rbac.entity.Account;
import org.lanqiao.rbac.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.web2017.shiro.IAccountService;
import org.web2017.test.data.RandomData;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest extends BaseUnitTester {
  @Autowired
  AccountService accountService;

  @Test
  public void findPermissionsById() throws Exception {
    final Set<String> permissions = accountService.findPermissionsById("1");
    for (String p : permissions) {
      System.out.println(p);
    }
  }
  @Test
  public void save(){
    Account model = new Account();
    model.setAccount( RandomData.randomName( "account" ) );
    model.setGmtCreate(new Date(  ) );
    model.setType( Short.valueOf("1") );
    model.setPassword( RandomData.randomName( "passwd" ) );
    accountService.save( model );
    assertThat(model.getId()).isNotNull();
    logger.debug("new account's id ====="+String.valueOf( model.getId()));
  }

  @Test
  public void testFindAll() {
    save();
    List<Account> accounts = accountService.findAll(  );
    for (Account account : accounts) {
      logger.debug( account.toString()+"\n" );
    }
  }
  @Test
  public void testFindByPage() {
    PageInfo<Account> pageInfo = accountService.findAll( 1, 10 );
    for (Account account : pageInfo.getList()) {
      logger.debug( account.toString() );
    }
  }
}