package org.lanqiao.rbac.web;

import org.lanqiao.rbac.core.Result;
import org.lanqiao.rbac.core.ResultGenerator;
import org.lanqiao.rbac.entity.Log;
import org.lanqiao.rbac.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zhengwei on 2017/08/20.
*/
@RestController
@RequestMapping("/rbac/log")
public class LogController {
    @Resource
    private LogService logService;

    @PostMapping
    public Result add(Log log) {
        logService.save(log);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Log log) {
        logService.update(log);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Log log = logService.findById(id);
        return ResultGenerator.genSuccessResult(log);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Log> list = logService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
