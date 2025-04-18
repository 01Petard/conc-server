package com.hzx.conc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.model.dto.DeptDto;
import com.hzx.conc.model.entity.Clazz;
import com.hzx.conc.model.entity.Dept;
import com.hzx.conc.model.vo.DeptClazzVo;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.DeptService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学院管理
 * @author hzx
 * @create 2024-04-17
 */
@CrossOrigin
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Resource
    private DeptService deptService;

    @Resource
    private ClazzService clazzService;


    @ApiOperation("新增学院")
    @PostMapping("/add")
    public Result<String> addDept(@RequestBody Dept dept) {
        deptService.save(dept);
        return Result.success();
    }

    @ApiOperation("删除学院")
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteDept(@PathVariable String id) {
        deptService.removeById(id);
        return Result.success();
    }

    @ApiOperation("更新学院信息")
    @PutMapping("/update")
    public Result<String> updateDept(@RequestBody DeptDto dto) {
        Dept dept = new Dept();
        dept.setDeptId(dto.getDeptId());
        dept.setDeptName(dto.getDeptName());
        dept.setUpdateTime(new Date());
        deptService.updateById(dept);
        return Result.success();
    }

    @ApiOperation("根据ID查询学院")
    @GetMapping("/get/{id}")
    public Result<String> getDeptById(@PathVariable String id) {
        deptService.getById(id);
        return Result.success();
    }

    @ApiOperation("查询所有学院和对应的班级")
    @GetMapping("/list")
    public Result<List<DeptClazzVo>> listDepts() {
        List<Dept> deptList = deptService.list();
        List<DeptClazzVo> vo = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptClazzVo deptClazzVo = new DeptClazzVo();
            deptClazzVo.setDeptId(dept.getDeptId());
            deptClazzVo.setDeptName(dept.getDeptName());
            List<Clazz> clazzList = clazzService.list(new LambdaQueryWrapper<Clazz>()
                    .eq(Clazz::getDeptId, dept.getDeptId()));
            deptClazzVo.setClazzList(clazzList);
            vo.add(deptClazzVo);
        }
        return Result.success(vo);
    }

    @ApiOperation("查询所有学院和对应的班级")
    @GetMapping("/listAll")
    public Result<List<Dept>> listAllDepts() {
        return Result.success(deptService.list());
    }
}
