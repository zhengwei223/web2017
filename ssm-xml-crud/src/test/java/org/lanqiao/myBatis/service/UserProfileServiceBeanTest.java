package org.lanqiao.myBatis.service;

import org.junit.Test;
import org.lanqiao.myBatis.entity.UserProfile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserProfileServiceBeanTest {

  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  @Test
  public void findAll() throws Exception {
    UserProfileServiceBean userProfileServiceBean = (UserProfileServiceBean) context.getBean("userProfileService");
    List<UserProfile> users = userProfileServiceBean.findAll();
    System.out.println(users);
  }

  @Test
  public void findAllPaging() throws Exception {
  }

  @Test
  public void findAllWithRoleName() throws Exception {
  }

  @Test
  public void findByExample() throws Exception {
  }

  @Test
  public void update() throws Exception {
  }

  @Test
  public void findById() throws Exception {
  }

}