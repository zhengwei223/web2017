package org.lanqiao.rbac.web;
import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.UserInfoBalala;
import org.lanqiao.rbac.service.UserInfoBalalaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/18.
*/
@RestController
@RequestMapping("/user/info/balala")
public class UserInfoBalalaController {
    @Resource
    private UserInfoBalalaService userInfoBalalaService;

    @PostMapping("/add")
    public Result add(UserInfoBalala userInfoBalala) {
        userInfoBalalaService.save(userInfoBalala);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        userInfoBalalaService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UserInfoBalala userInfoBalala) {
        userInfoBalalaService.update(userInfoBalala);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        UserInfoBalala userInfoBalala = userInfoBalalaService.findById(id);
        return ResultGenerator.genSuccessResult(userInfoBalala);
    }

    @PostMapping("/list")
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserInfoBalala> list = userInfoBalalaService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
