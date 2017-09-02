package org.web2017.multiDataSources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

  @Override
  /**
   * 返回当前要使用的DataSource的key
   */
  protected Object determineCurrentLookupKey() {
    final DataSourceKeyHolder.DataSourceType dbType = DataSourceKeyHolder.getDbType();
    return dbType.value();
  }
}
