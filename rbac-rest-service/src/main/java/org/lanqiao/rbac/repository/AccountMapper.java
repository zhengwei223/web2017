package org.lanqiao.rbac.repository;

import org.lanqiao.rbac.base.Mapper;
import org.lanqiao.rbac.entity.Account;

import java.util.Set;

public interface AccountMapper extends Mapper<Account> {
  Set<String> selectPermissionsById(Integer userId);
}