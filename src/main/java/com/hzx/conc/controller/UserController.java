package com.hzx.conc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.exception.BaseException;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.model.dto.UserDto;
import com.hzx.conc.model.entity.User;
import com.hzx.conc.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理
 * @author zexiao.huang
 * @date 2025/4/19 13:21
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;


    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDto dto) {
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, dto.getUserName())
                .eq(User::getPassword, dto.getPassword())
        );
        if (one == null){
            throw new BaseException(MessageConstant.USER_NOT_EXIST);
        }
        return Result.success();
    }

}