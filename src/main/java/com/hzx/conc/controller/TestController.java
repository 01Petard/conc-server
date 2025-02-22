package com.hzx.conc.controller;


import com.hzx.conc.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试")
    @GetMapping("/ping")
    public Result<String> ping() {
        return Result.success("pong");
    }



}