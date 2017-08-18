package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.UserMapper;
import org.lanqiao.rbac.entity.User;
import org.lanqiao.rbac.service.UserService;
import org.lanqiao.rbac.core.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhengwei on 2017/08/18.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    public void setMapper(UserMapper userMapper) {
        mapper = userMapper;
    }
}
