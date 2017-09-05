package org.lanqiao.spring.service;

import org.lanqiao.myBatis.entity.Role;
import org.lanqiao.myBatis.repository.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceBean {
  @Autowired
  private RoleMapper roleMapper;

  public List<Role> findAll(){
    return roleMapper.selectAll();
  }
}
