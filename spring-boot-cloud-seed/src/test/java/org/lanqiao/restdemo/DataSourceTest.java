package org.lanqiao.restdemo;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.web2017.test.category.FastTest;
import org.web2017.test.category.SlowTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;
@Category(FastTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataSourceAutoConfiguration.class})
public class DataSourceTest  {
  @Autowired
  DataSource dataSource;

  @Test
  public void checkDataSource(){
    Assertions.assertThat(dataSource).isNotNull();
  }
}