package org.lanqiao.showcase.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.web2017.test.data.RandomData;

@ContextConfiguration("classpath:cache/applicationContext-jpa.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
  @Autowired
  private UserService userService;

  @Test
  public void find1() throws Exception {
    final User entity = new User();
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