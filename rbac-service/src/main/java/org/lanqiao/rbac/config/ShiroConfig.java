package org.lanqiao.rbac.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhengwei.shiro.IAccountService;
import org.zhengwei.shiro.ShiroRestRealm;
import org.zhengwei.shiro.StatelessAuthcFilter;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class ShiroConfig {

  @Bean
  @Autowired
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(securityManager);

    Map<String, Filter> filters = bean.getFilters();
    filters.put("stateLessAuthcFilter", new StatelessAuthcFilter());// 无状态http权限验证过滤器
    filters.put("anon", new AnonymousFilter());// 添加过滤器

    // bean.setFilters(filters);
    /*登录跳转链接*/
    bean.setLoginUrl("/rbac/account/login");
    bean.setUnauthorizedUrl("/rbac/account/unauthorized");

    /*-------------*/
    /*不同的url用不同的过滤器拦截*/
    bean.getFilterChainDefinitionMap().put("/rbac/account/login", "anon"); // 不拦截的写在前面
    bean.getFilterChainDefinitionMap().put("/rbac/account/unauthorized*", "anon"); // 不拦截的写在前面
    bean.getFilterChainDefinitionMap().put("/**", "stateLessAuthcFilter"); // 添加路径拦截


    return bean;
  }

  @Bean
  @Autowired
  public DefaultWebSecurityManager securityManager(Realm realm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);
    securityManager.setSessionManager(new DefaultWebSessionManager());
    SecurityUtils.setSecurityManager(securityManager);
    return securityManager;
  }

  @Bean
  @Autowired
  public Realm realm(IAccountService accountService) {
    return new ShiroRestRealm(accountService);
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
    daap.setProxyTargetClass(true);
    return daap;
  }

  @Bean
  @Autowired
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
    aasa.setSecurityManager(securityManager);
    return aasa;
  }

  
}

