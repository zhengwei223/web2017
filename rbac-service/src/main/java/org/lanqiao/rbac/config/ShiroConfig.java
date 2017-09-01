package org.lanqiao.rbac.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
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
import org.zhengwei.shiro.IAccountService;
import org.zhengwei.shiro.ShiroRestRealm;
import org.zhengwei.shiro.StatelessAuthcFilter;

import javax.servlet.Filter;
import java.util.Map;

@ImportResource("classpath:spring-context.xml")
@Configuration
public class ShiroConfig {
  @Value( "spring.profile.active" )
  private String profile;
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
    //bean.getFilterChainDefinitionMap().put("/**", "noSessionCreation"); // 添加路径拦截
    bean.getFilterChainDefinitionMap().put("/**", "stateLessAuthcFilter"); // 添加路径拦截


    return bean;
  }

  @Bean
  public StatelessAuthcFilter authcFilter(AccountService accountService) {
    StatelessAuthcFilter authcFilter = new StatelessAuthcFilter();
    authcFilter.setAccountService(accountService);
    authcFilter.setProfile(profile);
    return authcFilter;
  }

  @Bean
  @Autowired
  public DefaultWebSecurityManager securityManager(Realm realm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);

    final DefaultSessionManager sessionManager = new DefaultWebSessionManager();
    // sessionManager.setSessionValidationSchedulerEnabled(false);
    securityManager.setSessionManager(sessionManager);
    // 禁止session被创建
    // securityManager.setSubjectFactory(new DefaultWebSubjectFactory() {
    //   @Override
    //   public Subject createSubject(SubjectContext context) {
    //     context.setSessionCreationEnabled(false);
    //     return super.createSubject(context);
    //   }
    // });
    //禁用使用Sessions 作为存储策略的实现
    // final DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    // subjectDAO.setSessionStorageEvaluator(new DefaultSessionStorageEvaluator(){
    //   @Override
    //   public boolean isSessionStorageEnabled() {
    //     return false;
    //   }
    // });
    // securityManager.setSubjectDAO(subjectDAO);
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

