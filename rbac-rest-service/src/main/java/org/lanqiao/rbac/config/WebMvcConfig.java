package org.lanqiao.rbac.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.web2017.commons.Https;
import org.web2017.commons.Responses;
import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @Configuration
@PropertySource( "classpath:/application.properties" )
@Profile("production")
public class WebMvcConfig {
  private final Logger logger = LoggerFactory.getLogger( WebMvcConfig.class );
  @Bean
  public HandlerInterceptorAdapter signCheckInterceptor() {
    return new HandlerInterceptorAdapter() {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          String sign = request.getParameter( "sign" );
          //验证签名
          if (StringUtils.isNotEmpty( sign ) && validateSign( request, sign )) {
            return true;
          } else {
            logger.warn( "签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                request.getRequestURI(), Https.getIpAddress( request ), JSON.toJSONString( request.getParameterMap() ) );

            Result result = new Result();
            result.setCode( ResultCode.UNAUTHORIZED ).setMessage( "签名认证失败" );
            Responses.sendResult( response, result );
            return false;
          }
      }
    };
  }

  /**
   * 一个简单的签名认证，规则：请求参数按ASCII码排序后，拼接为a=value&b=value...
   * 这样的字符串后进行MD5
   *
   * @param request
   * @param requestSign
   * @return
   */
  private boolean validateSign(HttpServletRequest request, String requestSign) {
    List<String> keys = new ArrayList<String>( request.getParameterMap().keySet() );
    Collections.sort( keys );

    String linkString = "";

    for (String key : keys) {
      if (!"sign".equals( key )) {
        linkString += key + "=" + request.getParameter( key ) + "&";
      }
    }
    if (StringUtils.isEmpty( linkString ))
      return false;

    linkString = linkString.substring( 0, linkString.length() - 1 );
    String key = "XiaoJiu";//自己修改
    String sign = DigestUtils.md5Hex( linkString + key );

    return StringUtils.equals( sign, requestSign );

  }

  public static void main(String[] args) {
    System.out.println(DigestUtils.md5Hex( "page=1&size=10XiaoJiu" ));
  }
}
