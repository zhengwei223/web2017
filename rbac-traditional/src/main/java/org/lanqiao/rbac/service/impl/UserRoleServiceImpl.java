package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.UserRoleMapper;
import org.lanqiao.rbac.entity.UserRole;
import org.lanqiao.rbac.service.UserRoleService;
import org.lanqiao.rbac.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Autowired
    public void setMapper(UserRoleMapper userRoleMapper) {
        mapper = userRoleMapper;
    }
}
