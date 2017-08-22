package org.lanqiao.rbac.service.impl;

import org.lanqiao.rbac.repository.AccountMapper;
import org.lanqiao.rbac.entity.Account;
import org.lanqiao.rbac.service.AccountService;
import org.lanqiao.rbac.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zhengwei on 2017/08/20.
 */
@Service
@Transactional
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {
    @Autowired
    public void setMapper(AccountMapper accountMapper) {
        mapper = accountMapper;
    }
}
