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
   * 鉴权，无需比对
   * 只需根据用户标识，返回其拥有的资源权限组合，比对的逻辑将由shiro框架完成。
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
    //userid
    String userId = (String) super.getAvailablePrincipal(principals);
    //userid可访问的所有url的集合
    Set<String> permissions = accountService.findPermissionsById(userId);
    //添加至AuthorInfo
    simpleAuthorInfo.addStringPermissions(permissions);
    return simpleAuthorInfo;
  }

  /**
   * 验证身份信息，但是这里不负责比对的逻辑，我们只需根据principle返回AuthenticationInfo（携带了用户名和密码）
   * 比对的逻辑将由shiro框架完成
   *
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
