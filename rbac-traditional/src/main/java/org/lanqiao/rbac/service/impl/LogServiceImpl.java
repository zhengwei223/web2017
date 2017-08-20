package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.LogMapper;
import org.lanqiao.rbac.entity.Log;
import org.lanqiao.rbac.service.LogService;
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
public class LogServiceImpl extends AbstractService<Log> implements LogService {
    @Autowired
    public void setMapper(LogMapper logMapper) {
        mapper = logMapper;
    }
}
