package org.lanqiao.myBatis.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;

import java.util.List;

public class UserProfileServiceTest {
  @Test
  public void findAll() throws Exception {
    UserProfileService userProfileService = new UserProfileService();
    List<UserProfile> userProfiles = userProfileService.findAll();
    System.out.println( userProfiles );

  }
  @Test
  public void findAllPaging() throws Exception {
    UserProfileService userProfileService = new UserProfileService();
    List<UserProfile> userProfiles = userProfileService.findAllPaging(0,10);
    System.out.println( userProfiles );

  }
  @Test
  public void findAllWithRoleName() throws Exception {
    UserProfileService userProfileService = new UserProfileService();
    List<UserWithRole> userProfiles = userProfileService.findAllWithRoleName();
    System.out.println( userProfiles );

  }

  @Test
  public void update() throws Exception {
    UserProfileService userProfileService = new UserProfileService();
    UserProfile record = new UserProfile();
    record.setId( 1 );
    record.setNickName( "98989" );
    userProfileService.update( record );

    record = userProfileService.findById(1);
    Assertions.assertThat( record.getNickName() ).isEqualTo( "98989" );
  }

  @Test
  public void testFindByExample() throws Exception {
    UserProfileService userProfileService = new UserProfileService();
    UserProfile record = new UserProfile();
    record.setNickName( "9%" );
    List<UserProfile> users = userProfileService.findByExample( record );
    System.out.println(users);
  }

  @Test
  public void testFindById(){
    UserProfileService userProfileService = new UserProfileService();
    System.out.println(userProfileService.findById( 1 ));
  }

}