package org.lanqiao.restdemo.repository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.lanqiao.restdemo.config.MybatisConfig;
import org.lanqiao.restdemo.entity.User;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web2017.test.category.FastTest;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class, MybatisConfig.class})
@Category(FastTest.class)
public class UserMapperTest {
  @Autowired
  private UserMapper userMapper;

  public void addUser() throws Exception {
    userMapper.addUser( new User("zhangsan","123456",1  ) );
    userMapper.addUser( new User("lisi","123456",1  ) );
    userMapper.addUser( new User("wangwu","123456",1  ) );
    userMapper.addUser( new User("zhaoliu","123456",1  ) );
  }

  @Test
  public void deleteUserById() throws Exception {
  }

  @Test
  public void updateUser() throws Exception {
  }

  @Test
  public void getById() throws Exception {
  }

  @Test
  public void queryUserList() throws Exception {
    addUser();
    List<User> users = userMapper.queryUserList();
    System.out.println(users);
    Assertions.assertThat( users.size() ).isEqualTo( 4 );
  }

}