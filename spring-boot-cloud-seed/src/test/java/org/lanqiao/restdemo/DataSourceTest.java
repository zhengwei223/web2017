package org.lanqiao.restdemo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web2017.test.category.Fast;

import javax.sql.DataSource;

@Category(Fast.class)
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