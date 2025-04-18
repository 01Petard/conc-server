package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.model.entity.Course;
import com.hzx.conc.service.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程管理
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Resource
    private CourseService courseService;

    @ApiOperation("测试")
    @GetMapping("/list")
    public Result<List<Course>> ping() {
        List<Course> list = courseService.list();
        return Result.success(list);
    }


}
