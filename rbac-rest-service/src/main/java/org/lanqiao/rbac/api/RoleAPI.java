package org.lanqiao.rbac.api;

import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultGenerator;
import org.lanqiao.rbac.entity.Role;
import org.lanqiao.rbac.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by web2017 on 2017/08/23.
*/
@RestController
@RequestMapping("/rbac/role")
public class RoleAPI {
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
    public Result list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = roleService.findAll(pageNumber,pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
