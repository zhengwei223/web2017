package org.web2017.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class UserIDToken implements AuthenticationToken {
  private String userId;


  public UserIDToken(String userid) {
    this.userId = userid;
  }

  public String getUserId() {
    return userId;
  }


  public Object getPrincipal() {
    return userId;
  }

  public Object getCredentials() {
    return userId;
  }
}
