package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.RoleMenuMapper;
import org.lanqiao.rbac.entity.RoleMenu;
import org.lanqiao.rbac.service.RoleMenuService;
import org.lanqiao.rbac.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends AbstractService<RoleMenu> implements RoleMenuService {
    @Autowired
    public void setMapper(RoleMenuMapper roleMenuMapper) {
        mapper = roleMenuMapper;
    }
}
