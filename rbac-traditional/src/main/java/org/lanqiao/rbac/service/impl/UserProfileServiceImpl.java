package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.UserProfileMapper;
import org.lanqiao.rbac.entity.UserProfile;
import org.lanqiao.rbac.service.UserProfileService;
import org.lanqiao.rbac.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class UserProfileServiceImpl extends AbstractService<UserProfile> implements UserProfileService {
    @Autowired
    public void setMapper(UserProfileMapper userProfileMapper) {
        mapper = userProfileMapper;
    }
}
