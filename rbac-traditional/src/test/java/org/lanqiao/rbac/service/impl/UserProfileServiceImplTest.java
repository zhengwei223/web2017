package org.lanqiao.rbac.service.impl;

import org.junit.Test;
import org.lanqiao.rbac.core.Tester;
import org.lanqiao.rbac.entity.UserProfile;
import org.lanqiao.rbac.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserProfileServiceImplTest extends Tester{
  @Autowired
  private UserProfileService userProfileService;

  @Test
  public void testFindAll(){
    List<UserProfile> all = userProfileService.findAll();
    assertThat( all ).isNotNull();
    for(UserProfile profile:all){
      System.out.println(profile);
    }
  }
  @Test
  public void testSave(){
    UserProfile model=new UserProfile();
    model.setGender( true );
    model.setNickName( "芦花荡" );
    model.setRealName( "当路滑" );
    userProfileService.save( model );
  }
  @Test
  public void testCount(){
    assertThat( userProfileService.count() >0).isTrue();
  }
}