package org.lanqiao.rbac.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.lanqiao.rbac.config.SysConst;
import org.lanqiao.rbac.entity.Account;
import org.lanqiao.rbac.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.web2017.commons.MD5Util;
import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultGenerator;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by web2017 on 2017/08/23.
 */
@RestController
@RequestMapping("/rbac/account")
public class AccountAPI {
  @Resource
  private AccountService accountService;

  @PostMapping
  public Result add(Account account) {
    account.setPassword(MD5Util.md5(account.getPassword(),SysConst.SALT));
    accountService.save(account);
    return ResultGenerator.genSuccessResult();
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Integer id) {
    accountService.deleteById(id);
    return ResultGenerator.genSuccessResult();
  }

  @PutMapping
  public Result update(Account account) {
    accountService.update(account);
    return ResultGenerator.genSuccessResult();
  }

  @GetMapping("/{id}")
  public Result detail(@PathVariable Integer id) {
    Account account = accountService.findById(id);
    return ResultGenerator.genSuccessResult(account);
  }

  @GetMapping
  @ApiOperation("分页展示账户信息")
  public Result list(Integer pageNumber, Integer pageSize) {
    PageInfo pageInfo = accountService.findAll(pageNumber, pageSize);
    return ResultGenerator.genSuccessResult(pageInfo);
  }

  @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
  @ApiOperation(value = "登录验证", /*httpMethod = "GET",*/ /*response = Result.class,*/ notes = "登录验证")
  public Result login(Account account) {
    if (null == account.getAccount()) {
      return ResultGenerator.genFailResult("请登录");
    } else {
      try {
        Map<String, Object> resultMap = new HashMap<>();
        String serverToken = check(account);
        Integer userid = accountService.saveToken(account, serverToken);
        resultMap.put("userid", userid);
        resultMap.put("token", serverToken);
        return ResultGenerator.genSuccessResult(resultMap);
      } catch (AuthenticationException e) { // 失败
        return ResultGenerator.genFailResult("登录失败");
      }
    }
  }

  private String check(Account account) {
    final String password = account.getPassword(); // 明文密码
    /*数据库中存储的是md5+盐加密后的密码，因此这里要把加密后的密码传入*/
    final String md5Password = MD5Util.md5(password, SysConst.SALT);
    AuthenticationToken token = new UsernamePasswordToken(account.getAccount(), md5Password);
    Subject currentSubject = SecurityUtils.getSubject();
    currentSubject.login(token);
    String serverToken = UUID.randomUUID().toString().replaceAll("-", "");
    return serverToken;
  }

  @RequestMapping("/unauthorized")
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public Result unauthorized() {
    return ResultGenerator.genFailResult("权限不足");
  }
}
