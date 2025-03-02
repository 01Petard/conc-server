package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.entity.Clazz;
import com.hzx.conc.service.ClazzService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @ApiOperation("测试")
    @GetMapping("/list")
    public Result<List<Clazz>> ping() {
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }


}
