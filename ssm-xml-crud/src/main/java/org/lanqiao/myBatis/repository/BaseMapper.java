package org.lanqiao.myBatis.repository;

import org.lanqiao.myBatis.entity.UserRole;

import java.util.List;

/**
 * 通用泛型Mapper
 */
public interface BaseMapper<K,T> {

    int insert(T record);
    int deleteByPrimaryKey(K id);
    int updateByPrimaryKey(T record);
    T selectByPrimaryKey(K id);
    List<T> selectAll();
}