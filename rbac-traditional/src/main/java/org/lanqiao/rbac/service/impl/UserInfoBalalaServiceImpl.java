package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.UserInfoBalalaMapper;
import org.lanqiao.rbac.entity.UserInfoBalala;
import org.lanqiao.rbac.service.UserInfoBalalaService;
import org.lanqiao.rbac.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhengwei on 2017/08/18.
 */
@Service
@Transactional
public class UserInfoBalalaServiceImpl extends AbstractService<UserInfoBalala> implements UserInfoBalalaService {
    @Resource
    private UserInfoBalalaMapper userInfoBalalaMapper;

}
