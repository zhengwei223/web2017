package org.lanqiao.rbac.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.session.NoSessionCreationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.lanqiao.rbac.service.AccountService;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.web2017.shiro.IAccountService;
import org.web2017.shiro.ShiroRestRealm;
import org.web2017.shiro.StatelessAuthcFilter;

import javax.servlet.Filter;
import java.util.Map;

@ImportResource("classpath:spring-context.xml")
@Configuration  // 可以认为这是spring配置的Java版
public class ShiroConfig {


  /*<bean id="shiroFilter" class="ShiroFilterFactoryBean" />*/
  @Bean
  @Autowired
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, StatelessAuthcFilter authcFilter) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(securityManager);

    Map<String, Filter> filters = bean.getFilters();
    filters.put("stateLessAuthcFilter", authcFilter);// 无状态http权限验证过滤器
    filters.put("anon", new AnonymousFilter());// 添加过滤器
    filters.put("noSessionCreation", new NoSessionCreationFilter());// 添加过滤器

    // bean.setFilters(filters);
    /*登录跳转链接*/
    bean.setLoginUrl("/rbac/account/login");
    bean.setUnauthorizedUrl("/rbac/account/unauthorized");

    /*-------------*/
    /*不同的url用不同的过滤器拦截*/
    bean.getFilterChainDefinitionMap().put("/rbac/account/login", "anon"); // 不拦截的写在前面
    bean.getFilterChainDefinitionMap().put("/rbac/account/unauthorized*", "anon"); // 不拦截的写在前面
    if (System.getProperty("spring.profiles.active")==null||System.getProperty("spring.profiles.active").equals("production"))
      bean.getFilterChainDefinitionMap().put("/**", "noSessionCreation,stateLessAuthcFilter"); // 添加路径拦截
    else
      bean.getFilterChainDefinitionMap().put("/**", "anon");


    return bean;
  }

  @Bean
  public StatelessAuthcFilter authcFilter(AccountService accountService) {
    StatelessAuthcFilter authcFilter = new StatelessAuthcFilter();
    authcFilter.setAccountService(accountService);
    return authcFilter;
  }

  @Bean
  @Autowired
  public DefaultWebSecurityManager securityManager(Realm realm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);

    final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    sessionManager.setSessionIdCookieEnabled(false);
    sessionManager.setSessionValidationSchedulerEnabled(false);
    securityManager.setSessionManager(sessionManager);
    // 禁止session被创建
    securityManager.setSubjectFactory(new DefaultWebSubjectFactory() {
      @Override
      public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
      }
    });
    // 禁用使用Sessions 作为存储策略的实现
    final DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    subjectDAO.setSessionStorageEvaluator(new DefaultSessionStorageEvaluator(){
      @Override
      public boolean isSessionStorageEnabled() {
        return false;
      }
    });
    securityManager.setSubjectDAO(subjectDAO);
    SecurityUtils.setSecurityManager(securityManager);

    return securityManager;
  }

  @Bean
  @Autowired
  public Realm realm(@Qualifier("accountService") IAccountService accountService) {
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

