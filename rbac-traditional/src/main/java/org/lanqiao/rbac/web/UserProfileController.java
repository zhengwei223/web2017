package org.lanqiao.rbac.web;

import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.UserProfile;
import org.lanqiao.rbac.service.UserProfileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/20.
*/
@RestController
@RequestMapping("/rbac/user/profile")
public class UserProfileController {
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
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserProfile> list = userProfileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
