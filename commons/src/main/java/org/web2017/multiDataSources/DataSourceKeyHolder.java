package org.web2017.multiDataSources;

/**
 * 设置线程级别的DataSource-key；
 * 如果未设置，默认返回master读写库；
 * 如果有设置（如slave）只读库，则返回该库DataSource的key
 */
public class DataSourceKeyHolder {
  /**
   * 线程threadlocal
   */
  private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

  public enum DataSourceType {
    MASTER("master"), SLAVE("slave");

    private String type;

    DataSourceType(String type) {
      this.type = type;
    }

    public String value() {
      return this.type;
    }
  }

  public static DataSourceType getDbType() {
    DataSourceType db = contextHolder.get();
    if (db == null) {
      db = DataSourceType.MASTER;
    }
    return db;
  }

  /**
   *
   * 设置本线程的dbtype
   *
   * @param type
   * @see [相关类/方法](可选)
   * @since [产品/模块版本](可选)
   */
  public static void setDbType(DataSourceType type) {
    contextHolder.set(type);
  }

  /**
   * clearDBType
   *
   * @Title: clearDBType
   * @Description: 清理连接类型
   */
  public static void clearDBType() {
    contextHolder.remove();
  }
}
