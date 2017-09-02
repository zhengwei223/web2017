package org.web2017.shiro;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web2017.commons.MD5Util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author web2017 2017-08-30
 */
public class StatelessAuthcFilter extends PermissionsAuthorizationFilter {
  private final Logger logger = LoggerFactory.getLogger(getClass());
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
    Boolean hasId = userid != null;

    String clientSign = request.getParameter("sign");
    Boolean hadSign = clientSign != null;

    Boolean isSignOK = checkSign(request, userid, clientSign);
    if (hasId&&hadSign&&isSignOK)  // 此处其实已经验证了用户的身份，下面一句不过是在形式上登录一下
      SecurityUtils.getSubject().login(new UserIDToken(userid)); // login之后request中才有principle，才能进行下一步鉴权
    else
      return false;
    // 判断url是否在用户的权限之中
    final String servletPath = ((HttpServletRequest) request).getServletPath();
    return super.isAccessAllowed(request, response, new String[]{servletPath});
  }

  private Boolean checkSign(ServletRequest request, String userid, String clientSign) {
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
    }
    String token = accountService.findTokenByUserId(userid);
    String serverSign = MD5Util.md5(linkString + token);//DigestUtils.md5Hex(linkString + token);
    if (logger.isDebugEnabled()) {
      logger.debug("服务端计算出来的签名是：" + serverSign);
    }
    return clientSign.equals(serverSign);
  }

  @Override
  protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
    super.redirectToLogin(request,response);
  }
}

