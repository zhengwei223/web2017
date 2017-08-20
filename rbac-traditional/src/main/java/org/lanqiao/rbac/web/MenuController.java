package org.lanqiao.rbac.web;

import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.Menu;
import org.lanqiao.rbac.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/20.
*/
@RestController
@RequestMapping("/rbac/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @PostMapping
    public Result add(Menu menu) {
        menuService.save(menu);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Menu menu) {
        menuService.update(menu);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return ResultGenerator.genSuccessResult(menu);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Menu> list = menuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
