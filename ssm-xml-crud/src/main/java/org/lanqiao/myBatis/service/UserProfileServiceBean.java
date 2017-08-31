package org.lanqiao.myBatis.service;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.lanqiao.myBatis.MyBatisHelper;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.myBatis.repository.UserProfileMapper;

import java.util.List;

public class UserProfileServiceBean {
  private UserProfileMapper userProfileMapper;

  public void setUserProfileMapper(UserProfileMapper userProfileMapper) {
    this.userProfileMapper = userProfileMapper;
  }

  public List<UserProfile> findAll() {
    return userProfileMapper.selectAll();
  }
  public List<UserProfile> findAllPaging(int offset, int limit) {
    return userProfileMapper.selectAll(new RowBounds(offset,limit ));
  }
  public List<UserWithRole> findAllWithRoleName() {
    return userProfileMapper.selectAllWithRoleName();
  }
  public List<UserProfile> findByExample(UserProfile userProfile) {
    return userProfileMapper.selectByExample(userProfile);
  }
  public void update(UserProfile record){
    userProfileMapper.updateByPrimaryKey( record );
  }

  public UserProfile findById(int i) {
    return userProfileMapper.selectByPrimaryKey( i );
  }
}
