package org.lanqiao.rbac.core;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseUnitTester;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class ContextTest extends BaseUnitTester {
  @Autowired
  //@Qualifier("master")
  private DataSource dataSource;

  @Test
  public void testDb() throws SQLException {
    System.out.println(dataSource);
    assertThat(dataSource.getConnection()).isNotNull();
  }
}
