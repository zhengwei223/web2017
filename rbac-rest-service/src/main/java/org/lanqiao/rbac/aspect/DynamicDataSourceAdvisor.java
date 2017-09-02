package org.lanqiao.rbac.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.web2017.multiDataSources.DataSourceKeyHolder;

/**
 * 数据源注入AOP
 */
@Component
@Aspect
@Profile("production")
public class DynamicDataSourceAdvisor {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Pointcut("execution(public * org.lanqiao.rbac.service.*Service.*(..))" +
      " || execution(public * org.lanqiao.rbac.base.AbstractService.*(..))" +
      " || execution(public * org.web2017.shiro.IAccountService.*(..))")
  public void service(){}

  @Before("service()")
  @Order(1)
  public void setDataSource(JoinPoint jp) {
    // get method info
    String methodName = ((MethodSignature) jp.getSignature()).getMethod().getName();
    if(methodName.startsWith("set")){
      return;
    }
    //methods starting with find or count ,use slaves
    DataSourceKeyHolder.clearDBType();// must clear , or else cannot set new value
    if (methodName.startsWith("find") || methodName.startsWith("count")) {
      if (logger.isDebugEnabled()){
        logger.debug("使用slave数据库");
      }
      DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.SLAVE);
    } else {
      if (logger.isDebugEnabled()){
        logger.debug("使用master数据库");
      }
      DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
    }
  }
}
