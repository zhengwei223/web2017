package org.lanqiao.showcase.service;

import org.junit.Test;
import org.lanqiao.showcase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.web2017.test.data.RandomData;

@ContextConfiguration({"classpath:applicationContext-jpa.xml","classpath:cache/applicationContext-redis.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
  @Autowired
  private UserService userService;

  @Test
  public void find1() throws Exception {
    final User entity = new User();
    entity.setId(1);
    entity.setUsername(RandomData.randomName("username"));
    entity.setPassword(RandomData.randomName("password"));
    entity.setTeamId(2);
    userService.save(entity);

    User user1 = userService.findById(1);
    System.out.println(user1);
    System.out.println("======-====开启缓存后，下面将不执行sql=============");
    user1 = userService.findById(1);
    System.out.println(user1);

  }
}