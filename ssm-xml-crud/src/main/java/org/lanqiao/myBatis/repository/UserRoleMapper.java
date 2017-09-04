package org.lanqiao.myBatis.repository;

import java.util.List;
import org.lanqiao.myBatis.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<Integer,UserRole>{

  void deleteByUserId(Integer id);
}