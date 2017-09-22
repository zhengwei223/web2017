package org.lanqiao.rbac.service;

import org.lanqiao.rbac.base.AbstractService;
import org.lanqiao.rbac.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web2017.cache.SpyMemcachedClient;

import java.util.List;


/**
 * Created by web2017 on 2017/08/23.
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends AbstractService<Role> {
  // 因为泛型注入，所以这里不必声明mapper的依赖
  //
  // @Autowired
  // private SpyMemcachedClient memcachedClient;
  //
  // @Override
  // public List<Role> findAll() {
  //   List<Role> roles = memcachedClient.get("rbac.roles");
  //   if (roles == null) {
  //     roles = refreshAll();
  //   }
  //   return roles;
  // }
  //
  // private List<Role> refreshAll() {
  //   List<Role> roles;
  //   roles = super.findAll();
  //   memcachedClient.set("rbac.roles",1*60*60, roles);
  //   return roles;
  // }
  //
  // @Override
  // public Role findById(Integer id) {
  //   return super.findById(id);
  // }


}
