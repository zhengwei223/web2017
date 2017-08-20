package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.RoleMapper;
import org.lanqiao.rbac.entity.Role;
import org.lanqiao.rbac.service.RoleService;
import org.lanqiao.rbac.core.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Autowired
    public void setMapper(RoleMapper roleMapper) {
        mapper = roleMapper;
    }
}
