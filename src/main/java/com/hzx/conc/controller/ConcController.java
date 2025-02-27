package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.entity.Conc;
import com.hzx.conc.service.ConcService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ConcController
 *
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@RestController
@RequestMapping("/conc")
public class ConcController {

    @Resource
    private ConcService concService;

    @ApiOperation("查询学生的专注度")
    @GetMapping("/query")
    public Result<String> query(
            @RequestParam("sno") Long sno
    ) {

        Conc conc = concService.getById(sno);
        String path = conc.getPath();
        if (path == null) {
            return Result.error("没有该学生的专注度");
        }
        // 调用获取专注度接口
        return Result.success("pong");
    }
}
