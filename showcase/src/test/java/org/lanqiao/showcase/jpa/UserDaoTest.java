package org.lanqiao.showcase.jpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.web2017.test.data.RandomData;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:cache/applicationContext-jpa.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
  @Autowired
  private UserDao userDao;

  @Test
  public void testFindAll() {
    assertThat(userDao.findAll().iterator()).hasSize(1);
  }

  @Before
  public void testSave(){
    final User entity = new User();
    entity.setUsername(RandomData.randomName("username"));
    entity.setPassword(RandomData.randomName("password"));
    userDao.save(entity);
  }
}