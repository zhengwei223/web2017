package org.zhengwei.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class UserIDToken implements AuthenticationToken {
  private String userId;
  private String sign;

  public UserIDToken(String userId, String sign) {
    this.userId = userId;
    this.sign = sign;
  }

  public UserIDToken(String userid) {
    this.userId=userid;
  }

  public String getUserId() {
    return userId;
  }

  public String getSign() {
    return sign;
  }

  public Object getPrincipal() {
    return userId;
  }

  public Object getCredentials() {
    return sign;
  }
}
