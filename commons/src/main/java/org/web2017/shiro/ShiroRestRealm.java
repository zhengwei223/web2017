package org.web2017.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class ShiroRestRealm extends AuthorizingRealm {
  private final IAccountService accountService;

  public ShiroRestRealm(IAccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof  UsernamePasswordToken || token instanceof UserIDToken;
  }

  /**
   * 鉴权
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
    String userId = (String) super.getAvailablePrincipal(principals);
    Set<String> permissions = accountService.findPermissionsById(userId);
    simpleAuthorInfo.addStringPermissions(permissions);
    return simpleAuthorInfo;
  }

  /**
   * 验证
   * 因为无状态，我们每次都要验证
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    SimpleAuthenticationInfo authInfo = null;
    if (token instanceof UsernamePasswordToken) {
      UsernamePasswordToken upToken = (UsernamePasswordToken) token;
      String account = upToken.getUsername();
      final String passwd = accountService.findPasswd(account);
      authInfo = new SimpleAuthenticationInfo(account, passwd, getName());
    }else if (token instanceof UserIDToken){
      UserIDToken userIDToken= (UserIDToken) token;
      authInfo=new SimpleAuthenticationInfo(userIDToken.getUserId(),userIDToken.getUserId(),getName());
    }
    return authInfo;
  }
}
