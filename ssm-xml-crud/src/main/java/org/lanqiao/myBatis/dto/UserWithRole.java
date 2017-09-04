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

  public String getRoleNames() {
    StringBuilder sb = new StringBuilder();
    for (Role role : getRoles()) {
      sb.append(role.getName() + ",");
    }
    if (sb.length() > 0)
      sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  public String getGenderString() {
    return getGender() ? "男" : "女";
  }
}
