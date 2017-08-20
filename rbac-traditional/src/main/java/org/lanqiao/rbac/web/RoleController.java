package org.lanqiao.rbac.web;

import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.Role;
import org.lanqiao.rbac.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/20.
*/
@RestController
@RequestMapping("/rbac/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping
    public Result add(Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.genSuccessResult(role);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Role> list = roleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
