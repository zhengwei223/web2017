package org.lanqiao.rbac.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.lanqiao.rbac.core.Tester;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileMapperTest extends Tester {
  @Autowired
  private UserProfileMapper userProfileMapper;

  @Test
  public void testSelectAll() {
    Assertions.assertThat( userProfileMapper.selectAll() ).hasSize( 1 );
  }
}