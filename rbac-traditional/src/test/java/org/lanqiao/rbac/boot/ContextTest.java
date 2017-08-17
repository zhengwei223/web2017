package org.lanqiao.rbac.boot;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:spring-context.xml")
@ActiveProfiles("development")
public class ContextTest extends AbstractJUnit4SpringContextTests {
  @Autowired
  private DataSource dataSource;

  @Test
  public void testDb() {
    assertThat(dataSource instanceof HikariDataSource).isTrue();
  }
}
