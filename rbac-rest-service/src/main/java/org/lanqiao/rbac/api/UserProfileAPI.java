package org.lanqiao.rbac.api;

import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultGenerator;
import org.lanqiao.rbac.entity.UserProfile;
import org.lanqiao.rbac.service.UserProfileService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by web2017 on 2017/08/23.
*/
@RestController
@RequestMapping("/rbac/user/profile")
public class UserProfileAPI {
    @Resource
    private UserProfileService userProfileService;

    @PostMapping
    public Result add(UserProfile userProfile) {
        userProfileService.save(userProfile);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userProfileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(UserProfile userProfile) {
        userProfileService.update(userProfile);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        UserProfile userProfile = userProfileService.findById(id);
        return ResultGenerator.genSuccessResult(userProfile);
    }

    @GetMapping
    public Result list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = userProfileService.findAll(pageNumber,pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
