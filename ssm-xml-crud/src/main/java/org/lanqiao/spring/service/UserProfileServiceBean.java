package org.lanqiao.spring.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.myBatis.repository.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileServiceBean {
  @Autowired
  private UserProfileMapper userProfileMapper;

  @Transactional(readOnly = true)
  public List<UserProfile> findAll() {
    return userProfileMapper.selectAll();
  }

  @Transactional(readOnly = true)
  public PageInfo<UserProfile> findAllPaging(int page, int size) {
    return PageHelper.startPage( page, size ).doSelectPageInfo( () -> {
      userProfileMapper.selectAll();
    } );
  }

  @Transactional(readOnly = true)
  public List<UserWithRole> findAllWithRoleName() {
    return userProfileMapper.selectAllWithRoleName();
  }

  @Transactional(readOnly = true)
  public List<UserProfile> findByExample(UserProfile userProfile) {
    return userProfileMapper.selectByExample( userProfile );
  }

  public void update(UserProfile record) {
    userProfileMapper.updateByPrimaryKey( record );
  }

  @Transactional(readOnly = true)
  public UserProfile findById(int i) {
    return userProfileMapper.selectByPrimaryKey( i );
  }
}
