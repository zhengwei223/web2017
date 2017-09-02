package org.lanqiao.rbac.api;

import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultGenerator;
import org.lanqiao.rbac.entity.UserRole;
import org.lanqiao.rbac.service.UserRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by web2017 on 2017/08/23.
*/
@RestController
@RequestMapping("/rbac/user/role")
public class UserRoleAPI {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping
    public Result add(UserRole userRole) {
        userRoleService.save(userRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(UserRole userRole) {
        userRoleService.update(userRole);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        UserRole userRole = userRoleService.findById(id);
        return ResultGenerator.genSuccessResult(userRole);
    }

    @GetMapping
    public Result list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = userRoleService.findAll(pageNumber,pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
