package org.lanqiao.rbac.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.zhengwei.multiDataSources.DataSourceKeyHolder;

/**
 * 数据源注入AOP
 */
public class DynamicDataSourceAdvisor {
  public void setDataSource(JoinPoint jp) {
    // get method info
    String methodName = ((MethodSignature) jp.getSignature()).getMethod().getName();
    if(methodName.startsWith("set")){
      return;
    }
    //methods starting with find or count ,use slaves
    DataSourceKeyHolder.clearDBType();// must clear , or else cannot set new value
    if (methodName.startsWith("find") || methodName.startsWith("count")) {
      DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.SLAVE);
    } else {
      DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
    }
  }
}
