package org.lanqiao.showcase.service;

import org.junit.Test;
import org.lanqiao.showcase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.web2017.test.data.RandomData;

@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
  @Autowired
  private UserService userService;

  @Test
  public void find1() throws Exception {
    final User entity = new User();
    entity.setId(1);
    entity.setUsername(RandomData.randomName("username"));
    entity.setPassword(RandomData.randomName("password"));

    userService.save(entity);

    User user1 = userService.findById(1);
    System.out.println(user1.getUsername());
    System.out.println("======-=================");
    user1 = userService.findById(1);
    System.out.println(user1.getUsername());

  }
  @Test
  public void find2() throws Exception {
       System.out.println("======-=================");
  }
}