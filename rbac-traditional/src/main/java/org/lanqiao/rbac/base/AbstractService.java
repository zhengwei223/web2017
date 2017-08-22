package org.lanqiao.rbac.base;


import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.transaction.annotation.Transactional;
import org.zhengwei.multiDataSources.DataSourceKeyHolder;
import org.zhengwei.web.rest.ServiceException;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

  protected Mapper<T> mapper;

  private Class<T> modelClass;    // 当前泛型真实类型的Class

  public AbstractService() {
    ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
    modelClass = (Class<T>) pt.getActualTypeArguments()[0];
  }

  public void save(T model) {
    mapper.insertSelective(model);
  }

  public void save(List<T> models) {
    mapper.insertList(models);
  }

  public void deleteById(Integer id) {
    mapper.deleteByPrimaryKey(id);
  }

  public void deleteByIds(String ids) {
    mapper.deleteByIds(ids);
  }

  public void update(T model) {
    mapper.updateByPrimaryKeySelective(model);
  }

  @Transactional(readOnly = true)
  public T findById(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  @Override
  @Transactional(readOnly = true)
  public T findBy(String property, Object value) throws TooManyResultsException {
    // 手动设置数据源
    // DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
    try {
      T model = modelClass.newInstance();
      Field field = modelClass.getDeclaredField(property);
      field.setAccessible(true);
      field.set(model, value);
      return mapper.selectOne(model);
    } catch (ReflectiveOperationException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Transactional(readOnly = true)
  public List<T> findByIds(String ids) {
    return mapper.selectByIds(ids);
  }

  @Transactional(readOnly = true)
  public List<T> findByCondition(Condition condition) {
    return mapper.selectByCondition(condition);
  }

  @Override
  // @Transactional(readOnly = true)
  public List<T> findAll() {
    return mapper.selectAll();
  }

  @Transactional(readOnly = true)
  public int count() {
    return mapper.selectCount(null);
  }

  @Override
  public PageInfo findAll(Integer pageNumber, Integer pageSize) {
    return PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(new ISelect() {
      @Override
      public void doSelect() {
        mapper.selectAll();
      }
    });
  }
}
