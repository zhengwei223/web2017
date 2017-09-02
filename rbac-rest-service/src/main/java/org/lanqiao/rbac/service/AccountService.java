package org.lanqiao.rbac.service;

import org.lanqiao.rbac.base.AbstractService;
import org.lanqiao.rbac.entity.Account;
import org.lanqiao.rbac.repository.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web2017.shiro.IAccountService;
import tk.mybatis.mapper.entity.Condition;

import java.util.Set;


/**
 * Created by web2017 on 2017/08/23.
 */
@Service("accountService")
@Transactional(readOnly = true)
public class AccountService extends AbstractService<Account> implements IAccountService {
  // 因为泛型注入，所以这里不必声明mapper的依赖

  @Override
  public String findTokenByUserId(String userid) {
    return mapper.selectByPrimaryKey(Integer.parseInt(userid)).getToken();
  }

  @Override
  public Set<String> findPermissionsById(String userId) {
    return ((AccountMapper)mapper).selectPermissionsById(Integer.parseInt(userId));
  }

  @Override
  public String findPasswd(String account) {
    Condition condition = new Condition(Account.class);
    condition.createCriteria().andEqualTo("account", account);
    return mapper.selectByCondition(condition).get(0).getPassword();
  }

  @Transactional(readOnly = false)
  public Integer saveToken(Account account, String serverToken) {
    Condition condition = new Condition(Account.class);
    condition.createCriteria().andEqualTo("account", account.getAccount());
    Account account1 = mapper.selectByCondition(condition).get(0);
    account1.setToken(serverToken);
    mapper.updateByPrimaryKey(account1);
    return account1.getId();
  }
}
