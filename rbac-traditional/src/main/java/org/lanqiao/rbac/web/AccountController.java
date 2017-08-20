package org.lanqiao.rbac.web;

import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.Account;
import org.lanqiao.rbac.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/20.
*/
@RestController
@RequestMapping("/rbac/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping
    public Result add(Account account) {
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
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Account> list = accountService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
