package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.MenuMapper;
import org.lanqiao.rbac.entity.Menu;
import org.lanqiao.rbac.service.MenuService;
import org.lanqiao.rbac.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {
    @Autowired
    public void setMapper(MenuMapper menuMapper) {
        mapper = menuMapper;
    }
}
