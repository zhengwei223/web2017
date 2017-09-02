package org.lanqiao.rbac.api;

import org.web2017.web.rest.Result;
import org.web2017.web.rest.ResultGenerator;
import org.lanqiao.rbac.entity.Log;
import org.lanqiao.rbac.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by web2017 on 2017/08/23.
*/
@RestController
@RequestMapping("/rbac/log")
public class LogAPI {
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
    public Result list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = logService.findAll(pageNumber,pageSize);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
