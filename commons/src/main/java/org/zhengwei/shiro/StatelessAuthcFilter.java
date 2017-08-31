package org.zhengwei.shiro;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengwei 2017-08-30
 */
public class StatelessAuthcFilter extends PermissionsAuthorizationFilter {
  private IAccountService accountService;

  public void setAccountService(IAccountService accountService) {
    this.accountService = accountService;
  }

  /**
   * 访问是否受到许可
   * 1、没有userid或者sign，说明没登录
   * 2、有二者，但是加密摘要和服务端加密摘要不一致，说明为伪造
   * @param request
   * @param response
   * @param mappedValue
   * @return
   * @throws IOException
   */
  @Override
  public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    String userid = request.getParameter("userid");
    String clientSign = request.getParameter("sign");
    // 没有token，此人不认识，没有任何访问权限，除非访问login
    if (null == userid || clientSign == null) {
      redirectToLogin(request,response);
      return  false;
    }
    // 有userID和sign，说明登录过，我们还要验证token的合法性，非rest没有这么麻烦
    // 1. token是否有效?应查数据库得到token；
    // 服务端用token加密参数为serverSign，比对客户端sign

    List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());

    String linkString = "";

    for (String key : keys) {
      // 除开userid和sign，其余字符串连接起来
      if (!"userid".equals(key) && !"sign".equals(key)) {
        linkString += key + "=" + request.getParameter(key) + "&";
      }
    }
    if (!StringUtils.isEmpty(linkString)) {
      linkString = linkString.substring(0, linkString.length() - 1);
      String token = accountService.findTokenByUserId(userid);
      String serverSign = DigestUtils.md5Hex(linkString + token);
      return clientSign.equals(serverSign);
    }
    // 判断url
    return super.isAccessAllowed(request, response, mappedValue);
  }
}

