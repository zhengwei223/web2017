package org.lanqiao.rbac.service.impl;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.lanqiao.rbac.core.Tester;
import org.lanqiao.rbac.entity.UserProfile;
import org.lanqiao.rbac.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileServiceImplTest extends Tester {
  @Autowired
  private UserProfileService userProfileService;

  @Test
  public void testFindAll() {
    List<UserProfile> all = userProfileService.findAll();
    assertThat( all ).isNotNull();
    for (UserProfile profile : all) {
      System.out.println( profile );
    }
  }

  @Test
  public void testSave() {
    for (int i = 0; i < 20; i++) {

      UserProfile model = new UserProfile();
      model.setGender( true );
      model.setNickName( RandomStringUtils.randomAlphabetic( 3, 8 ) );
      model.setRealName( "当路滑" );
      userProfileService.save( model );
    }
  }

  @Test
  public void testCount() {
    assertThat( userProfileService.count() > 0 ).isTrue();
  }

  @Test
  public void testFindByPage() {
    PageInfo<UserProfile> pageInfo = userProfileService.findAll( 1, 10 );
    for (UserProfile profile : pageInfo.getList()) {
      System.out.println( profile );
    }
  }
}