package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.entity.Student;
import com.hzx.conc.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @ApiOperation("获取所有学生数据")
    @GetMapping("/list")
    public Result<List<Student>> list() {
        List<Student> studentList = studentService.list();
        return Result.success(studentList);
    }

    @ApiOperation("获取班级的学生数据")
    @GetMapping("/listClazz")
    public Result<List<Student>> listByclazzId(
            @RequestParam("clazzId") Long clazzId,
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<Student> studentList = studentService.listByclazzId(clazzId, pageIndex, pageSize);
        return Result.success(studentList);
    }

}
