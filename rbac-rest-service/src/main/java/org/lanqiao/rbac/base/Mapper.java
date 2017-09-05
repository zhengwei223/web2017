package org.lanqiao.rbac.base;

import org.lanqiao.rbac.entity.Menu;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;
import java.util.Set;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 */
public interface Mapper<T>
    extends
    BaseMapper<T>,
    ConditionMapper<T>,
    IdsMapper<T>,
    InsertListMapper<T> {


  List<Menu> selectByPid(Integer pid);
}