package com.hzx.conc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.common.utils.AliOssUtil;
import com.hzx.conc.entity.Student;
import com.hzx.conc.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private AliOssUtil aliOssUtil;


    @ApiOperation("获取所有学生数据")
    @GetMapping("/list")
    public Result<List<Student>> list() {
        List<Student> studentList = studentService.list();
        return Result.success(studentList);
    }

    @ApiOperation("获取每个班级的学生数据")
    @GetMapping("/listAllClazz")
    public Result<Map<Long, List<Student>>> listAllClazz(
            @RequestParam(value = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        Map<Long, List<Student>> studentMap = studentService.listClazz(pageIndex, pageSize);
        return Result.success(studentMap);
    }

    @ApiOperation("获取班级的学生数据")
    @GetMapping("/listClazz")
    public Result<List<Student>> listByclazzId(
            @RequestParam(value = "clazzId") Long clazzId,
            @RequestParam(value = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        List<Student> studentList = studentService.listByclazzId(clazzId, pageIndex, pageSize);
        return Result.success(studentList);
    }

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
    ) {
        Student student = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getSname, name));
        if (ObjectUtils.isEmpty(student)) {
            return Result.error(MessageConstant.USER_NOT_EXIST);
        }
        String filePath;
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            assert originalFilename != null;
            String extension_name = originalFilename.substring(originalFilename.lastIndexOf("."));
            //用UUID构造新文件名称
            String object_name = UUID.randomUUID() + extension_name;
            //文件的请求路径
            filePath = aliOssUtil.upload(file.getBytes(), object_name);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
        student.setFaceimg(filePath);
        studentService.updateById(student);
        return Result.success();

    }

}
