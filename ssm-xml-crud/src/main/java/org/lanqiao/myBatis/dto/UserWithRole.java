package org.lanqiao.myBatis.dto;

import org.lanqiao.myBatis.entity.Role;
import org.lanqiao.myBatis.entity.UserProfile;

import java.util.List;

public class UserWithRole extends UserProfile {
  private List<Role> roles;

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return super.toString()+
        "{" +
        "role=" + roles.toString()+
        '}';
  }
}
