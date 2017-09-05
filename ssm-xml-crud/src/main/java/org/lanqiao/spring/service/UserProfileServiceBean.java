package org.lanqiao.spring.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.Role;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.myBatis.entity.UserRole;
import org.lanqiao.myBatis.repository.UserProfileMapper;
import org.lanqiao.myBatis.repository.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileServiceBean {
  @Autowired
  private UserProfileMapper userProfileMapper;
@Autowired
  private UserRoleMapper userRoleMapper;
  @Transactional(readOnly = true)
  public List<UserProfile> findAll() {
    return userProfileMapper.selectAll();
  }

  @Transactional(readOnly = true)
  public PageInfo<UserProfile> findAllPaging(int page, int size) {
    return PageHelper.startPage(page, size).doSelectPageInfo(() -> {
      userProfileMapper.selectAll();
    });
  }

  @Transactional(readOnly = true)
  public PageInfo<UserWithRole> findAllWithRoleName(Integer page, Integer size) {
    final PageInfo<UserWithRole> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(() -> {
      userProfileMapper.selectAllWithRoleName();
    });
    return pageInfo;
  }

  @Transactional(readOnly = true)
  public List<UserProfile> findByExample(UserProfile userProfile) {
    return userProfileMapper.selectByExample(userProfile);
  }

  @Transactional(readOnly = true)
  public UserProfile findById(int i) {
    return userProfileMapper.selectByPrimaryKey(i);
  }

  public void update(UserProfile record) {
    userProfileMapper.updateByPrimaryKey(record);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void save(UserWithRole user){
    userProfileMapper.insert(user);
    saveUserRole(user);
  }
  @Transactional(propagation = Propagation.REQUIRED)
  public void update(UserWithRole user) {
    userProfileMapper.updateByPrimaryKey(user);
    userRoleMapper.deleteByUserId(user.getId());
    saveUserRole(user);
  }

  private void saveUserRole(UserWithRole user) {
    for(Role role:user.getRoles()) {
      UserRole userRole = new UserRole();
      userRole.setUserId(user.getId());
      Integer roleId=role.getId();
      userRole.setRoleId(roleId);
      userRoleMapper.insert(userRole);
    }
  }
@Transactional(readOnly = true)
  public UserWithRole findUserWithRoleByUserId(Integer id) {
    return userProfileMapper.selectUserWithRoleByUserId(id);
  }

  public void remove(Integer id) {
    userProfileMapper.deleteByPrimaryKey(id);
    userRoleMapper.deleteByUserId(id);
  }
}
