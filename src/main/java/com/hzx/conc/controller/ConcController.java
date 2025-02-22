package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConcController
 *
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@RestController
@RequestMapping("/conc")
public class ConcController {

    @ApiOperation("查询学生的专注度")
    @GetMapping("/query")
    public Result<String> query(
            @RequestParam("sno") Long sno
    ) {
        return Result.success("pong");
    }
}
