package org.lanqiao.restdemo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/*以前关于mvc的配置在这里进行，继承WebMvcConfigurerAdapter，并覆写相关方法*/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
  static Logger logger = LoggerFactory.getLogger( MvcConfig.class );

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters( converters );
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures( SerializerFeature.PrettyFormat );
    fastConverter.setFastJsonConfig( fastJsonConfig );
    converters.add( fastConverter );
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    super.addArgumentResolvers( argumentResolvers );
  }

  /*添加拦截器*/
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor( new HandlerInterceptor() {
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*只有debug级别被启动时才有这项输出，请查看application.properties和logback-spring.xml两个文件*/
        if (logger.isDebugEnabled())
          logger.debug( "我是拦截器，拦截：" + request.getRequestURI() );
        return true;
      }

      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

      }

      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

      }
    } ).addPathPatterns( "/**" );// 一定要添加路径模式

    super.addInterceptors( registry );
  }

  /*修改默认的DispatcherServlet配置*/
  @Bean
  public ServletRegistrationBean dispatcherRegistation(DispatcherServlet dispatcherServlet) {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean( dispatcherServlet );
    servletRegistrationBean.getUrlMappings().clear();
    //处理.do结尾的url请求
    servletRegistrationBean.addUrlMappings( "*.do" );
    servletRegistrationBean.addUrlMappings( "*.json" );
    return servletRegistrationBean;
  }
}
