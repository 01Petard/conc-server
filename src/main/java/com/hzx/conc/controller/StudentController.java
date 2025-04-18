package com.hzx.conc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.common.utils.AliOssUtil;
import com.hzx.conc.model.dto.StudentEditDto;
import com.hzx.conc.model.entity.Dept;
import com.hzx.conc.model.entity.Student;
import com.hzx.conc.model.vo.StudentVo;
import com.hzx.conc.service.DeptService;
import com.hzx.conc.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 学生管理
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private DeptService deptService;

    @Resource
    private AliOssUtil aliOssUtil;

    private final String PATH_PREFIX = "src/main/resources/img";


    @ApiOperation("获取所有学生数据")
    @GetMapping("/list")
    public Result<List<Student>> list() {
        List<Student> studentList = studentService.list();

        // 遍历学生列表，将 faceimg 转换为 Base64 编码
        studentList.forEach(student -> {
            if (student.getFaceimg() != null && !student.getFaceimg().isEmpty()) {
                Path imagePath = Paths.get(PATH_PREFIX, student.getFaceimg()); // 获取图片路径
                try {
                    byte[] imageBytes = Files.readAllBytes(imagePath); // 读取图片字节
                    String base64Image = "data:image/png;base64," + Base64Utils.encodeToString(imageBytes); // 转换为 Base64
                    student.setFaceimg(base64Image); // 设置 Base64 字符串
                } catch (Exception e) {
                    student.setFaceimg(null); // 如果读取失败，设置为空
                    log.error(e.getMessage());
                }
            }
        });

        return Result.success(studentList);
    }

    @ApiOperation("获取所有学生数据")
    @GetMapping("/listName")
    public Result<List<StudentVo>> listName() {
        List<Student> studentList = studentService.list();

        List<StudentVo> voList = new ArrayList<>();

        studentList.forEach(student -> {
            StudentVo studentVo = new StudentVo();
            studentVo.setSno(student.getSno());
            studentVo.setSname(student.getSname());
            voList.add(studentVo);
        });
        return Result.success(voList);
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

    @PostMapping("/oss/upload")
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

    @ApiOperation("文件上传")
    @GetMapping("/upload")
    public Result<String> upload2(
            @RequestParam("fileName") String fileName,
            @RequestParam("name") String name
    ) {
        Student student = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getSname, name));
        if (ObjectUtils.isEmpty(student)) {
            return Result.error(MessageConstant.USER_NOT_EXIST);
        }
        student.setFaceimg(fileName);
        studentService.updateById(student);
        return Result.success();

    }


    @ApiOperation("添加学生")
    @PostMapping("/add")
    public Result<String> add(
            @RequestParam String sno,
            @RequestParam String sname,
            @RequestParam String ssex,
            @RequestParam String sdeptId,
            @RequestParam String clazzId,
            @RequestParam String sphone,
            @RequestParam String faceimg
    ) {
        // 查询是否存在学号相同的学生
        Student student = studentService.getOne(new LambdaQueryWrapper<Student>()
                .eq(Student::getSno, sno));
        if (!ObjectUtils.isEmpty(student)) {
            return Result.error(MessageConstant.USER_ALREADY_EXIST);
        }


        // 获取学院名称
        Dept dept = deptService.getOne(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getDeptId, sdeptId));

        Student stu = new Student();
        stu.setSno(sno);
        stu.setSname(sname);
        stu.setSsex(ssex);
        stu.setSdept(dept.getDeptName());
        stu.setClazzName(clazzId);
        stu.setSphone(sphone);
        stu.setFaceimg(faceimg);

        studentService.save(stu);
        return Result.success();
    }


    @ApiOperation("编辑学生信息")
    @PutMapping("/update")
    public Result<String> update(@RequestBody StudentEditDto dto) {

        Student student = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getSno, dto.getSno()));
        if (ObjectUtils.isEmpty(student)) {
            return Result.error(MessageConstant.USER_ALREADY_EXIST);
        }

        student.setSname(dto.sname);
        student.setSsex(dto.ssex);
        student.setSdept(dto.getSdept());
        student.setClazzName(dto.className);
        student.setSphone(dto.sphone);

        studentService.updateById(student);
        return Result.success();
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam String sno) {

        Student student = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getSno, sno));
        if (ObjectUtils.isEmpty(student)) {
            return Result.error(MessageConstant.USER_NOT_EXIST);
        }

        studentService.removeById(sno);
        return Result.success();
    }

}
